package com.erik.android.androidlean.bean;

public class MessageEvent {

    public final String message;

    public static MessageEvent getInstance(String message) {
        return new MessageEvent(message);
    }

    private MessageEvent(String message) {
        this.message = message;
    }
}
