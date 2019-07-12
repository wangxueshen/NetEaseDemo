package com.tengxincar.mobile.personalmodule;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tengxincar.mobile.annotations.BindPath;

@BindPath("personalmodule/personalmodule")
public class PersonalModuleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_module);
    }
}
