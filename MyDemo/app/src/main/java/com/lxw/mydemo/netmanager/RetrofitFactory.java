package com.lxw.mydemo.netmanager;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.lxw.mydemo.NewsApp;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * author  LiXiaoWei
 * date  2018/1/19.
 * desc:
 */

public class RetrofitFactory {

    private static Retrofit retrofit;

    public static ApiService getRetrofit() {
        if (retrofit == null) {
            synchronized (RetrofitFactory.class) {
                if (retrofit == null) {
                    Cache cache = new Cache(new File(NewsApp.appContext.getCacheDir(), "HttpCache"), 1024 * 1024 * 50);
                    OkHttpClient.Builder builder = new OkHttpClient.Builder()
                            .cache(cache)
                            .addInterceptor(addCommonHeader())
                            .addNetworkInterceptor(new StethoInterceptor())
                            .connectTimeout(10, TimeUnit.SECONDS)
                            .readTimeout(15, TimeUnit.SECONDS)
                            .writeTimeout(15, TimeUnit.SECONDS)
                            .retryOnConnectionFailure(true);
                    retrofit = new Retrofit.Builder()
                            .baseUrl("https://api.phc-dow.com/")
                            .client(builder.build())
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .build();
                }
            }
        }
        return retrofit.create(ApiService.class);
    }

    private static Interceptor addCommonHeader() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request.Builder builder = chain.request().newBuilder();
                builder.addHeader("token", "efacf9e366764d626ee6cba9f8149a71fbc00a18");
                return chain.proceed(builder.build());
            }
        };
    }
}