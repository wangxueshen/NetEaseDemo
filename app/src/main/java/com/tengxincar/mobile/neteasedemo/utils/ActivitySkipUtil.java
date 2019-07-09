package com.tengxincar.mobile.neteasedemo.utils;

import android.app.Activity;
import android.content.Intent;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ActivitySkipUtil {
 
	public ActivitySkipUtil() {
		throw new UnsupportedOperationException("ActivitySkipUtil不能实例化");
	}
 
	/**
	 * 功能描述:简单地Activity的跳转(不携带任何数据)
	 * 
	 * @Time 2016年4月25日
	 * @Author lizy18
	 * @param activity
	 *            发起跳转的Activity实例
	 */
	public static void skipAnotherActivity(Activity activity,
			Class<? extends Activity> cls) {
		Intent intent = new Intent(activity, cls);
		activity.startActivity(intent);
		activity.finish();
	}
 
	/**
	 * 功能描述：带数据的Activity之间的跳转
	 * 
	 * @Time 2016年4月25日
	 * @Author lizy18
	 * @param activity
	 * @param cls
	 * @param hashMap
	 */
	public static void skipAnotherActivity(Activity activity,
			Class<? extends Activity> cls,
			HashMap<String, ? extends Object> hashMap) {
		Intent intent = new Intent(activity, cls);
		Iterator<?> iterator = hashMap.entrySet().iterator();
		while (iterator.hasNext()) {
			@SuppressWarnings("unchecked")
			Map.Entry<String, Object> entry = (Map.Entry<String, Object>) iterator
					.next();
			String key = entry.getKey();
			Object value = entry.getValue();
			if (value instanceof String) {
				intent.putExtra(key, (String) value);
			}
			if (value instanceof Boolean) {
				intent.putExtra(key, (boolean) value);
			}
			if (value instanceof Integer) {
				intent.putExtra(key, (int) value);
			}
			if (value instanceof Float) {
				intent.putExtra(key, (float) value);
			}
			if (value instanceof Double) {
				intent.putExtra(key, (double) value);
			}
		}
		activity.startActivity(intent);
	}
}
