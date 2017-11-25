package com.fanting.aidongtan.utils;

import android.annotation.SuppressLint;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * @File:CalendarUtil.java
 * @Package:com.asiainfo.utils
 * @desc:日期计算工具类
 * @author:yuanchao
 * @date:2014年8月18日上午10:23:29
 * @version:V1.0
 */
public class CalendarUtil {

	/**
	 * 获取月份差
	 * @param start
	 * @param end
	 * @return
	 */
	public static int monthBetween(Date start, Date end){
		if (start.after(end)) {
			Date temp = start;
			start = end;
			end = temp;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(start);
		int startYear = calendar.get(Calendar.YEAR);
		int startMonth = calendar.get(Calendar.MONTH);
		
		calendar.setTime(end);
		int endYear = calendar.get(Calendar.YEAR);
		int endMonth = calendar.get(Calendar.MONTH);
		
		int year = endYear - startYear;
		int month = endMonth - startMonth;
		
		return year * 12 + month;
	}
	
	/**
	 * string转换为data
	 * @param time
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	public static Date string2Date(String time, String pattern){
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			return sdf.parse(time);
		} catch (ParseException e) {
			return new Date();
		}
	}
	
	/**
	 * date转换string
	 * @param date
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	public static String date2String(Date date, String pattern){
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}
	
	/**
	 * date转换为月日
	 * @param date
	 * @return
	 */
	public static String date2MonthAndDay(Date date){
		String dateStr = "";
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		dateStr = (calendar.get(Calendar.MONTH)+1) + "月";
		dateStr = dateStr + calendar.get(Calendar.DAY_OF_MONTH) + "日";
		return dateStr;
	}
	
	public static String getDayOfWeekFromDate(Date date){
		String dayOfWeek = "";
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		switch (calendar.get(Calendar.DAY_OF_WEEK)) {
		case 1:
			dayOfWeek = "星期日";
			break;

		case 2:
			dayOfWeek = "星期一";
			break;
			
		case 3:
			dayOfWeek = "星期二";
			break;
			
		case 4:
			dayOfWeek = "星期三";
			break;
			
		case 5:
			dayOfWeek = "星期四";
			break;
			
		case 6:
			dayOfWeek = "星期五";
			break;
			
		case 7:
			dayOfWeek = "星期六";
			break;
		}
		return dayOfWeek;
	}
	
	/**
	 * 获取一个月第一天
	 * @param date
	 * @return Date
	 */
	public static Date getFirstDayOfMonth(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}
	
	/**
	 * 获取一个月最后一天
	 * @param date
	 * @return Date
	 */
	public static Date getLastDayOfMonth(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		return calendar.getTime();
	}
	
	/**
	 * 日期格式转换
	 * @param date
	 * @param oriPattern
	 * @param desPattern
	 * @return
	 */
	public static String dateFormatConversion(String date, String oriPattern, String desPattern){
		return date2String(string2Date(date, oriPattern), desPattern);
	}
	
	/**
	 * 时间戳转为字符串
	 * @param cc_time
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	public static String getStringTime(String cc_time, String pattern){
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(new Date(Long.valueOf(cc_time)));
	}
	
	/**
	 * 字符串转换为时间戳
	 * @param strTime
	 * @param pattern
	 * @return
	 */
	public static String getTime(String strTime, String pattern){
		String cc_time = null;
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date;
		try {
			date = sdf.parse(strTime);
			cc_time = String.valueOf(date.getTime()).substring(0, 10);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cc_time;
	}
}
