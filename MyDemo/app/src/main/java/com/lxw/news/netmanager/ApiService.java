package com.lxw.news.netmanager;

import com.lxw.news.Constant;
import com.lxw.news.bean.joke.JokeCommentBean;
import com.lxw.news.bean.joke.JokeContentBean;
import com.lxw.news.bean.news.MultiNewsArticleBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * author  LiXiaoWei
 * date  2018/1/22.
 * desc:
 */

public interface ApiService {

    /**
     * 获取段子正文内容
     * http://www.toutiao.com/api/article/feed/?category=essay_joke&as=A115C8457F69B85&cp=585F294B8845EE1
     */
    @GET("api/article/feed/?category=essay_joke")
    Observable<JokeContentBean> getJokeContent(@Query("max_behot_time") String maxBehotTime, @Query("as") String as, @Query("cp") String cp);

    /**
     * 获取段子评论
     * http://m.neihanshequ.com/api/get_essay_comments/?group_id=编号&count=数量&offset=偏移量
     */
    @GET("http://m.neihanshequ.com/api/get_essay_comments/?count=20")
    @Headers({"User-Agent:" + Constant.USER_AGENT_MOBILE})
    Observable<JokeCommentBean> getJokeComment(
            @Query("group_id") String groupId,
            @Query("offset") int offset);

    @GET("http://is.snssdk.com/api/news/feed/v62/?iid=5034850950&device_id=6096495334&refer=1&count=20&aid=13")
    Observable<MultiNewsArticleBean> getNewsArticle(
            @Query("category") String category,
            @Query("max_behot_time") String maxBehotTime);

    @GET("http://lf.snssdk.com/api/news/feed/v62/?iid=12507202490&device_id=37487219424&refer=1&count=20&aid=13")
    Observable<MultiNewsArticleBean> getNewsArticle2(
            @Query("category") String category,
            @Query("max_behot_time") String maxBehotTime);
}
