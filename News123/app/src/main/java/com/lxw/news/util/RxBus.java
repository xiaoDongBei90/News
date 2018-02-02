package com.lxw.news.util;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Nonnull;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * author  LiXiaoWei
 * date  2018/1/31.
 * desc:
 */

public class RxBus {
    private static RxBus instance = new RxBus();
    private ConcurrentHashMap<Object, List<Subject>> subjectMapper = new ConcurrentHashMap<>();

    public static RxBus getInstance() {
        return instance;
    }

    public <T> Observable<T> register(@Nonnull Class<T> clz) {
        return register(clz.getName());
    }

    public <T> Observable<T> register(@Nonnull Object tag) {
        List<Subject> subjectList = subjectMapper.get(tag);
        if (null == subjectList) {
            subjectList = new ArrayList<>();
            subjectMapper.put(tag, subjectList);
        }
        Subject<T> sub = PublishSubject.create();
        subjectList.add(sub);
        return sub;
    }
    public <T> void unregister(@NonNull Class<T> clz, @NonNull Observable observable) {
        unregister(clz.getName(), observable);
    }

    public void unregister(@NonNull Object tag, @NonNull Observable observable) {
        List<Subject> subjects = subjectMapper.get(tag);
        if (null != subjects) {
            subjects.remove(observable);
            if (subjects.isEmpty()) {
                subjectMapper.remove(tag);
                //System.out.println("从rxbus取消注册");
            }
        }
    }
    public void post(@NonNull Object content) {
        post(content.getClass().getName(), content);
    }

    public void post(@NonNull Object tag, @NonNull Object content) {
        List<Subject> subjects = subjectMapper.get(tag);
        if (!subjects.isEmpty()) {
            for (Subject subject : subjects) {
                subject.onNext(content);
            }
        }
    }

}
