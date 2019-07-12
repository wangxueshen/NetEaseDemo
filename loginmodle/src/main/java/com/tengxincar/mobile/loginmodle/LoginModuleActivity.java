package com.tengxincar.mobile.loginmodle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.tengxincar.mobile.annotations.BindPath;
import com.tengxincar.mobile.arouter.ARouter;

@BindPath("loginmodle/LoginModule")
public class LoginModuleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_module);
    }

    public void jumpToPersonal(View view) {
        ARouter.getInstance().jumpToActivity("personalmodule/personalmodule", null);
    }

    private void dev0712(){

    }
}
