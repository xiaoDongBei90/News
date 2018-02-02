package com.lxw.news.bean;

import java.util.List;
import java.util.Map;

/**
 * Created by YongLiu on 2017/9/21.
 */

public class BrandInfo {
    private Map<String, List<CarBrandBean>> brandMap;
    private List<String> keyList;

    public Map<String, List<CarBrandBean>> getBrandMap() {
        return brandMap;
    }


    public List<String> getKeyList() {
        return keyList;
    }

}
