package com.erik.android.androidlean.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.erik.android.androidlean.R;
import com.erik.android.androidlean.view.NavigationBar;
import com.erik.utilslibrary.ActivityManager;
import com.erik.utilslibrary.FileDownload.FileDownloadered;

import java.io.File;
import java.util.Locale;

public class DownloadActivity extends BaseActivity {

    public EditText editPath;
    private Button btndown;
    private Button btnstop;
    private TextView textResult;
    public ProgressBar progressBar;
    private static final int PROCESSONG = 1;
    private static final int FAILURE = -1;

    private Handler handler = new UIHandler();

    private final class UIHandler extends Handler {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case PROCESSONG:
                    int size = msg.getData().getInt("size");
                    progressBar.setProgress(size);
                    float num = (float)progressBar.getProgress()/progressBar.getMax();
                    int result = (int)(num * 100);
                    String text = String.format(Locale.CHINA, "%d", result) + "%";
                    textResult.setText(text);
                    if (progressBar.getProgress() == progressBar.getMax()) {
                        Toast.makeText(getApplicationContext(), "文件下载成功", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case FAILURE:
                    Toast.makeText(getApplicationContext(), "文件下载失败", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        navigationBar();
        bindViews();
    }

    private void bindViews() {
        editPath = findViewById(R.id.editpath);
        String url = "http://ultravideo.cs.tut.fi/video/Bosphorus_3840x2160_120fps_420_10bit_YUV_RAW.7z";
        editPath.setText(url);
        btndown = findViewById(R.id.btndown);
        btnstop = findViewById(R.id.btnstop);
        textResult = findViewById(R.id.textresult);
        progressBar = findViewById(R.id.progressBar);
        ButtonClickListener listener = new ButtonClickListener();
        btndown.setOnClickListener(listener);
        btnstop.setOnClickListener(listener);
    }

    private void navigationBar() {
        final NavigationBar navigationBar = findViewById(R.id.nav_bar);
        Intent intent = getIntent();
        String title = intent.getStringExtra("name");
        navigationBar.setTitleTextStr(title);
        navigationBar.setShowBackBtn(true);
        navigationBar.setBtnOnClickListener(new NavigationBar.ButtonOnClickListener() {
            @Override
            public void onBackClick() {
                Activity activity = ActivityManager.getActivity().get();
                activity.finish();
            }
            @Override
            public void onRightClick() {

            }
        });
    }

    private final class ButtonClickListener implements View.OnClickListener {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btndown:
                    String path = editPath.getText().toString();
                    if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                        File saveDir = Environment.getExternalStorageDirectory();
                        download(path, saveDir);
                    } else {
                        Toast.makeText(getApplicationContext(), "sd卡读取失败", Toast.LENGTH_SHORT).show();
                    }
                    btndown.setEnabled(false);
                    btnstop.setEnabled(true);
                    break;
                case R.id.btnstop:
                    exit();
                    btndown.setEnabled(true);
                    btnstop.setEnabled(false);
                    break;
            }
        }

        /*
    由于用户的输入事件(点击button, 触摸屏幕....)是由主线程负责处理的，如果主线程处于工作状态，
    此时用户产生的输入事件如果没能在5秒内得到处理，系统就会报“应用无响应”错误。
    所以在主线程里不能执行一件比较耗时的工作，否则会因主线程阻塞而无法处理用户的输入事件，
    导致“应用无响应”错误的出现。耗时的工作应该在子线程里执行。
     */
        private DownloadTask task;
        /**
         * 退出下载
         */
        public void exit() {
            if(task!=null) task.exit();
        }

        private void download(String path, File saveDir) {//运行在主线程
            task = new DownloadTask(path, saveDir);
            new Thread(task).start();
        }

        /*
         * UI控件画面的重绘(更新)是由主线程负责处理的，如果在子线程中更新UI控件的值，更新后的值不会重绘到屏幕上
         * 一定要在主线程里更新UI控件的值，这样才能在屏幕上显示出来，不能在子线程中更新UI控件的值
         */
        private final class DownloadTask implements Runnable {
            private String path;
            private File saveDir;
            private FileDownloadered loader;
            public DownloadTask(String path, File saveDir) {
                this.path = path;
                this.saveDir = saveDir;
            }
            /**
             * 退出下载
             */
            public void exit() {
                if(loader!=null) loader.exit();
            }

            public void run() {
                try {
                    loader = new FileDownloadered(getApplicationContext(), path, saveDir, 3);
                    progressBar.setMax(loader.getFileSize());//设置进度条的最大刻度
                    loader.download(new com.erik.utilslibrary.FileDownload.DownloadProgressListener() {
                        public void onDownloadSize(int size) {
                            Message msg = new Message();
                            msg.what = 1;
                            msg.getData().putInt("size", size);
                            handler.sendMessage(msg);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    handler.sendMessage(handler.obtainMessage(-1));
                }
            }
        }
    }
 }
