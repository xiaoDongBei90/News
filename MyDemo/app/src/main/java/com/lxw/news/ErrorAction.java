package com.lxw.news;

import android.support.annotation.NonNull;

import javax.annotation.Nonnull;

import io.reactivex.functions.Consumer;

/**
 * author  LiXiaoWei
 * date  2018/2/1.
 * desc:
 */

public class ErrorAction {
    @NonNull
    public static Consumer<Throwable> error() {
        return new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                if (BuildConfig.DEBUG) {
                    throwable.printStackTrace();
                }
            }
        };
    }

    public static void print(@Nonnull Throwable throwable) {
        if (BuildConfig.DEBUG) {
            throwable.printStackTrace();
        }
    }
}
