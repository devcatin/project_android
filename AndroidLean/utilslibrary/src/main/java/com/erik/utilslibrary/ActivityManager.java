package com.erik.utilslibrary;

import android.app.Activity;

import java.util.Stack;

public class ActivityManager {

    private static ActivityManager activityManager;
    private Stack<Activity> activities;

    public static ActivityManager getActivity() {
        if (activityManager == null) {
            synchronized (ActivityManager.class) {
                if (activityManager == null) {
                    activityManager = new ActivityManager();
                }
            }
        }
        return activityManager;
    }

    //Activity集合
    private Stack<Activity> activityStack() {
        if (activities == null) {
            activities = new Stack<>();
        }
        return activities;
    }

    //获取下一个Activity
    private Activity activityStackLastElement() {
        try {
            return activityStack().lastElement();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //添加Activity
    public void add(Activity activity) {
        activityStack().add(activity);
    }

    //获取当前Activity
    public Activity get() {
        Activity activity = activityStackLastElement();
        return activity;
    }

    //获取指定Activity
    public Activity get(Class cla) {
        for (Activity activity : activityStack()) {
            if (activity.getClass().equals(cla)) {
                return activity;
            }
        }
        return null;
    }

    //移除当前Activity
    public void remove() {
        Activity activity = activityStackLastElement();
        remove(activity);
    }

    //移除指定Activity
    public void remove(Activity activity) {
        if (activity != null) {
            activityStack().remove(activity);
        }
    }

    //关闭当前Activity
    public void finish() {
        Activity activity = activityStackLastElement();
        finish(activity);
    }

    //关闭指定Activity
    public void finish(Activity activity) {
        if (activity != null) {
            activityStack().remove(activity);
            activity.finish();
        }
    }

    //关闭指定界面
    public void finish(Class cla) {
        for (Activity activity : activityStack() ) {
            if (activity.getClass().equals(cla)) {
                finish(activity);
                break;
            }
        }
    }

    //逐层关闭到指定界面
    public void finishExceptOne(Class cla) {
        while (true) {
            Activity activity = activityStackLastElement();
            if (activity == null) {
                break;
            }
            finish(activity);
        }
    }

    //关闭所有页面
    public void finishAll() {
        while (true) {
            Activity activity = activityStackLastElement();
            if (activity == null) {
                break;
            }
            if (!activity.getClass().getName().contains("MainActivity")) {
                finish(activity);
            } else {
                if (null != activities && activities.size() == 1) {
                    break;
                }
            }
        }
    }

}
