package com.erik.android.androidlean.tool;

public class BGTools {

    private static volatile BGTools singleton = null;

    private BGTools() {}

    public static BGTools getSingleton() {
        if (singleton == null) {
            synchronized (BGTools.class) {
                if (singleton == null) {
                    singleton = new BGTools();
                }
            }
        }
        return singleton;
    }
}
