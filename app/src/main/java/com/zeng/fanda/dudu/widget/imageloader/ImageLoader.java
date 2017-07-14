package com.zeng.fanda.dudu.widget.imageloader;

import android.content.Context;

import javax.inject.Inject;


/**
 * Created by David on 2017/6/28.
 * 图片加载上下文应用类
 */

public final class ImageLoader {
    private BaseImageLoaderStrategy mImageLoaderStrategy;

    @Inject
    public ImageLoader(BaseImageLoaderStrategy imageLoaderStrategy) {
        setImageLoaderStrategy(imageLoaderStrategy);
    }

    public <T extends ImageConfig> void loadImage(Context context, T config) {
        this.mImageLoaderStrategy.loadImage(context, config);
    }

    public <T extends ImageConfig> void clear(Context context, T config) {
        this.mImageLoaderStrategy.clear(context, config);
    }

    public void setImageLoaderStrategy(BaseImageLoaderStrategy strategy) {
        this.mImageLoaderStrategy = strategy;
    }
}
