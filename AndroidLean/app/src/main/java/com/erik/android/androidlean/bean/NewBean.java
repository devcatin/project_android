package com.erik.android.androidlean.bean;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.SerializationService;
import com.alibaba.fastjson.JSON;

import java.lang.reflect.Type;

//@Route(path = "/service/json")
public class NewBean {

    private String new_icon;
    private String new_title;
    private String new_content;

    public NewBean() {

    }

    public NewBean(String title, String content, String icon) {
        this.new_title = title;
        this.new_content = content;
        this.new_icon = icon;
    }

    /*@Override
    public void init(Context context) {

    }

    @Override
    public <T> T json2Object(String text, Class<T> clazz) {
        return JSON.parseObject(text, clazz);
    }

    @Override
    public String object2Json(Object object) {
        return JSON.toJSONString(object);
    }

    @Override
    public <T> T parseObject(String input, Type clazz) {
        return JSON.parseObject(input, clazz);
    }*/

    public String getNew_icon() {
        return new_icon;
    }

    public void setNew_icon(String new_icon) {
        this.new_icon = new_icon;
    }

    public String getNew_title() {
        return new_title;
    }

    public void setNew_title(String new_title) {
        this.new_title = new_title;
    }

    public String getNew_content() {
        return new_content;
    }

    public void setNew_content(String new_content) {
        this.new_content = new_content;
    }

}
