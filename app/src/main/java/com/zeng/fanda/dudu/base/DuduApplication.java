package com.zeng.fanda.dudu.base;

import android.app.Application;

import com.zeng.fanda.dudu.di.component.AppComponent;
import com.zeng.fanda.dudu.di.component.DaggerAppComponent;
import com.zeng.fanda.dudu.di.module.AppModule;
import com.zeng.fanda.mylibrary.Utils.DuduLogUtil;
import com.zeng.fanda.mylibrary.config.GlobalContext;
import com.zhy.autolayout.config.AutoLayoutConifg;

/**
 * Created by David on 2017/6/6.
 */

public class DuduApplication extends Application {

    AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        DuduLogUtil.init(this);
        GlobalContext.init(this);

        setupComponent();

        //拿设备的物理高度进行百分比化
        AutoLayoutConifg.getInstance().useDeviceSize();

    }

    private void setupComponent() {

        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
//        mAppComponent.inject(this);

    }

    /**
     * 将AppComponent返回出去,供其它地方使用, AppComponent接口中声明的方法返回的实例,在getAppComponent()拿到对象后都可以直接使用
     *
     * @return
     */
    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
