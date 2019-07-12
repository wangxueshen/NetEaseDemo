package com.tengxincar.mobile.neteasedemo;

import android.app.Application;

import com.tengxincar.mobile.arouter.ARouter;

/**
 * Created by wxs on 2019/7/12.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.getInstance().init(this);
    }
}
