package com.fanting.aidongtan;


import android.app.Application;
import android.content.Context;

import com.fanting.aidongtan.constant.MyConstant;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.fanting.aidongtan.handler.CrashHandler;
import com.fanting.aidongtan.utils.AppManager;

import java.io.File;
import java.util.Date;


public class AppContext extends Application {



	public static RequestQueue queue;
	private static boolean isLogin;//登陆状态
	private static Date sysTime;//网络日期
	//private static User loginUser;//登陆用户
	public static boolean isAuthentication;

	private static Context instance;

	public static AppManager appManager;

	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
		/*appManager = AppManager.getAppManager();
		CrashHandler crashHandler = CrashHandler.getInstance();
		// 注册crashHandler
		crashHandler.init(getApplicationContext());
		// 发送以前没发送的报告(可选)
<<<<<<< HEAD
		crashHandler.sendPreviousReportsToServer();*/
		//初始化缓存路径
		initCachePath();
		//crashHandler.sendPreviousReportsToServer();
		queue = Volley.newRequestQueue(getApplicationContext());
	}

	public static RequestQueue getQueue() {
		return queue;
	}

	public static Context getContext() {
		return instance;
	}

	/**
	 * 初始化用户信息
	 * @param user
	 * @param time
	 */
//	public static void initLogonInfo(User user, String time, String JSESSIONID){
//		if (user != null) {
//			loginUser = user;
//			isLogin = true;
//			if (time != null) {
//				sysTime = CalendarUtil.string2Date(time.substring(0, 8), "yyyyMMdd");
//			}
//			if (JSESSIONID != null) {
//				SharedPreferencesUtils.saveData("JSESSIONID", JSESSIONID);
//			}
//		} else {
//			logout();
//		}
//	}
//
	/**
	 * 用户注销
	 */
//	public static void logout(){
//		loginUser = null;
//		isLogin = false;
//		SharedPreferencesUtils.removeData("JSESSIONID");
//	}
//
	public static Date getSysTime() {
		if (sysTime == null) {
			sysTime = new Date();
		}
		return sysTime;
	}

	public static boolean isLogin(){
		return isLogin;
	}

//	public static User getUser(){
//		return loginUser;
//	}

	public static void exit(){
//		appManager.finishAllActivity();
		System.exit(0);
	}

	public static void get(){

	}

	//创建缓存路径
	private void initCachePath() {
		File photoDirs = new File(MyConstant.PHOTO_PATH);
		if(!photoDirs.exists()){
			photoDirs.mkdirs();
		}
		File DownlaodDirs = new File(MyConstant.DOWNLODA_PATH);
		if(!DownlaodDirs.exists()){
			DownlaodDirs.mkdirs();
		}
	}
}
