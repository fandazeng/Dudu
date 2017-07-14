package com.zeng.fanda.dudu.base;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.zeng.fanda.mylibrary.mvp.IPresenter;
import com.zhy.autolayout.AutoFrameLayout;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import javax.inject.Inject;

import static com.zeng.fanda.mylibrary.config.Constants.LAYOUT_FRAME_LAYOUT;
import static com.zeng.fanda.mylibrary.config.Constants.LAYOUT_LINEAR_LAYOUT;
import static com.zeng.fanda.mylibrary.config.Constants.LAYOUT_RELATIVE_LAYOUT;

/**
 * Created by David on 2017/6/6.
 */

public abstract class BaseActivity<P extends IPresenter> extends RxAppCompatActivity {

    @Inject
    protected P mPresenter;

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        View view = null;
        if (name.equals(LAYOUT_FRAME_LAYOUT)) {
            view = new AutoFrameLayout(context, attrs);
        }

        if (name.equals(LAYOUT_LINEAR_LAYOUT)) {
            view = new AutoLinearLayout(context, attrs);
        }

        if (name.equals(LAYOUT_RELATIVE_LAYOUT)) {
            view = new AutoRelativeLayout(context, attrs);
        }

        if (view != null) return view;

        return super.onCreateView(name, context, attrs);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestroy();
            this.mPresenter = null;
        }
    }
}
