package com.tengxincar.mobile.neteasedemo.utils;

import android.content.Context;
import android.util.Log;

import com.tengxincar.mobile.annotations.Subscrible;
import com.tengxincar.mobile.annotations.ThreadMode;
import com.tengxincar.mobile.neteasedemo.eventBus.SubscribleMethod;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by wxs on 2019/7/13.
 */
public class EventBus {
    //负责将某个Activity的方法添加到其中，其他Activity可以在里面寻找方法并调用


    private static EventBus eventBus;

    //存放带有注解的方法
    private Map<Object, List<SubscribleMethod>> cacheMap;


    private EventBus() {
        cacheMap = new HashMap<>();
    }

    public static EventBus getInstance() {
        if (eventBus == null) {
            eventBus = new EventBus();
        }
        return eventBus;
    }

    /**
     * //寻找所有obj类带有Subscrible注解的方法
     *
     * @param obj 可能传Fragment之类的
     */
    public void register(Object obj) {

        List<SubscribleMethod> list = cacheMap.get(obj);
        if (list == null) {
            list = findSubscribleMethods(obj);
            cacheMap.put(obj, list);
        }


    }

    /**
     * 找到带有注解的方法
     *
     * @param obj Activity fragment
     * @return
     */
    private List<SubscribleMethod> findSubscribleMethods(Object obj) {

        List<SubscribleMethod> subscribleMethodList = new ArrayList<>();
        Class<?> clazz = obj.getClass();

        while (clazz != null) {
            if (clazz.getName().startsWith("android.") || clazz.getName().startsWith("java.") || clazz.getName().startsWith("javax.")) {
                break;
            }
            Method[] methods = clazz.getDeclaredMethods();//只寻找当前Activity的方法  getMethod是当前类和父类的
            for (Method method : methods) {
                //只找带有Subscrible注解的方法
                Subscrible subscrible = method.getAnnotation(Subscrible.class);
                if (subscrible == null) {
                    continue;
                }
                //判断带有Subscrible注解中的参数类型
                Class<?>[] types = method.getParameterTypes();
                if (types.length != 1) {
                    Log.e("error", "只能传递一个参数");
                }

                ThreadMode threadMode = subscrible.threadMode();
                SubscribleMethod subscribleMethod = new SubscribleMethod(method, threadMode, types[0]);
                subscribleMethodList.add(subscribleMethod);
            }
            clazz = clazz.getSuperclass();
        }
        return subscribleMethodList;
    }


    /**
     * 跳转传递
     * @param type 传递的参数类型
     */
    public void post(Object type) {

        Set<Object> set = cacheMap.keySet();
        Iterator<Object> iterator = set.iterator();
        while (iterator.hasNext()) {
            //obj：Activity或者fragment
            Object obj = iterator.next();
            List<SubscribleMethod> list = cacheMap.get(obj);
            for (SubscribleMethod subscribleMethod : list) {
                //判断method的type和传入的type是否一致
                if (subscribleMethod.getType().isAssignableFrom(type.getClass())) {
                    invoke(subscribleMethod, obj, type);
                }
            }
        }

    }

    /**
     * method invoke
     * @param subscribleMethod 调用的方法名
     * @param obj
     * @param type  方法参数
     */
    private void invoke(SubscribleMethod subscribleMethod, Object obj, Object type) {
        Method method = subscribleMethod.getMethod();
        try {
            method.invoke(obj,type);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
