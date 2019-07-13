package com.tengxincar.mobile.neteasedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.tengxincar.mobile.neteasedemo.utils.ActivitySkipUtil;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {


    @BindView(R.id.lv_demo)
    ListView lvDemo;
    private String[] data = new String[]{
            "SplitView",
            "ScreenUtils",
            "ScreenPercent",
            "Density",
            "CustomARouter",
            "EventBus"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
        lvDemo.setAdapter(arrayAdapter);
        lvDemo.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String className = getPackageName() + "." + data[position] + "Activity";
        try {
            Class activityClass = Class.forName(className);
            ActivitySkipUtil.skipAnotherActivity(this, activityClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
