package com.tengxincar.mobile.neteasedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tengxincar.mobile.neteasedemo.utils.TXDensity;

public class DensityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TXDensity.setDensity(getApplication(), this);
        setContentView(R.layout.activity_density);
    }
}
