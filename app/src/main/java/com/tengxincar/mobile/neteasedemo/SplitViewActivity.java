package com.tengxincar.mobile.neteasedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tengxincar.mobile.neteasedemo.utils.SplitView;
/**
 *粒子爆炸动画
 *@author wxs
 *@date 2019/7/12
 */
public class SplitViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new SplitView(this));
    }
}
