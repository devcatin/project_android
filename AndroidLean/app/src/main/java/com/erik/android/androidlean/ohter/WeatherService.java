package com.erik.android.androidlean.ohter;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.erik.android.androidlean.R;
import com.erik.android.androidlean.activity.MainActivity;

public class WeatherService extends Service {

    private static final int NOTIFY_ID = 123;

    private Binder mBinder;

    @Override
    public IBinder onBind(Intent intent) {
        if (mBinder != null) {
            return mBinder;
        }
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        showNotificatin();
    }

    private void showNotificatin() {
        NotificationCompat.Builder mbuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.weather)
                .setContentTitle("晴转多云 22℃")
                .setContentText("上海市浦东新区")
                .setAutoCancel(true)
                .setWhen(System.currentTimeMillis());

        Intent intent = new Intent(this, MainActivity.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(intent);
        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        mbuilder.setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        final Notification notification = mbuilder.build();
        notificationManager.notify(NOTIFY_ID, notification);
        startForeground(NOTIFY_ID, notification);
    }
}
