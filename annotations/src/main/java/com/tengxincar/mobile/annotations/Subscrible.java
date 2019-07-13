package com.tengxincar.mobile.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by wxs on 2019/7/13.
 * eventBus注解
 */

@Target(ElementType.METHOD)//声明这个注解放在什么上面的 METHOD是方法上面
@Retention(RetentionPolicy.RUNTIME)
public @interface Subscrible {
    //声明线程
    ThreadMode threadMode() default ThreadMode.MIAN;


}

