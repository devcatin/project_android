package com.erik.android.androidlean.ohter;

import android.view.GestureDetector;
import android.view.MotionEvent;

public class BGestureListener implements GestureDetector.OnGestureListener {

    private final static String TAG = "MyGesture";

    /*
    @Override
    public boolean onDown(MotionEvent event) {
        Log.d(TAG, "onDown:按下");
        return false;
    }

    @Override
    public void onShowPress(MotionEvent event) {
        Log.d(TAG, "onShowPress:手指按下一段时间,不过还没到长按");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent event) {
        Log.d(TAG, "onSingleTapUp:手指离开屏幕的一瞬间");
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent event1, MotionEvent event2, float v1, float v2) {
        Log.d(TAG, "onScroll:在触摸屏上滑动");
        return false;
    }

    @Override
    public void onLongPress(MotionEvent event) {
        Log.d(TAG, "onLongPress:长按并且没有松开");
    }

    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2, float v1, float v2) {
        Log.d(TAG, "onFling:迅速滑动,并松开");
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onContextClick(MotionEvent e) {
        return false;
    }*/


    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    public void onLongPress(MotionEvent e) {

    }

    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    public void onShowPress(MotionEvent e) {

    }

    public boolean onDown(MotionEvent e) {
        return false;
    }

    public boolean onDoubleTap(MotionEvent e) {
        return false;
    }

    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    public boolean onContextClick(MotionEvent e) {
        return false;
    }


}
