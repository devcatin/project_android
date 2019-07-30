package com.erik.utilslibrary;

import java.net.HttpCookie;
import java.util.List;

import okhttp3.Headers;

public interface Response<T> {

    String url();

    RequestMethod requestMethod();

    boolean isSucceed();

    boolean isFromCache();

    Headers getHeaders();

    List<HttpCookie> getCookies();

    byte[] getBytes();

    T get();

    Exception getException();

    Object getTag();

    long getNetworkMillis();

}
