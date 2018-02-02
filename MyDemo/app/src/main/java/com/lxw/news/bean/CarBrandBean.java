package com.lxw.news.bean;

/**
 * Created by YongLiu on 2017/12/4.
 */

public class CarBrandBean {
    private String id;
    private String name;
    private String code;
    private String hot;
    private String logoUrl;
    private String category;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setHot(String hot) {
        this.hot = hot;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getHot() {
        return hot;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public String getCategory() {
        return category;
    }
}
