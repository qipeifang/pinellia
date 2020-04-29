package com.jokerchen.pinellia.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Pattern;

/**   
 * @description: 时间格式工具类
 * @author Joker Chen 
 * @date 2019-03-12 16:29:07  
 */
public class DateUtil {

	/** yyyy-MM-dd */
	public static String DATE_FORMAT_DATE = "yyyy-MM-dd";
	/** yyyy-MM-dd HH:mm:ss */
	public static String DATE_FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss";
	/** yyyy-MM-dd */
	public static SimpleDateFormat sdfDate = new SimpleDateFormat(DATE_FORMAT_DATE);
	/** yyyy-MM-dd HH:mm:ss */
	public static SimpleDateFormat sdfDateTime = new SimpleDateFormat(DATE_FORMAT_DATETIME);
	
	/**
	 * 用来将字符串转成时间的预定义正则
	 */
	public static Object[][] dateStringFormat = new Object[][] {
		{"^\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}$",sdfDateTime},
		{"^\\d{4}-\\d{2}-\\d{2}$",sdfDate}
	};
	
	/**
	 * 时间类型转成字符串类型
	 * @param date		要转换的时间
	 * @param format	要返回的时间格式
	 * @return	时间的字符串
	 */
	public static String dateToString(Date date,String format) {
		if(date == null) return "";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		return simpleDateFormat.format(date);
	}
	
	/**
	 * 时间类型转成字符串类型 yyyy-MM-dd HH:mm:ss
	 * @param date		要转换的时间
	 * @return	时间的字符串
	 */
	public static String dateToString(Date date) {
		if(date == null) return "";
		return sdfDateTime.format(date);
	}
	
	/**
	 * 数字格式的时间转成字符串类型
	 * @param time		要转换的时间，数字格式
	 * @param format	要返回的时间格式
	 * @return	时间的字符串
	 */
	public static String dateToString(long time,String format) {
		return dateToString(new Date(time),format);
	}
	
	/**
	 * 数字格式的时间转成字符串类型  yyyy-MM-dd HH:mm:ss
	 * @param time		要转换的时间，数字格式
	 * @return	时间的字符串
	 */
	public static String dateToString(long time) {
		return dateToString(new Date(time));
	}
	
	/**
	 * 时间类型转成字符串类型
	 * @param dateStr	要转换的时间
	 * @param format	传入字符串时间的格式
	 * @return	时间
	 * @throws ParseException 
	 */
	public static Date stringToDate(String dateStr,String format) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		return simpleDateFormat.parse(dateStr);
	}
	
	/**
	 * 时间类型转成字符串类型
	 * @param dateStr	要转换的时间
	 * @param format	传入字符串时间的格式
	 * @return	时间的数字
	 * @throws ParseException 
	 */
	public static long stringToTime(String dateStr,String format) throws ParseException {
		return stringToDate(dateStr,format).getTime();
	}
	
	/**
	 * 时间类型转成字符串类型
	 * @param dateStr 要转换的时间
	 * @return 时间
	 * @throws ParseException 
	 */
	public static Date stringToDate(String dateStr) throws ParseException {
		for(int i=0;i<dateStringFormat.length;i++) {
			if(Pattern.matches(dateStringFormat[i][0].toString(),dateStr)) {
				SimpleDateFormat simpleDateFormat = (SimpleDateFormat) dateStringFormat[i][1];
				return simpleDateFormat.parse(dateStr);
			}
		}
		return null;
	}
	
	/**
	 * 时间类型转成字符串类型
	 * @param dateStr 要转换的时间
	 * @return 时间
	 * @throws ParseException 
	 */
	public static Long stringToTime(String dateStr) throws ParseException {
		Date date = stringToDate(dateStr);
		return date == null? null:date.getTime();
	}
	
	public static void main(String[] args) {
		try {
			/*String dateStr = "2018-01-01";
			Date date = stringToDate(dateStr);
			System.out.println(date);*/
			
			System.out.println(getMonth());
			System.out.println(getLastMonth());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取本月时间
	 * @return
	 */
	public static String getMonth() {
		LocalDate today = LocalDate.now();
		DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy-MM");
		return formatters.format(today);
	}
	
	/**
	 * 获取上个月时间
	 * @return
	 */
	public static String getLastMonth() {
		LocalDate today = LocalDate.now();
		today = today.minusMonths(1);
		DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy-MM");
		return formatters.format(today);
	}
	
	
	/**
	 * 获取本月时间
	 * @return
	 */
	public static String getYear() {
		LocalDate today = LocalDate.now();
		DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy");
		return formatters.format(today);
	}
	
	/**
	 * 获取上个月时间
	 * @return
	 */
	public static String getLastYear() {
		LocalDate today = LocalDate.now();
		today = today.minusYears(1);
		DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy");
		return formatters.format(today);
	}
	
	/**
	 * 获取日期
	 * @param offset	偏移量，-2：前天，-1：昨天，0：今天，1：明天，2：后天，依此类推
	 * @return	返回日期字符串格式 yyyy-MM-dd
	 */
	public static String getDay(int offset) {
		long time = System.currentTimeMillis();
		//86400000 = 24 * 60 * 60 * 1000;
		time = time + offset * 86400000;
		Date date = new Date(time);
		return dateToString(date, DATE_FORMAT_DATE);
	}
	
}
