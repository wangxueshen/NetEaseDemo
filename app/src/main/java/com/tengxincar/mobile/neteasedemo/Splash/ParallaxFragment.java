package com.tengxincar.mobile.neteasedemo.Splash;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wxs on 2019/7/18.
 */
public class ParallaxFragment extends Fragment {

    // 里面包含的所有控件的view 的集合
    private List<View> parallaxViews=new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //获取到布局文件id
        int rootViewId = getArguments().getInt("layoutId");
        //自定义LayoutInflater来处理里面的控件
        ParallaxLayoutInflater parallaxLayoutInflater = new ParallaxLayoutInflater(inflater,getActivity(),this);
        View rootView = parallaxLayoutInflater.inflate(rootViewId, null);

        return rootView;
    }

    public List<View> getParallaxViews() {
        return parallaxViews;
    }

}
