package com.tengxincar.mobile.neteasedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.tengxincar.mobile.annotations.Subscrible;
import com.tengxincar.mobile.annotations.ThreadMode;
import com.tengxincar.mobile.neteasedemo.eventBus.EventBusBean;
import com.tengxincar.mobile.neteasedemo.utils.EventBus;

/**
 * eventBus
 *
 * @author wxs
 * @date 2019/7/13
 */
public class EventBusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus);

        EventBus.getInstance().register(this);

        startActivity(new Intent(this, EventBusSecondActivity.class));

    }

    //这个方法放到eventBus里，不是所有的方法，而是带有注解的方法
    @Subscrible(threadMode = ThreadMode.MIAN)
    public void testEventBus(EventBusBean resultMessage) {
        Log.e("mian--------->", resultMessage.toString());
    }

}
