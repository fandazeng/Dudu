package com.zeng.fanda.mylibrary.config;

import android.content.Context;

/**
 * Created by Linky on 16-9-19.
 * 全局的Context
 */
public class GlobalContext {
    private static Context context;

    public static void init(Context context) {
        GlobalContext.context = context;
    }

    public static Context getContext() {
        return context;
    }
}