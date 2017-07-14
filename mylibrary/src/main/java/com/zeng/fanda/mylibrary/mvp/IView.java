package com.zeng.fanda.mylibrary.mvp;

/**
 * Created by David on 2017/6/6.
 * View层接口基层
 */

public interface IView {

    /**
     * 显示加载
     */
    void showLoading();

    /**
     * 隐藏加载
     */
    void hideLoading();

    /**
     * 关闭界面
     */
    void finishMyself();

}
