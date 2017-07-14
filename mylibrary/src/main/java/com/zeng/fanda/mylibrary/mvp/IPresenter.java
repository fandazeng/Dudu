package com.zeng.fanda.mylibrary.mvp;

/**
 * Created by David on 2017/6/6.
 * P层基层接口
 */

public interface IPresenter {

    /**
     * 用来做初始化工作
     */
    void onStart();

    /**
     * 用来做善后工作
     */
    void onDestroy();

}
