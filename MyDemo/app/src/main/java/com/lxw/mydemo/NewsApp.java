package com.lxw.mydemo;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;

/**
 * author  LiXiaoWei
 * date  2018/1/19.
 * desc:
 */

public class NewsApp extends Application {

    public static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
        Stetho.initializeWithDefaults(this);
    }
}
