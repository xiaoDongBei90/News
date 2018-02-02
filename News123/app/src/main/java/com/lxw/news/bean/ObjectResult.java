package com.lxw.news.bean;

/**
 * @ author  LiXiaoWei
 * @ date  2017/10/27.
 * desc:
 */

public class ObjectResult<K> extends BaseResult {
    private K data;

    public K getData() {
        return data;
    }
}
