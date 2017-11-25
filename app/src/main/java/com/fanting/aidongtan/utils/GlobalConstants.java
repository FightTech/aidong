package com.fanting.aidongtan.utils;


/**
 * @File:GlobalConstants.java
 * @Package:com.asiainfo.utils
 * @desc:全局静态变量(存放User对象、SessionID等信息)
 * @author:zhuzuofei
 * @date:2014-9-1 上午11:34:17
 * @version:V1.0
 */
public class GlobalConstants {
	public static final String APPID = "android"; //AppId
	
	public static final String BUSICODE_LOGIN = "10500";//busi_code
	public static final String BUSICODE_RESET_PWD = "10100";
	public static final String BUSICODE_UPDATE_PWD = "10101";
	public static final String BUSICODE_SEND_MESSAGE = "10102";
	public static final String BUSICODE_PART_OF_NETWORK = "10000";
	public static final String BUSICODE_VERRIFY_PWD = "10103";
	public static final String BUSICODE_DETAILS_QUERY = "10002";
	public static final String BUSICODE_REALBILL = "10003";
	public static final String BUSICODE_ACCOUNT_BALANCE = "10001";
	public static final String BUSICODE_USERBILL = "10004";
	public static final String BUSICODE_FREERES = "10005";
	public static final String BUSICODE_USER_ALLOFFER = "10006";
	
	public static final String BUSICODE_BUSI_REC = "10007";
	public static final String BUSICODE_PAY_REC = "10008";
	public static final String BUSICODE_ACCOUNT_PERIOD = "10009";
	public static final String BUSICODE_SYS_TIME = "10017";
	public static final String BUSICODE_HIS_OWED_BILL = "10018";
	public static final String BUSICODE_PRODUCT_REGION = "10019";
	public static final String BUSICODE_ORDER_VAS_OFFER = "10104";
	public static final String BUSICODE_CUSTOMER_INFO = "10010";
	public static final String BUSICODE_UPDATE_CUSTOMER_INFO = "10106";
	public static final String BUSICODE_USER_MAIN_OFFER = "10011";//在用套餐
	public static final String BUSICODE_USER_AVAIL_MAIN_OFFER = "10012";//可订购套餐
	public static final String BUSICODE_UPDATE_USER_MAIN_OFFER = "10107";
	public static final String BUSICODE_BUY_CARD = "10108";
	public static final String BUSICODE_XZQTC = "10013";
	public static final String BUSICODE_QUERY_CARD_REC = "10015";
	public static final String BUSICODE_CARD_PAYMENT = "10109";
	public static final String BUSICODE_CASH_PAYMENT = "10110";
	public static final String BUSICODE_CASH_PAYACCOUNT = "10016";
	public static final String BUSICODE_PAYFEE_LOG = "10501";
	public static final String BUSICODE_SMS_COMMAND = "10300";
	public static final String BUSICODE_BUSINESS_HALL = "10301";
	public static final String BUSICODE_FEED_BACK = "10400";
	public static final String BUSICODE_USER_STATE = "10022";
	public static final String BUSICODE_USER_NORMAL_INFO = "10021";
	public static final String BUSICODE_PHONENUMBER_STATE = "10020";
	public static final String BUSICODE_STOP_OR_START = "10111";
	public static final String BUSICODE_GETVERSION_CODE = "10401";
	public static final String BUSICODE_GETPAYORDER_INFO = "10112";
	
	public static final String QUERY_MODE_CALL = "202";//通话详单	
	public static final String QUERY_MODE_NET = "203";//上网详单
	public static final String QUERY_MODE_MSG = "204";//短信彩信详单
	public static final String QUERY_MODE_VALUE_ADDED = "205";//短信彩信详单
	
	public static final String IS_AUTO_LOGIN = "values-21";//是否自动登录
	public static final String NOT_AUTO_LOGIN = "0";
	
	public static final String REP_CODE = "0000";
	public static final String CHK_RES_RET_CODE = "0";
	
	public static final int PAGE_SIZE = 20;//默认分页大小
	
	public static final String INCOMING_CALL = "主叫";
	public static final String OUTGOING_CALL = "被叫";
	public static final String INCOMING_MSG = "短信息接受话单";
	public static final String OUTGOING_MSG = "短信息发送话单";
	
	public static final String FEERESOURCEINFO_YY = "88881001";
	public static final String FEERESOURCEINFO_DX = "88881003";
	public static final String FEERESOURCEINFO_LL = "88881008";
	public static final String FEERESOURCEINFO_TC = "88881000";
	
	public static final int MSG_WHAT_PROGRESS_START = 1;
	public static final int MSG_WHAT_PROGRESS_FINISH = 2;
	public static final int MSG_WHAT_PROGRESS_FAILED = 3;
	public static final int MSG_WHAT_PROGRESS_UPDATE = 4;
	public static final int MSG_WHAT_SDCARD_NOT_EXIST = 5;
	
	public static final int NO_NETWORK = 0;// 无网络
	public static final int FRESH_FAIL = 1;// 第一次加载失败
	public static final int FRESH_OK = 2;// 第一次刷新成功
	public static final int MORE_OK = 3;// 加载更多成功
	public static final int MORE_FAIL = 4;// 加载更多成功
	public static final int ERROR = 5;// 失败出错
	
	public static final String PUSH_TYPE_PRIVATE = "0";//个人消息
	public static final String PUSH_TYPE_SYSTEM = "values-21";//推送消息
	
	//保存文件路径(当前应用程序安装路径)
//	public static final String SAVE_ROOT =AppContext.getContext().getApplicationContext().getFilesDir().getAbsolutePath()+"/dixintong/";
//	public static final String SAVE_ROOT_CHAT =AppContext.getContext().getApplicationContext().getFilesDir().getAbsolutePath()+"/dixintong/main/";
}
