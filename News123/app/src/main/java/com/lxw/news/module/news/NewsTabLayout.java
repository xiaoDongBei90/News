package com.lxw.news.module.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.lxw.news.Constant;
import com.lxw.news.R;
import com.lxw.news.adapter.base.BasePagerAdapter;
import com.lxw.news.bean.news.NewsChannelBean;
import com.lxw.news.database.dao.NewsChannelDao;
import com.lxw.news.module.joke.content.JokeContentView;
import com.lxw.news.module.news.article.NewsArticleView;
import com.lxw.news.util.RxBus;
import com.lxw.news.util.SettingUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * author  LiXiaoWei
 * date  2018/1/29.
 * desc:
 */

public class NewsTabLayout extends Fragment {
    public static final String TAG = "NewsTabLayout";
    @BindView(R.id.tab_layout_news)
    TabLayout tabLayout;
    @BindView(R.id.view_pager_news)
    ViewPager viewPager;
    @BindView(R.id.add_channel_iv)
    ImageView ivAddChannel;
    @BindView(R.id.header_layout)
    View headerLayout;
    private Unbinder bind;
    private List<Fragment> fragmentList;
    private List<String> titleList;
    private Map<String, Fragment> map = new HashMap<>();
    private BasePagerAdapter adapter;
    private Observable<Boolean> observable;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_tab, container, false);
        bind = ButterKnife.bind(this, view);
        initView(view);
        initData();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        headerLayout.setBackgroundColor(SettingUtil.getInstance().getColor());
    }

    private void initView(View view) {
        tabLayout.setupWithViewPager(viewPager);
        ivAddChannel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(NewsTabLayout.this.getActivity(), "增加频道页面", Toast.LENGTH_SHORT).show();
            }
        });
        headerLayout.setBackgroundColor(SettingUtil.getInstance().getColor());
    }

    private void initData() {
        initTabs();
        adapter = new BasePagerAdapter(getChildFragmentManager(), fragmentList, titleList);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(15);
        observable = RxBus.getInstance().register(NewsTabLayout.TAG);
        observable.subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean isRefresh) throws Exception {
                if (isRefresh) {
                    initTabs();
                    adapter.recreateItems(fragmentList, titleList);
                }
            }
        });
    }

    private void initTabs() {
        NewsChannelDao newsChannelDao = new NewsChannelDao();
        List<NewsChannelBean> channelList = newsChannelDao.query(1);
        fragmentList = new ArrayList<>();
        titleList = new ArrayList<>();
        if (channelList.size() == 0) {
            newsChannelDao.addInitData();
            channelList = newsChannelDao.query(Constant.NEWS_CHANNEL_ENABLE);
        }
        for (NewsChannelBean bean : channelList) {
            Fragment fragment = null;
            String channelId = bean.getChannelId();
            switch (channelId) {
                case "essay_joke":
                    if (map.containsKey(channelId)) {
                        fragmentList.add(map.get(channelId));
                    } else {
                        fragment = JokeContentView.newInstance();
                        fragmentList.add(fragment);
                    }
                    break;
                default:
                    if (map.containsKey(channelId)) {
                        fragmentList.add(map.get(channelId));
                    } else {
                        fragment = NewsArticleView.newInstance(channelId);
                        fragmentList.add(fragment);
                    }
                    break;
            }
            titleList.add(bean.getChannelName());
            if (fragment != null) {
                map.put(channelId, fragment);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
