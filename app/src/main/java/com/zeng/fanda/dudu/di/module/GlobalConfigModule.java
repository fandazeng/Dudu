package com.zeng.fanda.dudu.di.module;

import com.zeng.fanda.dudu.di.scope.ActivityScope;
import com.zeng.fanda.dudu.widget.imageloader.BaseImageLoaderStrategy;
import com.zeng.fanda.dudu.widget.imageloader.ImageLoader;
import com.zeng.fanda.dudu.widget.imageloader.glide.GlideImageLoaderStrategy;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

/**
 * Created by David on 2017/6/28.
 * 全局提供依赖
 */
@ActivityScope
@Module
public class GlobalConfigModule {

    @Provides
    ImageLoader provideImageLoader() {
        return new ImageLoader(new GlideImageLoaderStrategy());
    }
}
