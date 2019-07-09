package com.tengxincar.mobile.neteasedemo.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by wxs on 2019/7/9.
 * 屏幕相关工具类
 */
public class ScreenUtils {

    public static ScreenUtils screenUtils;


    public static int SCREEN_WIDTH = -1;//当前设备的宽度
    public static int SCREEN_HEIGHT = -1;//当前设备的高度
    private final float STANDED_WIDTH = 720;//设计图宽度
    private final float STANDED_HEIGHT = 1280;//设计图高度


    private ScreenUtils(Context context) {

        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        DisplayMetrics displayMetrics = new DisplayMetrics();

        Display display = manager.getDefaultDisplay();

        display.getMetrics(displayMetrics);


        SCREEN_HEIGHT = displayMetrics.heightPixels - getStatusBarHeight(context);

        SCREEN_WIDTH = displayMetrics.widthPixels;

        if (SCREEN_WIDTH > SCREEN_HEIGHT) {
            int t = SCREEN_HEIGHT;
            SCREEN_HEIGHT = SCREEN_WIDTH;
            SCREEN_WIDTH = t;
        }

    }

    private int getStatusBarHeight(Context context) {

        int resId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resId > 0) {
            return context.getResources().getDimensionPixelOffset(resId);
        }
        return 0;


    }

    public static ScreenUtils getInstance(Context context) {
        if (screenUtils == null) {
            screenUtils = new ScreenUtils(context);
        }
        return screenUtils;
    }

    /**
     * 获取水平方向比例
     *
     * @return
     */
    public float getHorizontalScale() {
        return SCREEN_WIDTH / STANDED_WIDTH;
    }

    /**
     * 获取垂直方向比例
     *
     * @return
     */
    public float getVerticalScale() {
        return SCREEN_HEIGHT / STANDED_HEIGHT;
    }
}
