package com.tengxincar.mobile.neteasedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.tengxincar.mobile.neteasedemo.eventBus.EventBusBean;
import com.tengxincar.mobile.neteasedemo.utils.EventBus;

/**
 *
 *@author wxs
 *@date 2019/7/13
 */
public class EventBusSecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus_second);
    }

    public void jumpToEventBus(View view) {
        EventBus.getInstance().post(new EventBusBean("test1","test2"));
    }
}
