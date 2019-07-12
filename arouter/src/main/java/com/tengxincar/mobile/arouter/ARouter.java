package com.tengxincar.mobile.arouter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import dalvik.system.DexFile;

/**
 * ARouter代理
 * Created by wxs on 2019/7/12.
 */
public class ARouter {

    private static ARouter aRouter;
    private Context context;


    private HashMap<String, Class<? extends Activity>> activitys;


    public ARouter() {
        activitys = new HashMap<>();
    }

    public void init(Context context) {
        this.context = context;
        //包名下面所有的类名
        List<String> classNames = getClassName("com.tengxincar.mobile.utils");
        for (String className : classNames) {
            try {
                //得到类
                Class<?> aClass = Class.forName(className);
                //是否是IRouter的实现类
                if (IRouter.class.isAssignableFrom(aClass)) {
                    //接口的引用指向子类的实例
                    IRouter iRouter = (IRouter) aClass.newInstance();
                    iRouter.putActivity();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private List<String> getClassName(String packgeName) {
        //创建一个Class对象的集合
        List<String> classList = new ArrayList<>();
        String path = null;

        try {
            //通过包管理器，获取到应用信息类然后获取到APK的完整路径
            path = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).sourceDir;
            //根据APK的完整路径获取到编译后的dex文件
            DexFile dexFile = new DexFile(path);
            //获得编译后的dex文件中的所有class
            Enumeration enumeration = dexFile.entries();
            //然后进行遍历
            while (enumeration.hasMoreElements()) {
                //遍历所有的class包名
                String name = (String) enumeration.nextElement();
                //判断类的包名是否符合
                if (name.contains(packgeName)) {
                    classList.add(name);
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return classList;
    }


    public static ARouter getInstance() {
        if (aRouter == null)
            aRouter = new ARouter();
        return aRouter;
    }

    /**
     * 将Activity的类对象添加到map中
     *
     * @param path
     * @param clazz
     */
    public void putActivity(String path, Class<? extends Activity> clazz) {
        if (path != null && clazz != null) {
            activitys.put(path, clazz);
        }

    }

    /**
     * 跳转方法
     *
     * @param path
     * @param bundle
     */
    public void jumpToActivity(String path, Bundle bundle) {
        Class<? extends Activity> aClass = activitys.get(path);
        if (aClass == null) {
            return;
        }
        Intent intent = new Intent(context, aClass);
        if (bundle != null) {
            intent.putExtra("bundle", bundle);
        }
        context.startActivity(intent);

    }


}
