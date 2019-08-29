package com.erik.android.androidlean.bean;

import java.util.ArrayList;
import java.util.List;

public class ComBean {

    private String type;
    List<BannerBean> banners = new ArrayList<>();
    List<ClassBean> classes = new ArrayList<>();
    List<LiveBean> lives = new ArrayList<>();

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

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

    public List<LiveBean> getLives() {
        return lives;
    }

    public void setLives(List<LiveBean> lives) {
        this.lives = lives;
    }

}
