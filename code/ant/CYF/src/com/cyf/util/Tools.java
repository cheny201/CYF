package com.cyf.util;

import java.io.File;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;


import com.cyf.log.CYFLog;

/**
 * 一般工具类
 * @author ChenY201
 * @version 1.0
 * @created 2014-11-12 下午8:35:04
 */
public class Tools {

	/**
	 * 默认时间格式<br/>
	 * yyyy-MM-dd
	 */
	public final static String DEFAULTFORMAT = "yyyy-MM-dd";
	
	/**
	 * 完整日期格式
	 */
	public final static String FULLFORMAT = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 默认的小数格式
	 */
	public final static String DECIMALFORMAT = "0.00";
	
	/**
	 * 描述：验证字符串是否为空
	 * <p>
	 * 日期：2011-02-09
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isBlank(Object str) {
		if (str == null || "".equals(str.toString().trim()))
			return true;
		else
			return false;
	}
	 
	public static boolean isBlank(final String str) {
		return (str == null) || (str.trim().length() <= 0);
	}
	
	public static boolean isBlank(final Object[] objs) {
		return (objs == null) || (objs.length <= 0);
	}

	public static boolean isBlank(final Collection<?> obj) {
		return (obj == null) || (obj.size() <= 0);
	}

	public static boolean isBlank(final Set<?> obj) {
		return (obj == null) || (obj.size() <= 0);
	}

	public static boolean isBlank(final Serializable obj) {
		return obj == null;
	}

	public static boolean isBlank(final Map<?,?> obj) {
		return (obj == null) || (obj.size() <= 0);
	}
	
	/**
	 * 描述：判断输入是否是数字  日期：2011-02-14
	 * @param str
	 * @return
	 */
	public static boolean isNum(String str) {
		boolean flag = false;
		Pattern pattern = Pattern.compile("^\\d+$");
		if (isBlank(str)) {
			return flag;
		} else {
			Matcher m = pattern.matcher(str);
			if (m.matches()) {
				flag = true;
			} else {
				flag = false;
			}
			return flag;
		}
	}

	/**
	 * 描述：把字符串转换成指定格式的日期
	 * <p>
	 * 日期：2011-02-12
	 */
	public static Date converDate(String destStr)
			throws Exception {
		try {
			if (Tools.isBlank(destStr)) {
				return null;
			}
			SimpleDateFormat dFormat = new SimpleDateFormat(Tools.DEFAULTFORMAT);
			return dFormat.parse(destStr);
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 描述：把字符串转换成指定格式的日期
	 * <p>
	 * 日期：2011-02-12
	 */
	public static Date converDate(String destStr, String format)
			throws Exception {
		try {
			if (Tools.isBlank(destStr)) {
				return null;
			}
			SimpleDateFormat dFormat = new SimpleDateFormat(format);
			return dFormat.parse(destStr);
		} catch (Exception e) {
			throw e;
		}
	}


	/**
	 * 描述：把日期转换成指定格式的日期
	 * <p>
	 * 日期：2011-02-12
	 */
	public static Date converDate(Date destDate, String format)
			throws Exception {
		try {
			if (destDate == null) {
				return null;
			}
			SimpleDateFormat dFormat = new SimpleDateFormat(format);
			String tmp = dFormat.format(destDate);
			return Tools.converDate(tmp, format);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 描述：把日期转换成指定格式的日期
	 * <p>
	 * 日期：2011-02-12
	 */
	public static String converToString(Date destDate, String format)
			throws Exception {
		try {
			if (destDate == null) {
				return null;
			}
			SimpleDateFormat dFormat = new SimpleDateFormat(format);
			String tmp = dFormat.format(destDate);
			return tmp;
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 描述：把日期格式字符串转换成指定格式的字符串
	 * <p>
	 * 日期：2011-02-12
	 * @param destStr 需要转换的日期格式的字符串
	 * @param destStrFormat 需要转换的字符串的格式
	 * @param toFormat 要转换成的格式
	 */
	public static String converToString(String destStr, String destStrFormat,String toFormat)
			throws Exception {
		try {
			if (isBlank(destStr)) {
				return null;
			}
			SimpleDateFormat dFormat = new SimpleDateFormat(destStrFormat);
			Date tmp = dFormat.parse(destStr);
			dFormat = new SimpleDateFormat(toFormat);
			return dFormat.format(tmp);
		} catch (Exception e) {
			throw e;
		}
	}


	/**
	 * 描述：把字符串转换成指定编码格式
	 * <p>
	 * 日期：2011-02-21
	 * 
	 * @return
	 */
	public static String encodStr(String srcStr, String encoder)
			throws Exception {
		try {
			if (Tools.isBlank(srcStr)) {
				return srcStr;
			}
			return new String(srcStr.getBytes("iso-8859-1"), encoder);
		} catch (Exception e) {
			throw e;
		}
	}
	/**
	 * 描述：计算两个时间相差的天数<p>
	 * 参数：返回date1-date2相差的天数
	 */
	public static Long dateDvalue(Date date1,Date date2)
		throws Exception{
		try {
			Long result = converDate(date1, "yyyy-MM-dd").getTime()-converDate(date2, "yyyy-MM-dd").getTime();
			return result/86400000;
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 描述：计算两个时间相差的毫秒数<p>
	 * 参数：返回date1-date2相差的毫秒数
	 */
	public static Long dateMillisecond(Date date1,Date date2)
		throws Exception{
		try {
			Long result = date1.getTime()-date2.getTime();
			return result;
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 描述：把数组转换成字符串，使用指定符号分隔
	 * @param src 要转换的数组
	 * @param arg 拼接数组元素的分隔符
	 * @return
	 */
	public static String arrayToString(String[] src,String arg){
		if(src==null || src.length<=0)
			return null;
		StringBuilder builder = new StringBuilder();
		for(String str : src){
			if(isBlank(str))
				continue;
			builder.append("'"+str+"'").append(arg);
		}
		if(builder!=null && builder.length()>0)
			builder = builder.deleteCharAt(builder.length()-1);
		return builder.toString();
	}
	
	/**
	 * 描述：把数组转换，使用指定符号分隔
	 * @param src 要转换的数组
	 * @param arg 拼接数组元素的分隔符
	 * @return
	 */
	public static String arrayToNumber(Object[] src,String arg){
		if(src==null || src.length<=0)
			return null;
		StringBuilder builder = new StringBuilder();
		for(Object str : src){
			if(isBlank(str))
				continue;
			builder.append(str).append(arg);
		}
		if(builder!=null && builder.length()>0)
			builder = builder.deleteCharAt(builder.length()-1);
		return builder.toString();
	}
	
	/**
	 * 描述：把集合转换成字符串，使用指定符号分隔
	 * @param src 要转换的数组
	 * @param arg 拼接数组元素的分隔符
	 * @return
	 */
	public static String arrayToString(List<?> src,String arg){
		if(src==null || src.size()<=0)
			return null;
		StringBuilder builder = new StringBuilder();
		for(Object str : src){
			if(isBlank(str))
				continue;
			builder.append("'"+str.toString()+"'").append(arg);
		}
		if(builder!=null && builder.length()>0)
			builder = builder.deleteCharAt(builder.length()-1);
		return builder.toString();
	}
	
	/**
	 * 根据输入的格式返回当前时间的字符串格式
	 * @param format
	 * @return
	 */
	public static String getCurrentDate(String format){
		SimpleDateFormat dFormat = new SimpleDateFormat(format);
		return dFormat.format(new Date());
	}
	
	public static Calendar getCurrentDate(){
		return Calendar.getInstance();
	}
	
	public static Calendar getCurrentCalendar(String format) throws Exception{
		Calendar nowdate = Calendar.getInstance();
		try {
			nowdate.setTime(Tools.converDate(new Date(), format));
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return nowdate;
		
	}
	
	public  static String getRandomStr(){
		try {
			Thread.sleep(40);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Long times = getCurrentDate().getTimeInMillis();
		return times+"";
	}
	
	/**
	* 将文件名中的汉字转为UTF8编码的串,以便下载时能正确显示另存的文件名.
	* @param s 原文件名
	* @return 重新编码后的文件名
	*/
	public static String toUtf8String(String s) 
	{
		StringBuffer sb = new StringBuffer();
		for (int i=0;i<s.length();i++) 
		{
		    char c = s.charAt(i);
		    if (c >= 0 && c <= 255) {
		    	sb.append(c);
		    } else {
				byte[] b;
				try {
					b = String.valueOf(c).getBytes("utf-8");
				} catch (Exception ex) {
				    b = new byte[0];
				}
				for (int j = 0; j < b.length; j++) {
				    int k = b[j];
				    if (k < 0) k += 256;
				    sb.append("%" + Integer.toHexString(k).
				    toUpperCase());
				}
		    }
		}
		return sb.toString();
	}
	
	public static void setHead(HttpServletResponse response,String contenttype,String filename){
		response.setContentType(contenttype);
		String externalName = "";
		try {
			externalName = URLEncoder.encode(filename, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			CYFLog.error("",e);
		}
		response.addHeader("Content-Disposition", "attachment;filename="+externalName);
	}
	
	public static void setHead(HttpServletResponse response,String contenttype,String filename,String encoding){
		response.setContentType(contenttype);
		String externalName = "";
		try {
			externalName = URLEncoder.encode(filename, encoding);
		} catch (UnsupportedEncodingException e) {
			CYFLog.error("",e);
		}
		response.addHeader("Content-Disposition", "attachment;filename="+externalName);
	}
	
	/**
	 * 删除文件
	 * @param localUrl
	 * @param delParent 是否删除所有上级目录（直到上级目录下有文件为止则不删除）
	 */
	public static void delFile(String localUrl,boolean delParent){
		File file = new File(localUrl);
		if(!file.exists()){
			return;
		}
		
		file.delete();
		
		file = file.getParentFile();
		if(file.isDirectory() && (file.list()==null || file.list().length<=0)){
			if(delParent){
				delFile(file.getAbsolutePath(), delParent);
			}
		}
	}
	
	/**
	 * 删除文件
	 * @param localUrl
	 * @param deep 如果上级目录为空则删除上级目录，deep表示往上删除几层
	 */
	public static void delFile(String localUrl,int deep){
		File file = new File(localUrl);
		if(!file.exists()){
			return;
		}
		
		file.delete();
		if(deep<=0)
			return;
		
		file = file.getParentFile();
		if(file.isDirectory() && (file.list()==null || file.list().length<=0)){
			delFile(file.getAbsolutePath(), deep-1);
		}
	}
	
	public static double formatDecimat(Double value,String fromat){
		if(value == null){
			value = 0.0;
		}
		DecimalFormat decimalFormat = new DecimalFormat();
		decimalFormat.applyPattern(fromat);
		return Double.valueOf(decimalFormat.format(value));
	}
	
	public static String formatDecimatToStr(Double value,String fromat){
		if(value == null){
			value = 0.0;
		}
		DecimalFormat decimalFormat = new DecimalFormat();
		decimalFormat.applyPattern(fromat);
		return decimalFormat.format(value);
	}
	
	/**
	 * 获取指定日期的1日0时0分0秒
	 * @param date 需要处理的日期
	 * @return 当月的1月1日
	 * @since 2013-10-18
	 */
	public static Date toFirstDateOfMonth(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, 1);
		return c.getTime();
	}
	
	/**
	 * 获取指定日期的1月1日0时0分0秒
	 * @param date 需要处理的日期
	 * @return 当月的1月1日
	 * @since 2013-10-18
	 */
	public static Date toFirstDateOfYear(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_YEAR, 1);
		return c.getTime();
	}
	
	/**
	 * 获取指定日期的00:00:00时间点
	 * @param date 待获取时间点的时间
	 * @return 某日期的00:00:00
	 * @since 2013-11-11
	 */
	public static Date startOfDay(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return c.getTime();
	}
	
	/**
	 * 获取指定日期的23:59:59时间点
	 * @param date 待获取时间点的时间
	 * @return 某日期的23:59:59
	 * @since 2013-11-11
	 */
	public static Date endOfDay(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE,59);
		c.set(Calendar.SECOND, 59);
		return c.getTime();
	}
	
	/**
	 * 向集合中连续添加指定对象n次
	 * @param c 待添加对象的集合
	 * @param num 添加次数
	 * @param obj 添加的对象
	 * @since 2013-10-23
	 */
	public static <T> void fillList(Collection<T> c,int num,T obj){
		if(c == null)
			return;
		for(int x = 0;x < num;x++)
			c.add(obj);
	}
	
	/**
	 * 向集合中连续添加指定类型的对象n次
	 * @param c 待添加对象的集合
	 * @param num 添加次数
	 * @param clazz 对象类型
	 * @since 2013-10-23
	 */
	public static <T> void fillList(Collection<T> c,int num,Class<T> clazz){
		if(c == null)
			return;
		for(int x = 0;x < num;x++){
			try {
				c.add(clazz.newInstance());
			} catch (Exception e) {
				CYFLog.error("实例化对象失败",e);
				c.add(null);
			}
		}
	}
	
	/**
	 * 获取Map中的Values，并存如Collection中
	 * @param maps 待处理的map
	 * @return map中value集合
	 * @since 2013-10-24
	 */
	public static <T> Collection<T> mapToCollection(Map<?, T> maps){
		if(maps == null || maps.size() == 0)
			return null;
		Collection<T> ts = new ArrayList<T>();
		for(T t : maps.values()){
			ts.add(t);
		}
		return ts;
	}
	
	/**
	 * 获取Map中的Values，并存如List中
	 * @param maps 待处理的map
	 * @return map中value集合
	 * @since 2013-10-24
	 */
	public static <T> List<T> mapToList(Map<?, T> maps){
		if(maps == null || maps.size() == 0)
			return null;
		List<T> ts = new ArrayList<T>();
		for(T t : maps.values()){
			ts.add(t);
		}
		return ts;
	}
	/**
	 * 传入一个object类型的参数，如果该参数不为null则该参数被转换成string输出，如果该参数为
	 * null则返回空字符
	 * @param str
	 * @return String字符串
	 */
	public static String cutNull(Object str){
		if(str == null){
			return "";
		}
		return String.valueOf(str);
	}
	  //前补补长度
	  public static String makeLength(String value, int len) {
	    String returnStr = value.trim();
	    try {
	      int i = returnStr.length();
	      while (i < len) {
	        returnStr = "0" + returnStr;
	        i++;
	      }
	      return returnStr;
	    }
	    catch (Exception e) {
	      return null;
	    }
	  }
	//前补补长度
	  public static String makeLengthSq(String value, int len) {
	    String returnStr = value.trim();
	    try {
	      int i = returnStr.length();
	      while (i < len) {
	        returnStr = " " + returnStr;
	        i++;
	      }
	      return returnStr;
	    }
	    catch (Exception e) {
	      return null;
	    }
	  }
	  //前补补长度
	  public static String makeLengthNull(String value, int len) {
	   if(value == null){
	    value ="";
	   }
	    String returnStr = value.trim();
	    try {
	      int i = returnStr.length();
	      while (i < len) {
	        returnStr = "0" + returnStr;
	        i++;
	      }
	      return returnStr;
	    }
	    catch (Exception e) {
	      return null;
	    }
	  }
	  /**
	   * 去除左边多余的空格。
	   * @param value 待去左边空格的字符串
	   * @return 去掉左边空格后的字符串
	   * @since  0.6
	   */
	  public static String trimLeft(String value) {
	    String result = value;
	    if(result == null) return result;
	    char ch[] = result.toCharArray();
	    int index = -1;
	    for(int i=0; i < ch.length ; i++) {
	      if(Character.isWhitespace(ch[i])) {
	        index = i;
	      }
	      else {
	        break;
	      }
	    }
	    if(index != -1) {
	      result = result.substring(index+1);
	    }
	    return result;
	  }
	  /**
	   * 去除右边多余的空格。
	   * @param value 待去右边空格的字符串
	   * @return 去掉右边空格后的字符串
	   * @since  0.6
	   */
	  public static String trimRight(String value) {
	    String result = value;
	    if(result == null) return result;
	    char ch[] = result.toCharArray();
	    int endIndex = -1;
	    for(int i=ch.length-1; i > -1; i--) {
	      if(Character.isWhitespace(ch[i])) {
	        endIndex = i;
	      }
	      else {
	        break;
	      }
	    }
	    if(endIndex != -1) {
	      result = result.substring(0, endIndex);
	    }
	    return result;
	  }
	public static Double converDouble(String str) throws Exception{
		if(!isBlank(str)){
			return Double.parseDouble(str);
		}else{
			return null;
		}
	}
	  
}

