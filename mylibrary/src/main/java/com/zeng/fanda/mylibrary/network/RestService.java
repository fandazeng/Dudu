package com.zeng.fanda.mylibrary.network;

import com.zeng.fanda.mylibrary.Utils.DuduLogUtil;
import com.zeng.fanda.mylibrary.Utils.SPUtil;
import com.zeng.fanda.mylibrary.config.Constants;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Created by David on 2017/6/5.
 * 请求服务端
 */

public class RestService {

    private static final String URL = "https://api.dribbble.com/v1/";

    private static Retrofit mRetrofit;

    private RestService() {
    }

    static {
        //配置相关属性
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new TakenInterceptor());
        if (DuduLogUtil.isDebug) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String s) {
                    DuduLogUtil.d(s);
                }
            });
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(httpLoggingInterceptor);
        }

        mRetrofit = new Retrofit.Builder().baseUrl(URL).client(builder.build())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(FastjsonConverterFactory.create())
                .build();
    }

    public static <T> T from(Class<T> service) {
        return mRetrofit.create(service);
    }

    private static class TakenInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            //设置Authorization标头
            request = request.newBuilder().addHeader("Authorization", "Bearer " + SPUtil.getString(Constants.PREF_TAKEN, "")).build();
            return chain.proceed(request);
        }
    }


}
