package com.zeng.fanda.mylibrary.core.imageloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;

import com.zeng.fanda.mylibrary.core.cache.DiskCache;
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


/**
 * Created by David on 2017/7/17.
 * 图片加载器
 */

public class ImageLoader02 {

    //内存缓存
    ImageCache mImageCache = new ImageCache();

    //SD卡缓存
    DiskCache mDiskCache = new DiskCache();

    boolean isUseDiskCache = true;

    //线程池，线程数量为CPU的数量
    ExecutorService mExecutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    final Bitmap[] bitmap = {null};

    public void displayImage(final String url, final ImageView imageView) {


//        Bitmap bitmap = isUseDiskCache ? mDiskCache.get(url) : mImageCache.get(url);
//        if (bitmap != null) {
//            imageView.setImageBitmap(bitmap);
//            return;
//        }

        imageView.setTag(url);

        downloadImage(url);

        if (imageView.getTag().equals(url)) {
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageBitmap(bitmap[0]);
        }
//        if (bitmap == null) {
//            return;
//        }
//        if (imageView.getTag().equals(url)) {
//            imageView.setVisibility(View.VISIBLE);
//            imageView.setImageBitmap(bitmap);
//        }
//
//        if (isUseDiskCache) {
//            mDiskCache.put(url, bitmap);
//        } else {
//            mImageCache.put(url, bitmap);
//        }

//        mExecutorService.submit(new Runnable() {
//            @Override
//            public void run() {
//                Bitmap bitmap = downloadImage(url);
//                if (bitmap == null) {
//                    return;
//                }
//                if (imageView.getTag().equals(url)) {
//                    imageView.setVisibility(View.VISIBLE);
//                    imageView.setImageBitmap(bitmap);
//                }
//
//                if (isUseDiskCache) {
//                    mDiskCache.put(url, bitmap);
//                } else {
//                    mImageCache.put(url, bitmap);
//                }
//            }
//        });
    }

    public void downloadImage(String imageUrl) {

        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(imageUrl).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                bitmap[0] = BitmapFactory.decodeStream(response.body().byteStream());
            }
        });

    }


//    public Bitmap downloadImage(String imageUrl) {
//        Bitmap bitmap = null;
//        try {
//            URL url = new URL(imageUrl);
//            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            bitmap = BitmapFactory.decodeStream(conn.getInputStream());
//            conn.disconnect();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return bitmap;
//    }

    public void setUseDiskCache(boolean useDiskCache) {
        this.isUseDiskCache = useDiskCache;
    }
}
