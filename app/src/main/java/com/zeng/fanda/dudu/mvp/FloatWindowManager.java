/*
 * Copyright (C) 2016 Facishare Technology Co., Ltd. All Rights Reserved.
 */
package com.zeng.fanda.dudu.mvp;

import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.util.Log;
import android.view.Gravity;
import android.view.WindowManager;

import java.lang.reflect.Field;

/**
 * Description:
 *
 * @author zhaozp
 * @since 2016-10-17
 */

public class FloatWindowManager {

    private static final String TAG = "FloatWindowManager";

    private static volatile FloatWindowManager instance;
    private boolean isWindowDismiss = true;
    private WindowManager windowManager;
    private WindowManager.LayoutParams params;
    private FloatView floatView;

    public static FloatWindowManager get() {
        if (instance == null) {
            synchronized (FloatWindowManager.class) {
                if (instance == null) {
                    instance = new FloatWindowManager();
                }
            }
        }
        return instance;
    }

    /**
     * 是否已经显示悬浮窗
     *
     * @return
     */
    public boolean isShowing() {
        return floatView != null && floatView.isShowing();
    }

    /**
     * 显示悬浮窗
     */
    public void show(Context context) {
        if (!isWindowDismiss) {
            Log.e(TAG, "view is already added here");
            return;
        }

        isWindowDismiss = false;
        if (windowManager == null) {
            windowManager =
                    (WindowManager) context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        }

        Point size = new Point();
        windowManager.getDefaultDisplay().getSize(size);
        int screenWidth = size.x;

        floatView = new FloatView(context);

        params = new WindowManager.LayoutParams();
        params.packageName = context.getPackageName();
        params.width = FloatView.viewWidth;
        params.height = FloatView.viewHeight;
        params.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;

        params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
        params.format = PixelFormat.RGBA_8888;
        params.gravity = Gravity.LEFT | Gravity.TOP;
        params.x = screenWidth;
        params.y = 0;

        floatView.setParams(params);
        floatView.setIsShowing(true);
        windowManager.addView(floatView, params);
    }

    /**
     * 关闭悬浮窗
     */
    public void dismiss() {
        if (isWindowDismiss) {
            Log.e(TAG, "window can not be dismiss cause it has not been added");
            return;
        }
        isWindowDismiss = true;
        floatView.setIsShowing(false);
        if (windowManager != null && floatView != null) {
            windowManager.removeViewImmediate(floatView);
        }
    }

}
