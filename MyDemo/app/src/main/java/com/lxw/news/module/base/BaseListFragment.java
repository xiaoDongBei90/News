package com.lxw.news.module.base;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.lxw.news.R;
import com.lxw.news.bean.LoadingEndBean;
import com.lxw.news.util.RxBus;
import com.lxw.news.util.SettingUtil;

import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * author  LiXiaoWei
 * date  2018/1/31.
 * desc:
 */

public abstract class BaseListFragment<T extends IBasePresenter> extends LazyLoadFragment<T> implements IBaseListView<T>, SwipeRefreshLayout.OnRefreshListener {
    protected RecyclerView rvFragment;
    protected SwipeRefreshLayout refreshLayout;
    protected MultiTypeAdapter adapter;
    private Unbinder bind;
    public static final String TAG = "BaseListFragment";
    protected Observable<Integer> observable;
    protected boolean canLoadMore = false;
    protected Items oldItems = new Items();

    @Override
    protected int attachLayoutId() {
        return R.layout.fragment_list;
    }

    @Override
    protected void initView(View view) {
        rvFragment = view.findViewById(R.id.recycler_view);
        refreshLayout = view.findViewById(R.id.refresh_layout);
        rvFragment.setHasFixedSize(true);
        refreshLayout.setColorSchemeColors(getResources().getColor(R.color.Green));
        refreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onShowLoading() {
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(true);
            }
        });
    }

    @Override
    public void onHideLoading() {
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void fetchData() {
        observable = RxBus.getInstance().register(BaseListFragment.TAG);
        observable.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onShowNetError() {
        Toast.makeText(getActivity(), "网络不给力", Toast.LENGTH_SHORT).show();
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.setItems(new Items());
                adapter.notifyDataSetChanged();
                canLoadMore = false;
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshLayout.setColorSchemeColors(SettingUtil.getInstance().getColor());
    }

    @Override
    public void onShowNoMore() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (oldItems.size() > 0) {
                    Items newItems = new Items(oldItems);
                    newItems.remove(newItems.size() - 1);
                    newItems.add(new LoadingEndBean());
                    adapter.setItems(newItems);
                    adapter.notifyDataSetChanged();
                } else if (oldItems.size() == 0) {
                    oldItems.add(new LoadingEndBean());
                    adapter.setItems(oldItems);
                    adapter.notifyDataSetChanged();
                }
                canLoadMore = false;
            }
        });
    }

    @Override
    public void onRefresh() {
        int firstVisibleItemPosition = ((LinearLayoutManager) rvFragment.getLayoutManager()).findFirstVisibleItemPosition();
        if (firstVisibleItemPosition == 0) {
            presenter.doRefresh();
            return;
        }
        rvFragment.scrollToPosition(5);
        rvFragment.smoothScrollToPosition(0);
    }

    @Override
    public void onDestroy() {
        RxBus.getInstance().unregister(BaseListFragment.TAG, observable);
        super.onDestroy();
    }
}
