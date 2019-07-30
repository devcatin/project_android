package com.erik.utilslibrary;

public enum  RequestMethod {

    GET("GET"),

    POST("POST"),

    PUT("PUT"),

    DELETE("DELETE"),

    HEAD("HEAD"),

    OPTIONS("OPTIONS"),

    TRACE("TRACE"),

    PATCH("PATCH");

    private final String value;

    RequestMethod(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
