package com.erik.android.androidlean.ohter;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.erik.android.androidlean.activity.MainActivity;
import com.erik.android.androidlean.R;

public class TestService extends Service {

    private final String TAG = "TestService";

    private SimpleBinder mBinder;

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind方法被调用");
        if (mBinder != null) {
            return mBinder;
        }
        return null;
    }

    @Override
    public void onCreate() {
        Log.i(TAG, "onCreate方法被调用");
        super.onCreate();
        //mBinder = new SimpleBinder();
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentIntent(PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0));
        builder.setAutoCancel(false);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setTicker("Foreground Service Start");
        builder.setContentTitle("Socket服务端");
        builder.setContentText("正在运行...");
        startForeground(1, builder.getNotification());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand方法被调用");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy方法被调用");
        super.onDestroy();
    }

    class SimpleBinder extends Binder {

        public void doTask() {
            Log.d(TAG, "doTask");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //任务逻辑
                }
            }).start();
        }

    }

}
