package com.fanting.aidongtan.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetWorkUtils {

   /**
    * 检查当前手机网络
    */
   public static boolean checkNetConnectde(Context context) {
       boolean mobileConnectde = isMOBILEConnectde(context);
       boolean wifiConnectde = isWIFIConnectde(context);
       if (mobileConnectde == false && wifiConnectde == false) {
           return false;
       }
       return true;
   }

   /**
    * 判断手机是否采用手机默认联网方式
    */
   public static boolean isMOBILEConnectde(Context context) {
       ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
       NetworkInfo networkInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
       if (networkInfo != null && networkInfo.isConnected()) {
           return true;
       }
       return false;
   }

   /**
    * 判断手机是否采用 WIFI 联网
    */
   public static boolean isWIFIConnectde(Context context) {
       ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
       NetworkInfo networkInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
       if (networkInfo != null && networkInfo.isConnected()) {
           return true;
       }
       return false;
   }

}
