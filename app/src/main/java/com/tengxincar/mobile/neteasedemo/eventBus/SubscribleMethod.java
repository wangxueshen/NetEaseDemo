package com.tengxincar.mobile.neteasedemo.eventBus;

import com.tengxincar.mobile.annotations.ThreadMode;

import java.lang.reflect.Method;

/**
 * Created by wxs on 2019/7/13.
 */
public class SubscribleMethod {

    private Method method;//方法
    private ThreadMode mThreadMode;//线程模式
    private Class<?> type;//返回值类型


    public SubscribleMethod(Method method, ThreadMode mThreadMode, Class<?> type) {
        this.method = method;
        this.mThreadMode = mThreadMode;
        this.type = type;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public ThreadMode getmThreadMode() {
        return mThreadMode;
    }

    public void setmThreadMode(ThreadMode mThreadMode) {
        this.mThreadMode = mThreadMode;
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }
}
