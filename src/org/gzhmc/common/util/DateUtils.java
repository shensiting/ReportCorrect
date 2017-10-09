package org.gzhmc.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

	static SimpleDateFormat dateFormat;

	/**
	 * 获取当前时间
	 * 
	 * @return 格式如：20150828
	 */
	public static String getCurrentDate() {
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
		String dateStr = dateFormat.format(date);// 获取今天日期
		return dateStr;
	}
	/**
	 * 获取实验报告编号
	 * @return
	 */
	public static String getreportnum() {
		String num=DateUtils.date2string(new Date(),"yyyy-MM-dd hh:mm:ss");
		num=StringUtils.stringgetnum(num);
		return num;
	}
	
	/** 
	   * 将java.util.Date 格式转换为字符串格式'yyyy-MM-dd HH:mm:ss'(24小时制)<br> 
	   * 如Sat May 11 17:24:21 CST 2002 to '2002-05-11 17:24:21'<br> 
	   * @param time Date 日期<br> 
	   * @return String   字符串<br> 
	   */ 	  
	public static String dateToString(Date time){ 
	    SimpleDateFormat formatter; 
	    formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss"); 
	    String ctime = formatter.format(time); 

	    return ctime; 
	} 
	public static String getCurTimestamp() {
		String cur = String.valueOf(System.currentTimeMillis());
		return cur;
	}

	/**
	 * 时间转字符串
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String date2string(Date date, String format) {
		dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}

	/**
	 * 字符串转日期
	 * 
	 * @param string
	 * @return
	 */
	public static Date string2date(String string) {
		dateFormat = new SimpleDateFormat();
		try {
			return dateFormat.parse(string);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 字符串转日期
	 * 
	 * @param string
	 * @return
	 */
	public static Date string2date(String string, String format) {
		dateFormat = new SimpleDateFormat(format);
		try {
			return dateFormat.parse(string);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
