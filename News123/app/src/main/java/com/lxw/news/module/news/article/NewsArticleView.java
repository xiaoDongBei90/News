package com.lxw.news.module.news.article;

import android.os.Bundle;
import android.view.View;

import com.lxw.news.Register;
import com.lxw.news.bean.LoadingBean;
import com.lxw.news.module.base.BaseListFragment;
import com.lxw.news.util.DiffCallback;
import com.lxw.news.util.onLoadMoreListener;

import java.util.List;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * author  LiXiaoWei
 * date  2018/2/2.
 * desc:
 */

public class NewsArticleView extends BaseListFragment<INewsArticle.Presenter> implements INewsArticle.View {
    private static final String TAG = "NewsArticleView";
    private String categoryId;

    public static NewsArticleView newInstance(String categoryId) {
        Bundle bundle = new Bundle();
        bundle.putString(TAG, categoryId);
        NewsArticleView view = new NewsArticleView();
        view.setArguments(bundle);
        return view;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        adapter = new MultiTypeAdapter(oldItems);
        Register.registerNewsArticleItem(adapter);
        rvFragment.setAdapter(adapter);
        rvFragment.addOnScrollListener(new onLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (canLoadMore) {
                    canLoadMore = false;
                    presenter.doLoadMoreData();
                }
            }
        });
    }

    @Override
    public void fetchData() {
        super.fetchData();
        onLoadData();
    }

    @Override
    public void setPresenter(INewsArticle.Presenter persenter) {
        if (null == presenter) {
            this.presenter = new NewsArticlePresenter(this);
        }
    }

    @Override
    public void onSetAdapter(List<?> list) {
        Items newItems = new Items(list);
        newItems.add(new LoadingBean());
        DiffCallback.create(oldItems, newItems, adapter);
        oldItems.clear();
        oldItems.addAll(newItems);
        canLoadMore = true;
        /**
         * https://medium.com/@hanru.yeh/recyclerview-and-appbarlayout-behavior-changed-in-v26-0-x-d9eb4de78fc0
         * support libraries v26 增加了 RV 惯性滑动，当 root layout 使用了 AppBarLayout Behavior 就会自动生效
         * 因此需要手动停止滑动
         */
        rvFragment.stopScroll();
    }

    @Override
    public void onLoadData() {
        onShowLoading();
        presenter.doLoadData(categoryId);
    }

    @Override
    protected void initData() throws NullPointerException {
        categoryId = getArguments().getString(TAG);
    }
}
