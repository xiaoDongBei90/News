package com.lxw.news.module.base;

import com.trello.rxlifecycle2.LifecycleTransformer;

import java.util.List;

/**
 * author  LiXiaoWei
 * date  2018/1/31.
 * desc:
 */

public interface IBaseListView<T> extends IBaseView<T> {
    @Override
    void onShowLoading();

    @Override
    void onHideLoading();

    @Override
    void onShowNetError();

    @Override
    void setPresenter(T persenter);

    /**
     * 绑定生命周期
     */
    @Override
    <T> LifecycleTransformer<T> bindToLife();

    /**
     * 设置适配器
     */
    void onSetAdapter(List<?> list);

    /**
     * 加载完毕
     */
    void onShowNoMore();
}
