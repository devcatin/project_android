package com.erik.android.androidlean.Ohters;

import java.util.HashMap;

public class TestSingleton {

    private static TestSingleton instance = null;
    final HashMap<String, Object> mMap;

    private TestSingleton() {
        mMap = new HashMap<String, Object>();
    }

    public void put(String key, Object value) {
        mMap.put(key, value);
    }

    public Object get(String key) {
        return mMap.get(key);
    }

    public synchronized static TestSingleton getInstance() {
        if (instance == null) {
            instance = new TestSingleton();
        }
        return instance;
    }

}
