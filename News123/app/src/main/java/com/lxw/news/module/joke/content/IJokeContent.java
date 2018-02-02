package com.lxw.news.module.joke.content;

import com.lxw.news.module.base.IBaseListView;
import com.lxw.news.module.base.IBasePresenter;

/**
 * author  LiXiaoWei
 * date  2018/1/31.
 * desc:
 */

public interface IJokeContent {
    interface View extends IBaseListView<Presenter> {

        /**
         * 请求数据
         */
        void onLoadData();
    }

    interface Presenter extends IBasePresenter {

        /**
         * 请求数据
         */
        void doLoadData();

        /**
         * 再起请求数据
         */
        void doLoadMoreData();

        /**
         * 设置适配器
         */
        void doSetAdapter();

        void doShowNoMore();
    }
}
