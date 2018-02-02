package com.lxw.news.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;

/**
 * Created by Meiji on 2017/5/31.
 */

public class ImageLoader {

    public static void loadCenterCrop(Context context, String url, ImageView view, int defaultResId) {
        Glide.with(context).load(url).crossFade().centerCrop().placeholder(defaultResId).error(defaultResId).into(view);
    }

    /**
     * 带加载异常图片
     */
    public static void loadCenterCrop(Context context, String url, ImageView view, int defaultResId, int errorResId) {
        Glide.with(context).load(url).crossFade().centerCrop().placeholder(defaultResId).error(errorResId).into(view);
    }

    /**
     * 带监听处理
     */
    public static void loadCenterCrop(Context context, String url, ImageView view, RequestListener listener) {
        Glide.with(context).load(url).crossFade().centerCrop().listener(listener).into(view);
    }
}
