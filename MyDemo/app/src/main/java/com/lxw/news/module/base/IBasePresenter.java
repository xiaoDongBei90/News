package com.lxw.news.module.base;

/**
 * author  LiXiaoWei
 * date  2018/1/31.
 * desc:
 */

public interface IBasePresenter {
    /**
     * 刷新数据
     */
    void doRefresh();

    /**
     * 显示网络错误
     */
    void doShowNetError();
}
