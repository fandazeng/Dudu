package com.zeng.fanda.mylibrary.Utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

/**
 * Created by David on 2017/6/5.\
 *  日志管理类
 */

public class DuduLogUtil {

    public static final String GLOBAL_TAG = "dudu_log";

    public static  boolean isDebug = true;

    public static void init(Context context) {

        isDebug = context.getApplicationInfo() != null &&
                (context.getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;

        setLogAdapter();

    }

    public static void setLogAdapter() {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder().tag(GLOBAL_TAG).build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
        Logger.addLogAdapter(new AndroidLogAdapter(){
//        Logger.addLogAdapter(new DiskLogAdapter()); //Save logs to the file
            @Override
            public boolean isLoggable(int priority, String tag) {
                return isDebug;
            }
        });


    }

    public static void e(String msg) {
        Logger.e(msg);
    }

    public static void w(String msg) {
        Logger.w(msg);
    }

    public static void i(String msg) {
        Logger.i(msg);
    }

    public static void d(String msg) {
        Logger.d(msg);
    }

    public static void v(String msg) {
        Logger.v(msg);
    }

    public static void json(String json) {
        Logger.json(json);
    }

    public static void xml(String xml) {
        Logger.json(xml);
    }

}
