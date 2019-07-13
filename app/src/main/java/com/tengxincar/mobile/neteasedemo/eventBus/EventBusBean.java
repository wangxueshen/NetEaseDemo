package com.tengxincar.mobile.neteasedemo.eventBus;

import java.io.Serializable;

/**
 * Created by wxs on 2019/7/13.
 * EventResult传递的bean
 */
public class EventBusBean implements Serializable {
    private String result;
    private String message;

    public EventBusBean(String result, String message) {
        this.result = result;
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "EventBusBean{" +
                "result='" + result + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}

