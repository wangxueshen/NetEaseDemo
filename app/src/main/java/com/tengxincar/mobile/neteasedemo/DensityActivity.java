package com.tengxincar.mobile.neteasedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.tengxincar.mobile.neteasedemo.utils.TXDensity;

/**
 * 自定义Density来适配
 *
 * @author wxs
 * @date 2019/7/12
 */
public class DensityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TXDensity.setDensity(getApplication(), this);
        setContentView(R.layout.activity_density);
    }
}
