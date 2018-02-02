package com.lxw.news.util;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;

import com.lxw.news.NewsApp;
import com.lxw.news.R;

/**
 * author  LiXiaoWei
 * date  2018/1/31.
 * desc:
 */

public class SettingUtil {
    private SharedPreferences setting = PreferenceManager.getDefaultSharedPreferences(NewsApp.appContext);
    private static SettingUtil instance = new SettingUtil();

    public static SettingUtil getInstance() {
        return instance;
    }

    /**
     * 获取主题颜色
     */
    public int getColor() {
        int defaultColor = ContextCompat.getColor(NewsApp.appContext, R.color.colorPrimary);
        int color = setting.getInt("color", defaultColor);
        if (color != 0 && Color.alpha(color) != 255) {
            return defaultColor;
        }
        return color;
    }

    /**
     * 获取字体大小
     */
    public int getTextSize() {
        return setting.getInt("textsize", 16);
    }
}
