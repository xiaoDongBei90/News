package com.lxw.news;

import android.content.Context;
import android.content.Intent;

import javax.annotation.Nonnull;

/**
 * author  LiXiaoWei
 * date  2018/2/2.
 * desc:
 */

public class IntentAction {
    public static void send(@Nonnull Context context, @Nonnull String shareText) {
        Intent shareIntent = new Intent()
                .setAction(Intent.ACTION_SEND)
                .setType("text/plain")
                .putExtra(Intent.EXTRA_TEXT, shareText);
        context.startActivity(Intent.createChooser(shareIntent, "分享"));
    }
}
