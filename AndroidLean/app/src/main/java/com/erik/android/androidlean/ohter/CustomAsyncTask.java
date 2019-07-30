package com.erik.android.androidlean.ohter;

import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;

public class CustomAsyncTask extends AsyncTask<Integer, Integer, String> {

    private TextView txt;
    private ProgressBar pgbar;

    public class DelayOperator {
        //延时操作,用来模拟下载
        public void delay() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public CustomAsyncTask(TextView textView, ProgressBar progressBar) {
        super();
        this.txt = textView;
        this.pgbar = progressBar;
    }

    @Override
    protected String doInBackground(Integer... params) {
        DelayOperator dop = new DelayOperator();
        int i = 0;
        for (i = 10; i <= 100; i++) {
            dop.delay();
            publishProgress(i);
        }
        return i + params[0].intValue() + "";
    }

    @Override
    protected void onPreExecute() {
        txt.setText("开始执行异步线程~");
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        int value = values[0];
        pgbar.setProgress(value);
    }

}
