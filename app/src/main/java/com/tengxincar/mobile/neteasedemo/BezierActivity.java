package com.tengxincar.mobile.neteasedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tengxincar.mobile.neteasedemo.customView.BezierView;
/**
 *贝塞尔曲线
 *@author wxs
 *@date 2019/7/17
 */
public class BezierActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new BezierView(this));
    }
}
