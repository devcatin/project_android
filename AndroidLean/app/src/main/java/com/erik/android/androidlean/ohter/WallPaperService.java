package com.erik.android.androidlean.ohter;

import android.app.Service;
import android.app.WallpaperManager;
import android.content.Intent;
import android.os.IBinder;

import com.erik.android.androidlean.R;

public class WallPaperService extends Service {

    private int current = 0;
    private int[] papers = new int[]{R.drawable.wallpaper1,R.drawable.wallpaper2,R.drawable.wallpaper3};
    private WallpaperManager manager = null;

    @Override
    public void onCreate() {
        super.onCreate();
        manager = WallpaperManager.getInstance(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (current >= 3) {
            current = 0;
        }
        try {
            manager.setResource(papers[current++]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }





}
