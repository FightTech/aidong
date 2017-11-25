package com.fanting.aidongtan.utils;

import android.content.SharedPreferences;

import com.fanting.aidongtan.AppContext;


/**
 * @Title:SharedPreferencesUtils.java
 * @Package:com.asiainfo.utils
 * @desc:本地保存数据工具类
 * @author:zhuzuofei
 * @date:2014-7-23 上午11:24:12
 * @version:V1.0
 */
public class SharedPreferencesUtils {

	private static final String SHAREDNAME = "dixintongconfig";
	
	public static void saveData(String key, Object value) {
		SharedPreferences sp = AppContext.getContext()
				.getSharedPreferences(SHAREDNAME, 0);
		SharedPreferences.Editor editor = sp.edit();
		if (value instanceof String) {
			editor.putString(key, value.toString());
		} else if (value instanceof Boolean) {
			editor.putBoolean(key, (Boolean) value);		//?????
		} else if (value instanceof Float) {
			editor.putFloat(key, Float.parseFloat(value.toString()));
		} else if (value instanceof Integer) {
			editor.putInt(key, Integer.parseInt(value.toString()));
		} else if (value instanceof Long) {
			editor.putLong(key, Long.parseLong(value.toString()));
		} else {
			editor.putString(key, value.toString());
		}
		editor.commit();
	}
	
	public static void saveBoolean(String key, boolean value) {
		SharedPreferences sp = AppContext.getContext()
				.getSharedPreferences(SHAREDNAME, 0);
		SharedPreferences.Editor editor = sp.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}

	public static void removeData(String key) {
		SharedPreferences sp = AppContext.getContext()
				.getSharedPreferences(SHAREDNAME, 0);
		if (sp.contains(key))
			sp.edit().remove(key).commit();
	}

	public static String getString(String key, String defaultValue) {
		SharedPreferences sp = AppContext.getContext()
				.getSharedPreferences(SHAREDNAME, 0);
		return sp.getString(key, defaultValue);
	}

	public static boolean getBoolean(String key, boolean defaultValue) {
		SharedPreferences sp = AppContext.getContext()
				.getSharedPreferences(SHAREDNAME, 0);
		return sp.getBoolean(key, defaultValue);
	}

	public static float getFloat(String key, float defaultValue) {
		SharedPreferences sp = AppContext.getContext()
				.getSharedPreferences(SHAREDNAME, 0);
		return sp.getFloat(key, defaultValue);
	}

	public static int getInt(String key, int defaultValue) {
		SharedPreferences sp = AppContext.getContext()
				.getSharedPreferences(SHAREDNAME, 0);
		return sp.getInt(key, defaultValue);
	}

	public static long getLong(String key, long defaultValue) {
		SharedPreferences sp = AppContext.getContext()
				.getSharedPreferences(SHAREDNAME, 0);
		return sp.getLong(key, defaultValue);
	}

}
