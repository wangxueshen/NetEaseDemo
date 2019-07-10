package com.tengxincar.mobile.neteasedemo.customView;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.tengxincar.mobile.neteasedemo.R;

/**
 * Created by wxs on 2019/7/9.
 * 模仿谷歌原生的百分比布局
 */
public class PercentLayout extends RelativeLayout {
    public PercentLayout(Context context) {
        super(context);
    }

    public PercentLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PercentLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public static class LayoutParmas extends RelativeLayout.LayoutParams {

        private float widthPercent;
        private float heightPercent;
        private float marginLeftPercent;
        private float marginRightPercent;
        private float marginTopPercent;
        private float marginBottomPercent;


        public LayoutParmas(Context c, AttributeSet attrs) {
            super(c, attrs);


            TypedArray array = c.obtainStyledAttributes(attrs, R.styleable.PercentLayout);
            widthPercent = array.getFloat(R.styleable.PercentLayout_widthPercent, 0);
            heightPercent = array.getFloat(R.styleable.PercentLayout_heightPercent, 0);
            marginTopPercent = array.getFloat(R.styleable.PercentLayout_marginTopPercent, 0);
            marginBottomPercent = array.getFloat(R.styleable.PercentLayout_marginBottomPercent, 0);
            marginLeftPercent = array.getFloat(R.styleable.PercentLayout_marginLeftPercent, 0);
            marginRightPercent = array.getFloat(R.styleable.PercentLayout_marginRightPercent, 0);
            array.recycle();

        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            ViewGroup.LayoutParams params = child.getLayoutParams();
            if (checkLayoutParams(params)) {
                LayoutParmas lp = (LayoutParmas) params;
                float widthPercent = lp.widthPercent;
                float heightPercent = lp.heightPercent;
                float maginTopPercent = lp.marginTopPercent;
                float maginLeftPercent = lp.marginLeftPercent;
                float maginRightPercent = lp.marginRightPercent;
                float maginBottomPercent = lp.marginBottomPercent;

                if (widthPercent > 0) {
                    params.width = (int) (widthSize * widthPercent);
                }

                if (heightPercent > 0) {
                    params.height = (int) (heightSize * heightPercent);
                }

                if (maginLeftPercent > 0) {
                    ((LayoutParmas) params).leftMargin = (int) (widthSize * maginLeftPercent);
                }

                if (maginBottomPercent > 0) {
                    ((LayoutParmas) params).bottomMargin = (int) (heightSize * maginBottomPercent);
                }

                if (maginTopPercent > 0) {
                    ((LayoutParmas) params).topMargin = (int) (heightSize * maginTopPercent);
                }

                if (maginRightPercent > 0) {
                    ((LayoutParmas) params).rightMargin = (int) (heightSize * maginRightPercent);
                }

            }
        }


        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParmas(getContext(), attrs);
    }
}
