package com.tengxincar.mobile.neteasedemo.Splash;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.tengxincar.mobile.neteasedemo.R;

/**
 * 自定义LayoutInflater
 * Created by wxs on 2019/7/18.
 */
public class ParallaxLayoutInflater extends LayoutInflater {

    private ParallaxFragment fragment;


    protected ParallaxLayoutInflater(LayoutInflater original, Context newContext, ParallaxFragment fragment) {
        super(original, newContext);
        this.fragment = fragment;
        setFactory2(new ParallaxFactory(this));
    }


    @Override
    public LayoutInflater cloneInContext(Context newContext) {
        return new ParallaxLayoutInflater(this, newContext, fragment);
    }


    /**
     * 用来获取布局内容
     */
    class ParallaxFactory implements Factory2 {

        private LayoutInflater layoutInflater;
        //系统控件的前缀
        private String[] sClassPrefix = {
                "android.widget.",
                "android.view."
        };
        //系统控件自定义的属性
        int[] attrIds = {
                R.attr.a_in,
                R.attr.a_out,
                R.attr.x_in,
                R.attr.x_out,
                R.attr.y_in,
                R.attr.y_out};


        public ParallaxFactory(LayoutInflater layoutInflater) {
            this.layoutInflater = layoutInflater;
        }

        /**
         * 反射机制
         *
         * @param parent  顶级容器
         * @param name    控件名字（像RelativeLayout，ImageView）如果是自定义控件的话  返回的是全路径名字
         * @param context
         * @param attrs   控件的属性（width，height）
         * @return
         */
        @Override
        public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {

            View view = null;

            view = createMyView(name, context, attrs);

            if (view != null) {

                //attrs 控件的所有属性,attrIds 是想要获取的控件的属性
                TypedArray array = context.obtainStyledAttributes(attrs, attrIds);
                if (array != null && array.length() > 0) {
                    ParallaxViewTag viewTag = new ParallaxViewTag();
                    viewTag.alphaIn = array.getFloat(0, 0f);
                    viewTag.alphaOut = array.getFloat(array.getIndex(1), 0f);
                    viewTag.xIn = array.getFloat(array.getIndex(2), 0f);
                    viewTag.xOut = array.getFloat(array.getIndex(3), 0f);
                    viewTag.yIn = array.getFloat(array.getIndex(4), 0f);
                    viewTag.yOut = array.getFloat(array.getIndex(5), 0f);
                    view.setTag(R.id.parallax_view_tag, viewTag);

                }
                fragment.getParallaxViews().add(view);
                array.recycle();
            }

            return view;
        }

        /**
         * 创建view
         *
         * @param name
         * @param context
         * @param attrs
         * @return
         */
        private View createMyView(String name, Context context, AttributeSet attrs) {

            //如果是系统的控件，不会有.  如果是自定义控件的话  含有.
            if (name.contains(".")) {
                return reflectView(name, null, attrs);
            } else {
                //循环两个包
                for (String prefix : sClassPrefix) {
                    View view = reflectView(name, prefix, attrs);
                    if (view != null) {
                        return view;
                    }
                }

            }

            return null;
        }

        /**
         * @param name   控件名字
         * @param prefix 控件前缀
         * @param attrs  控件属性
         * @return
         */
        private View reflectView(String name, String prefix, AttributeSet attrs) {
            try {
                //通过系统的layoutInflater创建视图，读取系统属性
                return layoutInflater.createView(name, prefix, attrs);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        public View onCreateView(String name, Context context, AttributeSet attrs) {
            return null;
        }
    }

}
