package com.zeng.fanda.dudu.di.component;

import android.app.Application;

import com.zeng.fanda.dudu.di.module.AppModule;
import com.zeng.fanda.dudu.di.module.GlobalConfigModule;
import com.zeng.fanda.dudu.widget.imageloader.ImageLoader;

import dagger.Component;

/**
 * Creatd by David on 2017/6/6.
 */

@Component(modules = {AppModule.class,GlobalConfigModule.class})
public interface AppComponent {
    Application application();

    //图片管理器,用于加载图片的管理类,默认使用glide,使用策略模式,可替换框架
    ImageLoader imageLoader();

    void inject(Application application);
}
