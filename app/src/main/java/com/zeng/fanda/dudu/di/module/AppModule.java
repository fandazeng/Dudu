package com.zeng.fanda.dudu.di.module;

import android.app.Application;

import com.zeng.fanda.dudu.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by David on 2017/6/6.
 * 用于提供在整个 应用生命周期都存活的对象
 */

@ActivityScope
@Module
public class AppModule {
    private Application mApplication;

    public AppModule(Application mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    public Application provideApplication() {
        return mApplication;
    }
}
