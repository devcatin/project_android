package com.erik.android.androidlean.bean;

import java.util.ArrayList;
import java.util.List;

public class HomeBean {

    List<BannerBean> banners = new ArrayList<>();
    List<ClassBean> classes = new ArrayList<>();

    public List<BannerBean> getBanners() {
        return banners;
    }

    public void setBanners(List<BannerBean> banners) {
        this.banners = banners;
    }

    public List<ClassBean> getClasses() {
        return classes;
    }

    public void setClasses(List<ClassBean> classes) {
        this.classes = classes;
    }

}
