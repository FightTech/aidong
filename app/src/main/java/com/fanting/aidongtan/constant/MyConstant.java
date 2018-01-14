package com.fanting.aidongtan.constant;

import android.os.Environment;

import com.fanting.aidongtan.AppContext;

/**
 * Created by Administrator on 2017/4/5.
 */

public class MyConstant {
    /**
     * 微信APP_ID
     **/
    public static String WX_APP_ID = "wx1c1448a235ab9940";
    /**
     * 微信WX_APP_SECRET
     **/
    public static String WX_APP_SECRET = "6fba33a8a80f4aec43ec4ca03272981a";
    /**
     * 支付宝APPID
     **/
    public static String ALIPAY_APPID = "2017030906138203";

    /**
     * 正常页
     **/
    public static int PAGE_NORMAL = 0;
    /**
     * 错误页
     **/
    public static int PAGE_ERROR = 1;

    /**
     * 空页面
     **/
    public static int PAGE_EMPTY = 2;

    /**
     * htmlPage_type
     **/
    public static String htmlPage_goodsDital = "htmlPage_goodsDital";

    /**
     * 缓存目录
     **/
    public static String CACHEDIR= AppContext.getContext().getExternalCacheDir().getAbsolutePath();
    /**
     * 下载的图片缓存文件夹
     **/
    public static String DOWNLODA_PATH = CACHEDIR+ "/download";
    /**
     * 拍照缓存目录
     **/
    public static String PHOTO_PATH = CACHEDIR+ "/photo";

    /**
     * 云信缓存目录
     **/
    public static String NIM_CACHE_PATH = CACHEDIR+ "/nim";

    /**
     * 数据库名称
     **/
    public static String DB_NAME ="sharetrunk_db";

}

