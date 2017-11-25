package com.fanting.aidongtan.utils;

import java.io.File;
import java.lang.reflect.Method;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageStats;
import android.os.Environment;
import android.os.RemoteException;

/**
 * @title DataCleanManager
 * @desc 本应用数据管理器
 * @company asiainfo.com
 * @author yuanchao
 * @date 2014年9月21日
 */
public class AppDataManager {

	/**
	 * 清除本应用内部缓存(/data/data/com.xxx.xxx/cache)
	 */
	public static void cleanInternalCache(Context context){
		deleteFileByDirectory(context.getCacheDir());
	}
	
	/**
	 * 清除本应用所有数据库(/data/data/com.xxx.xxx/databases)
	 * @param context
	 */
	public static void cleanDatabases(Context context){
		deleteFileByDirectory(new File("/data/data" + context.getPackageName() + "/databases"));
	}
	
	/**
	 * 清除本应用SharedPreference(/data/data/com.xxx.xxx/shared_prefs)
	 * @param context
	 */
	public static void cleanSharedPreference(Context context){
		deleteFileByDirectory(new File("/data/data" + context.getPackageName() + "shared_prefs"));
	}
	
	/**
	 * 按名字清除本应用数据库
	 * @param context
	 * @param dbName
	 */
	public static void cleanDatabaseByName(Context context, String dbName){
		context.deleteDatabase(dbName);
	}
	
	/**
	 * 清除/data/data/com.xxx.xxx/files下的内容
	 * @param context
	 */
	public static void cleanFiles(Context context){
		deleteFileByDirectory(context.getFilesDir());
	}
	
	/**
	 * 清除外部cache下的内容(/mnt/sdcard/android/data/com.xxx.xxx/cache)
	 * @param context
	 */
	public static void cleanExternalCache(Context context){
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			deleteFileByDirectory(context.getExternalCacheDir()	);
		}
	}
	
	/**
	 * 清除自定义路径下的文件，使用需小心，请不要误删。而且只支持目录下的文件删除
	 * @param fileName
	 */
	public static void cleanCustomCache(String fileName){
		deleteFileByDirectory(new File(fileName));
	}
	
	/**
	 * 清除本应用所有的数据
	 * @param context
	 * @param fileName
	 */
	public static void cleanApplicationData(Context context, String... fileName){
		cleanInternalCache(context);
		cleanExternalCache(context);
		cleanDatabases(context);
		cleanSharedPreference(context);
		cleanFiles(context);
		for (String filePath : fileName) {
			cleanCustomCache(filePath);
		}
	}
	
	/**
	 * 删除方法，只删除指定文件夹下的文件，如果@directory是个文件，将不做处理
	 * @param directory
	 */
	private static void deleteFileByDirectory(File directory){
		if (directory != null && directory.exists() && directory.isDirectory()) {
			for (File item : directory.listFiles()) {
				item.delete();
			}
		}
	}
	
}
