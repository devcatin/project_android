package com.erik.utilslibrary;

public interface OnResponseListener <T> {

    void onStart(int what);

    void onSucceed(int what, Response<T> response);

    void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis);

    void onFinished(int what);

}
