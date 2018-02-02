package com.lxw.news.module.joke.content;

import com.lxw.news.Constant;
import com.lxw.news.ErrorAction;
import com.lxw.news.bean.joke.JokeContentBean;
import com.lxw.news.netmanager.RetrofitFactory;
import com.lxw.news.util.TimeUtil;
import com.lxw.news.util.ToutiaoUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * author  LiXiaoWei
 * date  2018/2/1.
 * desc:
 */

public class JokeContentPresenter implements IJokeContent.Presenter {
    private List<JokeContentBean.DataBean.GroupBean> groupList = new ArrayList<>();
    private IJokeContent.View view;
    private String time;

    public JokeContentPresenter(IJokeContent.View view) {
        this.view = view;
        this.time = TimeUtil.getCurrentTimeStamp();
    }

    @Override
    public void doRefresh() {
        if (groupList.size() != 0) {
            groupList.clear();
        }
        view.onShowLoading();
        doLoadData();
    }

    @Override
    public void doShowNetError() {
        view.onHideLoading();
        view.onShowNetError();
    }

    @Override
    public void doLoadData() {
        Map<String, String> asCpMap = ToutiaoUtil.getAsCp();
        RetrofitFactory.getRetrofit().getJokeContent(time, asCpMap.get(Constant.AS), asCpMap.get(Constant.CP))
                .subscribeOn(Schedulers.io())
                .map(new Function<JokeContentBean, List<JokeContentBean.DataBean.GroupBean>>() {
                    @Override
                    public List<JokeContentBean.DataBean.GroupBean> apply(JokeContentBean jokeContentBean) throws Exception {
                        List<JokeContentBean.DataBean> data = jokeContentBean.getData();
                        for (JokeContentBean.DataBean dataBean : data) {
                            groupList.add(dataBean.getGroup());
                        }
                        time = jokeContentBean.getNext().getMax_behot_time() + "";
                        return groupList;
                    }
                }).compose(view.<List<JokeContentBean.DataBean.GroupBean>>bindToLife())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<JokeContentBean.DataBean.GroupBean>>() {
                    @Override
                    public void accept(List<JokeContentBean.DataBean.GroupBean> groupBeans) throws Exception {
                        if (groupBeans.size() > 0) {
                            doSetAdapter();
                        } else {
                            doShowNoMore();
                        }
                    }
                }, ErrorAction.error());
    }

    @Override
    public void doLoadMoreData() {
        doLoadData();
    }

    @Override
    public void doSetAdapter() {
        view.onSetAdapter(groupList);
        view.onHideLoading();
    }

    @Override
    public void doShowNoMore() {
        view.onHideLoading();
        view.onShowNoMore();
    }
}
