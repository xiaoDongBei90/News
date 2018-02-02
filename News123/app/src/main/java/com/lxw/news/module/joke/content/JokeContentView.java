package com.lxw.news.module.joke.content;

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
 * date  2018/2/1.
 * desc:
 */

public class JokeContentView extends BaseListFragment<IJokeContent.Presenter> implements IJokeContent.View {
    public static JokeContentView newInstance() {
        return new JokeContentView();
    }

    @Override
    public void fetchData() {
        onLoadData();
    }

    @Override
    public void setPresenter(IJokeContent.Presenter persenter) {
        if (null == presenter) {
            this.presenter = new JokeContentPresenter(this);
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
        rvFragment.stopScroll();
    }

    @Override
    public void onLoadData() {
        onShowLoading();
        presenter.doLoadData();
    }


    @Override
    protected void initData() throws NullPointerException {

    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        adapter = new MultiTypeAdapter(oldItems);
        Register.registerJokeContentItem(adapter);
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
}
