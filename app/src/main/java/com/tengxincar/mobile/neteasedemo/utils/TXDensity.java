package com.tengxincar.mobile.neteasedemo.utils;

import android.app.Activity;
import android.app.Application;
import android.util.DisplayMetrics;

/**
 * Created by wxs on 2019/7/10.
 * 屏幕适配
 */
public class TXDensity {


    private static float WIDTH = 360;//参考设备的宽度  单位dp

    private static float appDensity;//当前屏幕密度
    private static float appScaleDensity;//字体缩放比例默认为appDensity


    public static void setDensity(Application application, Activity activity) {

        //获取当前屏幕显示信息
        DisplayMetrics displayMetrics = application.getResources().getDisplayMetrics();
        if (appDensity == 0) {
            appDensity = displayMetrics.density;
            appScaleDensity = displayMetrics.scaledDensity;
        }

        //计算目标值density scaleDensity densityDpi
        //density屏幕密度  160px 每平方英寸有多少个像素点
        //scaleDensity 字体缩放比例 跟density一样
        //densityDpi 屏幕上每一英寸的像素点有多少个

        float targetDensity = displayMetrics.widthPixels / WIDTH;//当前设备的宽度

        float targetScaleDensity = targetDensity * (appScaleDensity / appDensity);

        int targetDensityDpi = (int) (targetDensity * WIDTH);


        //替换Activity的值

        DisplayMetrics dm = activity.getResources().getDisplayMetrics();

        dm.density = targetDensity;
        dm.scaledDensity = targetScaleDensity;
        dm.densityDpi = targetDensityDpi;


    }

}
