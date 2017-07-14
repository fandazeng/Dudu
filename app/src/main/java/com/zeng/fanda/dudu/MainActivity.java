package com.zeng.fanda.dudu;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.zeng.fanda.dudu.base.DuduApplication;
import com.zeng.fanda.dudu.di.component.AppComponent;
import com.zeng.fanda.dudu.widget.imageloader.ImageLoader;
import com.zeng.fanda.dudu.widget.imageloader.glide.GlideImageConfig;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class MainActivity extends AppCompatActivity {

    ImageLoader mImageLoader;

    AppComponent mAppComponent;

    @BindView(R.id.iv_image)
    ImageView iv_image;

    private SimpleTarget drawableTarget = new SimpleTarget<GlideBitmapDrawable>() {
        @Override
        public void onResourceReady(GlideBitmapDrawable resource, GlideAnimation<? super GlideBitmapDrawable> glideAnimation) {
            iv_image.setImageDrawable(resource);
        }
    };

    private SimpleTarget bitmapTarget = new SimpleTarget<Bitmap>(250, 250) {
        @Override
        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
            iv_image.setImageBitmap(resource);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Glide.with(this);

        mAppComponent = ((DuduApplication) getApplication()).getAppComponent();
        mImageLoader = mAppComponent.imageLoader();
    }

    @OnClick(R.id.tv_load)
    public void load() {

//        Glide.with(this).load("http://img.zcool.cn/community/01822155c1a44f6ac7253f360673f6.gif").asBitmap().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(iv_image);
        String url = "http://www.jianbihua.cc/uploads/allimg/140215/2-140215123319130.jpg";
//        String url2 = "http://pic.58pic.com/58pic/14/15/11/658PICw58PICCkU_1024.jpg";
//
//        DrawableRequestBuilder<String> thumbnailRequest = Glide.with(this).load(url).priority(Priority.HIGH);
//
//        RequestListener<String, Bitmap> requestListener = new RequestListener<String, Bitmap>() {
//            @Override
//            public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
//                ToastUtils.showShortToast(MainActivity.this,e.getMessage());
//                return false;
//            }
//
//            @Override
//            public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
//                return false;
//            }
//        };
//
//        Glide.with(this)
//                .load(url2)
//                .bitmapTransform(new CropCircleTransformation(this),new GrayscaleTransformation(this))
//                .into(drawableTarget);

        mImageLoader.loadImage(this, GlideImageConfig.builder().url(url).placeholder(R.mipmap.icon_home_placeholder).errorPic(R.mipmap.icon_loading_error).transformation(new CropCircleTransformation(this)).imageView(iv_image).build());

    }

}
