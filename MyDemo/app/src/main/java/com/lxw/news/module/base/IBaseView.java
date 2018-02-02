package com.lxw.news.module.base;

import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * author  LiXiaoWei
 * date  2018/1/31.
 * desc:
 */

public interface IBaseView<T> {
    /**
     * 显示加载动画
     */
    void onShowLoading();

    /**
     * 隐藏加载
     */
    void onHideLoading();

    /**
     * 显示网络错误
     */
    void onShowNetError();

    /**
     * 设置 presenter
     */
    void setPresenter(T persenter);

    /**
     * 绑定生命周期
     */
    <T> LifecycleTransformer<T> bindToLife();
}
