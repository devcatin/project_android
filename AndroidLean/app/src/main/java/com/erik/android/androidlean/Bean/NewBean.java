package com.erik.android.androidlean.Bean;

import java.io.Serializable;

public class NewBean implements Serializable {

    private String new_title;
    private String new_content;

    public NewBean(String title, String content) {
        this.new_title = title;
        this.new_content = content;
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
