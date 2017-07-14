package com.zeng.fanda.dudu.widget.imageloader;

import android.content.Context;

/**
 * Created by David on 2017/6/14.
 * 策略基类，定义实现类需要什么样的功能
 */

public interface BaseImageLoaderStrategy<T extends ImageConfig> {
    /**
     * 加载图片
     *
     * @param context
     * @param config
     */
    void loadImage(Context context, T config);

    /**
     * 清除缓存
     *
     * @param context
     * @param config
     */
    void clear(Context context, T config);

}
