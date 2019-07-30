package com.erik.utilslibrary;

import android.text.TextUtils;
import android.util.Log;

public class Result {

    public static final String TAG = "Result";

    private String status;
    private String data;
    private String msg;
    private String json;

    //分页数据
    private int pageIndex;//当前页
    private int pageSize;//每页条数
    private int totalPage;//总页数
    private int totalSize;//总条数

    public boolean isSuccess() {
        return ReqCode.SUCCESS.equals(status);
    }

    public void printErrorLog() {
        if (!TextUtils.isEmpty(msg)) {
            Log.i(TAG, msg);
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

}
