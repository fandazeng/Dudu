package com.zeng.fanda.mylibrary.core.imageloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import com.zeng.fanda.mylibrary.core.cache.ImageCache;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.content.ContentValues.TAG;


/**
 * Created by David on 2017/7/17.
 * 图片加载器
 */

public class ImageLoader01plus {

    private static final String TAG = "ImageLoader01plus";

    ImageCache mImageCache = new ImageCache();

    //线程池，线程数量为CPU的数量
    ExecutorService mExecutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public void displayImage(final String url, final ImageView imageView) {
        Bitmap bitmap = mImageCache.get(url);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
            imageView.invalidate();
            Log.d(TAG, "displayImage: ");
            return;
        }
        imageView.setTag(url);

        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap =downloadImage(url);
                if (bitmap == null) {
                    return;
                }
                if (imageView.getTag().equals(url)) {
                    imageView.setImageBitmap(bitmap);
                    imageView.invalidate();
                }
                mImageCache.put(url,bitmap);
            }
        });
    }


    public Bitmap downloadImage( String imageUrl  ) {
        Bitmap bitmap = null;
        try {
            URL url = new URL(imageUrl);
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            bitmap = BitmapFactory.decodeStream(conn.getInputStream());
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;

//        try {
//            OkHttpClient okHttpClient = new OkHttpClient();
//            Request request = new Request.Builder().url(imageUrl).build();
//            Response response = okHttpClient.newCall(request).execute();
//            bitmap = BitmapFactory.decodeStream(response.body().byteStream());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return bitmap;
    }
}
