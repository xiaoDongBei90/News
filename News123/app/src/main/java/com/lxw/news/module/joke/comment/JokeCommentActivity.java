package com.lxw.news.module.joke.comment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lxw.news.NewsApp;
import com.lxw.news.R;
import com.lxw.news.bean.joke.JokeContentBean;
import com.lxw.news.module.base.BaseActivity;

/**
 * author  LiXiaoWei
 * date  2018/2/1.
 * desc:
 */

public class JokeCommentActivity extends BaseActivity {
    private static final String TAG = "NewsCommentView";
    public static void launch(JokeContentBean.DataBean.GroupBean bean) {
        NewsApp.appContext.startActivity(new Intent(NewsApp.appContext,JokeCommentActivity.class)
                .putExtra(TAG, bean)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container);
        //getSupportFragmentManager().beginTransaction().replace(R.id.container,Jokecom)
    }
}
