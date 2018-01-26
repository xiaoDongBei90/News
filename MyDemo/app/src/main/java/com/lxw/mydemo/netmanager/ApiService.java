package com.lxw.mydemo.netmanager;

import com.lxw.mydemo.bean.BrandInfo;
import com.lxw.mydemo.bean.ObjectResult;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * author  LiXiaoWei
 * date  2018/1/22.
 * desc:
 */

public interface ApiService {
    @GET("item/carBrand/map")
    Observable<ObjectResult<BrandInfo>> getBrandData();
}
