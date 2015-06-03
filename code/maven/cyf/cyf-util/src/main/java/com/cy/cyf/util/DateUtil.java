package com.cy.cyf.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 * @author ChenY201
 * @version 1.0
 * @created 2015-5-23 下午7:53:23
 */
public class DateUtil {

	/**
	 * 获取指定日期的1日0时0分0秒
	 * 
	 * @param date
	 *            需要处理的日期
	 * @return 当月的1日
	 */
	public static Date toFirstDateOfMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	/**
	 * 获取指定日期当月最后一天的23:59:59时间点
	 * 
	 * @param date
	 *            需要处理的日期
	 * @return 当月的最后一天
	 */
	public static Date toLastDateOfMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.MONTH, c.get(Calendar.MONTH) + 1);
		c.set(Calendar.DAY_OF_MONTH, 0);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 999);
		return c.getTime();
	}

	/**
	 * 获取指定日期的1月1日0时0分0秒
	 * 
	 * @param date
	 *            需要处理的日期
	 * @return 当月的1月1日
	 */
	public static Date toFirstDateOfYear(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_YEAR, 1);
		return c.getTime();
	}

	/**
	 * 获取指定日期的00:00:00时间点
	 * 
	 * @param date
	 *            待获取时间点的时间
	 * @return 某日期的00:00:00
	 */
	public static Date startOfDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return c.getTime();
	}

	/**
	 * 获取指定日期的23:59:59时间点
	 * 
	 * @param date
	 *            待获取时间点的时间
	 * @return 某日期的23:59:59
	 */
	public static Date endOfDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		return c.getTime();
	}
	
	/**
	 * 将字符串转换成日期
	 * @param date 日期
	 * @param format 日期格式
	 * @return
	 */
	public static Date strToDate(String date,String format){
		try {
			return new SimpleDateFormat(format).parse(date);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 将日期转换成字符串
	 * @param date 日期
	 * @param format 日期格式
	 * @return
	 */
	public static String dateToStr(Date date,String format){
		try {
			return new SimpleDateFormat(format).format(date);
		} catch (Exception e) {
			return null;
		}
	}

}
