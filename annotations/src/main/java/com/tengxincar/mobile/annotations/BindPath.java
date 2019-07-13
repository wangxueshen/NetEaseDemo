package com.tengxincar.mobile.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by wxs on 2019/7/12.
 * 定义注解需要用@interface声明，并且需要写原注解@Target(ElementType.TYPE)
 * 注解需要定义javaLib
 */
@Target(ElementType.TYPE)//声明这个注解放在什么上面的 TYPE是类上面
@Retention(RetentionPolicy.CLASS)//声明这个注解的生命周期   三个生命周期  java--->Source class--->Class RUNTime---->run
public @interface BindPath {
    String value();
}
