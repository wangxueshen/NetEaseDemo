package com.tengxincar.mobile.neteasedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.tengxincar.mobile.arouter.ARouter;

public class CustomARouterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_arouter);


    }

    public void jumpLoginActivity(View view) {
        ARouter.getInstance().jumpToActivity("loginmodle/LoginModule", null);
    }
}
