package com.erik.utilslibrary;

import android.text.TextUtils;
import android.util.Log;


public abstract class ResultCallback implements OnResponseListener<String> {

    public abstract ResultCallback resultCallback();

    public static final String TAG = "ResultCallback";

    @Override
    public void onStart(int what) {
    }

    @Override
    public void onSucceed(int what, Response<String> response) {
        try {
            Result result = null;
            String resultStr = response.get();
            if (!TextUtils.isEmpty(resultStr)) {
                Log.i(TAG, resultStr);
            }
            if (result == null) {
                result = new Result();
                result.setStatus(ReqCode.NO_DATA);
                result.setMsg("数据异常");
            } else {
                result.setJson(resultStr);
            }
            onSuccess(what, result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            onFinished(what);
        }
    }

    @Override
    public void onFinished(int what) {

    }

    public abstract void onSuccess(int what, Result result);

    public abstract void onSuccessString(int what, String result);

}
