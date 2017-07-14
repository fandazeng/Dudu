package com.zeng.fanda.mylibrary.mvp;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by David on 2017/6/6.
 * p层基类
 */

public class BasePresenter<M extends IModel, V extends IView> implements IPresenter {

    protected CompositeSubscription mCompositeSubscription;

    protected M mModel;
    protected V mRootView;

    public BasePresenter(M model, V rootView) {
        this.mModel = mModel;
        this.mRootView = mRootView;
        onStart();
    }

    public BasePresenter(V mRootView) {
        this.mRootView = mRootView;
        onStart();
    }

    public BasePresenter() {
        onStart();
    }

    @Override
    public void onStart() {
        //注册总线事件等
    }

    @Override
    public void onDestroy() {
        unSubscription();
        this.mModel = null;
        this.mRootView = null;
        this.mCompositeSubscription = null;
    }

    /**
     * 添加订阅事件
     *
     * @param subscription 事件
     */
    protected void addSubscription(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    /**
     * 清除订阅事件
     */
    protected void unSubscription() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.clear();
        }
    }
}
