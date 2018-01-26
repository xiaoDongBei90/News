package com.lxw.mydemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.lxw.mydemo.bean.BrandInfo;
import com.lxw.mydemo.bean.ObjectResult;
import com.lxw.mydemo.netmanager.RetrofitFactory;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_test1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData1();
            }

        });
    }

    private void getData1() {
        Log.d("testkk", "点击了-------------");
        RetrofitFactory.getRetrofit().getBrandData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ObjectResult<BrandInfo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("testkk", "onSubscribe-------------" + d);
                    }

                    @Override
                    public void onNext(ObjectResult<BrandInfo> brandInfoObjectResult) {
                        int result = brandInfoObjectResult.getResult();
                        BrandInfo data = brandInfoObjectResult.getData();
                        Log.d("testkk", "onNext-------------" + brandInfoObjectResult);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("testkk", "onError-------------" + e);
                    }

                    @Override
                    public void onComplete() {
                        Log.d("testkk", "onComplete-------------");
                    }
                });
    }
}
