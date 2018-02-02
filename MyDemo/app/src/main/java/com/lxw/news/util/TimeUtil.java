package com.lxw.news.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * author  LiXiaoWei
 * date  2018/2/1.
 * desc:
 */

public class TimeUtil {
    public static String getCurrentTimeStamp() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }

    /**
     * 根据时间戳 返回发布时间距离当前的时间
     */
    public static String getTimeStampAgo(String timeStamp) {
        Long time = Long.valueOf(timeStamp);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        String string = sdf.format(time * 1000L);
        Date date = null;
        try {
            date = sdf.parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timeAgo(date);
    }
    /**
     * 返回发布时间距离当前的时间
     */
    public static String timeAgo(Date createdTime) {
        SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA);
        if (createdTime != null) {
            long agoTimeInMin = (new Date(System.currentTimeMillis()).getTime() - createdTime.getTime()) / 1000 / 60;
            // 如果在当前时间以前一分钟内
            if (agoTimeInMin <= 1) {
                return "刚刚";
            } else if (agoTimeInMin <= 60) {
                // 如果传入的参数时间在当前时间以前10分钟之内
                return agoTimeInMin + "分钟前";
            } else if (agoTimeInMin <= 60 * 24) {
                return agoTimeInMin / 60 + "小时前";
            } else if (agoTimeInMin <= 60 * 24 * 2) {
                return agoTimeInMin / (60 * 24) + "天前";
            } else {
                return format.format(createdTime);
            }
        } else {
            return format.format(new Date(0));
        }
    }
}
