package com.tengxincar.mobile.neteasedemo.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by wxs on 2019/7/9.
 */
public class ScreenAdapterLayout extends RelativeLayout {
    public ScreenAdapterLayout(Context context) {
        super(context);
    }

    public ScreenAdapterLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScreenAdapterLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        float scaleX = ScreenUtils.getInstance(getContext()).getHorizontalScale();
        float scaleY = ScreenUtils.getInstance(getContext()).getVerticalScale();

        int count=getChildCount();

        for (int i=0;i<count;i++){
            View child=getChildAt(i);
            LayoutParams params= (LayoutParams) child.getLayoutParams();

            params.width= (int) (params.width*scaleX);
            params.height= (int) (params.height*scaleY);

        }



        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
