package com.lxw.news;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.lxw.news.module.base.BaseActivity;
import com.lxw.news.module.media.MediaChannelView;
import com.lxw.news.module.news.NewsTabLayout;
import com.lxw.news.module.photo.PhotoTabLayout;
import com.lxw.news.module.video.VideoTabLayout;
import com.lxw.news.widget.helper.BottomNavigationViewHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottom_navigation;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer_layout;
    @BindView(R.id.nav_view)
    NavigationView nav_view;
    private NewsTabLayout newsTabLayout;
    private PhotoTabLayout photoTabLayout;
    private VideoTabLayout videoTabLayout;
    private MediaChannelView mediaChannelView;
    private int position;
    private static final int FRAGMENT_NEWS = 0;
    private static final int FRAGMENT_PHOTO = 1;
    private static final int FRAGMENT_VIDEO = 2;
    private static final int FRAGMENT_MEDIA = 3;
    private long exitTime = 0;
    private Unbinder bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bind = ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        toolbar.inflateMenu(R.menu.menu_activity_main);
        BottomNavigationViewHelper.disableShiftMode(bottom_navigation);
        showFragment(FRAGMENT_NEWS);
        bottom_navigation.setSelectedItemId(R.id.action_news);
        bottom_navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_news:
                        showFragment(FRAGMENT_NEWS);
                        break;
                    case R.id.action_photo:
                        showFragment(FRAGMENT_PHOTO);
                        break;
                    case R.id.action_video:
                        showFragment(FRAGMENT_VIDEO);
                        break;
                    case R.id.action_media:
                        showFragment(FRAGMENT_MEDIA);
                        break;
                    default:
                        break;

                }
                return true;
            }
        });
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.nav_drawer_open, R.string.nav_drawer_close);
        drawer_layout.addDrawerListener(toggle);
        toggle.syncState();
        nav_view.setNavigationItemSelectedListener(this);
    }

    private void showFragment(int index) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        hideFragment(ft);
        position = index;
        switch (index) {
            case FRAGMENT_NEWS:
                toolbar.setTitle(getString(R.string.title_news));
                if (newsTabLayout == null) {
                    newsTabLayout = new NewsTabLayout();
                    ft.add(R.id.container, newsTabLayout, NewsTabLayout.class.getName());
                } else {
                    ft.show(newsTabLayout);
                }
                break;
            case FRAGMENT_PHOTO:
                toolbar.setTitle(getString(R.string.title_photo));
                if (photoTabLayout == null) {
                    photoTabLayout = new PhotoTabLayout();
                    ft.add(R.id.container, photoTabLayout, PhotoTabLayout.class.getName());
                } else {
                    ft.show(photoTabLayout);
                }
                break;
            case FRAGMENT_VIDEO:
                toolbar.setTitle(getString(R.string.title_video));
                if (videoTabLayout == null) {
                    videoTabLayout = new VideoTabLayout();
                    ft.add(R.id.container, videoTabLayout, VideoTabLayout.class.getName());
                } else {
                    ft.show(videoTabLayout);
                }
                break;
            case FRAGMENT_MEDIA:
                toolbar.setTitle(getString(R.string.title_media));
                if (mediaChannelView == null) {
                    mediaChannelView = new MediaChannelView();
                    ft.add(R.id.container, mediaChannelView, MediaChannelView.class.getName());
                } else {
                    ft.show(mediaChannelView);
                }
                break;
            default:
                break;
        }
        ft.commit();
    }

    private void hideFragment(FragmentTransaction ft) {
        if (newsTabLayout != null) {
            ft.hide(newsTabLayout);
        }
        if (photoTabLayout != null) {
            ft.hide(photoTabLayout);
        }
        if (videoTabLayout != null) {
            ft.hide(videoTabLayout);
        }
        if (mediaChannelView != null) {
            ft.hide(mediaChannelView);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_switch_night_mode:
                Toast.makeText(this, "夜间模式", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_setting:
                Toast.makeText(this, "设置", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_share:
                Toast.makeText(this, "分享", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - exitTime < 1000) {
            super.onBackPressed();
        } else {
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            exitTime = currentTime;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_search) {
            Toast.makeText(this, "打开搜索页面", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
