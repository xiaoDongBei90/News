package com.lxw.news.util;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * author  LiXiaoWei
 * date  2018/2/1.
 * desc:
 */

public abstract class onLoadMoreListener extends RecyclerView.OnScrollListener {

    private LinearLayoutManager layoutManager;
    private int itemCount, lastPosition, lastItemCount;

    public abstract void onLoadMore();

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
            layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            itemCount = layoutManager.getItemCount();
            lastPosition = layoutManager.findLastCompletelyVisibleItemPosition();

            if (lastItemCount != itemCount && lastPosition == itemCount - 1) {
                lastItemCount = itemCount;
                this.onLoadMore();
            }
        }
    }
}
