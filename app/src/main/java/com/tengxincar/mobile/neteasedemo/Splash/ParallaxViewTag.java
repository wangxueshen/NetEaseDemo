package com.tengxincar.mobile.neteasedemo.Splash;

/**
 * 视差动画播放时参数的控制
 * Created by wxs on 2019/7/18.
 */
public class ParallaxViewTag {
    protected int index;
    protected float xIn;
    protected float xOut;
    protected float yIn;
    protected float yOut;
    protected float alphaIn;
    protected float alphaOut;


    @Override
    public String toString() {
        return "ParallaxViewTag [index=" + index + ", xIn=" + xIn + ", xOut="
                + xOut + ", yIn=" + yIn + ", yOut=" + yOut + ", alphaIn="
                + alphaIn + ", alphaOut=" + alphaOut + "]";
    }
}
