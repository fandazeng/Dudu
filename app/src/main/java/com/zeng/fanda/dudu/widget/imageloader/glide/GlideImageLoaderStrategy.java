package com.zeng.fanda.dudu.widget.imageloader.glide;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.Target;
import com.zeng.fanda.dudu.widget.imageloader.BaseImageLoaderStrategy;

import rx.Observable;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by David on 2017/6/28.
 */

public class GlideImageLoaderStrategy implements BaseImageLoaderStrategy<GlideImageConfig> {

    @Override
    public void loadImage(Context context, GlideImageConfig config) {
        if (context == null) throw new IllegalStateException("Context is required");
        if (config == null) throw new IllegalStateException("GlideImageConfig is required");
        if (TextUtils.isEmpty(config.getUrl())) throw new IllegalStateException("Url is required");
        if (config.getImageView() == null) throw new IllegalStateException("Imageview is required");

        DrawableRequestBuilder<String> requestBuilder = Glide.with(context).load(config.getUrl()).crossFade();

        switch (config.getCacheStrategy()) {
            case 0:
                requestBuilder.diskCacheStrategy(DiskCacheStrategy.ALL);
                break;
            case 1:
                requestBuilder.diskCacheStrategy(DiskCacheStrategy.NONE);
                break;
            case 2:
                requestBuilder.diskCacheStrategy(DiskCacheStrategy.SOURCE);
                break;
            case 3:
                requestBuilder.diskCacheStrategy(DiskCacheStrategy.RESULT);
                break;
        }

        if (config.getTransformation() != null) {
            requestBuilder.transform(config.getTransformation());
        }

        if (config.getPlaceholder() != 0) {
            requestBuilder.placeholder(config.getPlaceholder());
        }

        if (config.getErrorPic() != 0) {
            requestBuilder.error(config.getErrorPic());
        }

        requestBuilder.into(config.getImageView());
    }

    @Override
    public void clear(final Context context, GlideImageConfig config) {
        if (context == null) throw new IllegalStateException("Context is required");
        if (config == null) throw new IllegalStateException("GlideImageConfig is required");

        if (config.getImageViews() != null && config.getImageViews().length > 0) {//取消在执行的任务并且释放资源
            for (ImageView imageView : config.getImageViews()) {
                Glide.clear(imageView);
            }
        }

        if (config.getTargets() != null && config.getTargets().length > 0) {//取消在执行的任务并且释放资源
            for (Target target : config.getTargets())
                Glide.clear(target);
        }

        // 必须在后台线程中调用
        if (config.isClearDiskCache()) {//清除本地缓存
            Observable.just(0)
                    .observeOn(Schedulers.io())
                    .subscribe(new Action1<Integer>() {
                        @Override
                        public void call(Integer integer) {
                            Glide.get(context).clearDiskCache();
                        }
                    });
        }

        if (config.isClearMemory()) {//清除内存缓存
            Glide.get(context).clearMemory();
        }
    }
}
