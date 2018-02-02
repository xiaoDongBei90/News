package com.lxw.news.module.joke.comment;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.lxw.news.R;
import com.lxw.news.module.base.BaseListFragment;

import java.util.List;

import me.drakeet.multitype.MultiTypeAdapter;

/**
 * author  LiXiaoWei
 * date  2018/2/1.
 * desc:
 */

public class JokeCommentFragment extends BaseListFragment<IJokeComment.Presenter> implements IJokeComment.View {
    public static final String TAG = "JokeCommentFragment";

    public static JokeCommentFragment newInstance(Parcelable data) {
        Bundle args = new Bundle();
        args.putParcelable(TAG, data);
        JokeCommentFragment fragment = new JokeCommentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        initToolBar(toolbar, true, "");
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rvFragment.smoothScrollToPosition(0);
            }
        });
        adapter = new MultiTypeAdapter(oldItems);
        //Register.registerJokeCommentItem(adapter);
    }

    @Override
    public void onLoadData() {

    }

    @Override
    public void setPresenter(IJokeComment.Presenter persenter) {

    }

    @Override
    public void onSetAdapter(List<?> list) {

    }

    @Override
    protected void initData() throws NullPointerException {

    }
}
