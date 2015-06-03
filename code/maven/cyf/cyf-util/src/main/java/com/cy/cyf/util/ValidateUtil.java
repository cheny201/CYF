package com.cy.cyf.util;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 校验工具类
 * @author ChenY201
 * @version 1.0
 * @created 2015-5-23 下午5:21:22
 */
public class ValidateUtil {
	
	/**
	 * 非空校验
	 * @param obj 需要校验的对象
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean isEmpty(Object obj){
		if(obj == null){
			return true;
		}
		if(obj instanceof String){
			String str = (String) obj;
			return ((str.trim().length() == 0) || (str.trim().equalsIgnoreCase("null")));
		}else if(obj instanceof StringBuffer){
			String str = ((StringBuffer) obj).toString();
			return ((str.trim().length() == 0) || (str.trim().equalsIgnoreCase("null")));
		}else if(obj instanceof List){
			List<Object> ls = (List<Object>)obj;
			return ls.isEmpty();
		}else if(obj instanceof Map){
			Map<Object,Object> map = (Map<Object,Object>)obj;
			return map.isEmpty();
		}else if(obj instanceof Set){
			Set<Object> set = (Set<Object>)obj;
			return set.isEmpty();
		}else{
			return false;
		}
	}
	
	/**
	 * 数字校验
	 * @param str 需要校验的字符串
	 * @return
	 */
	public static boolean isNumber(String str){
		return isInteger(str) || isDecimal(str);
	}
	
	/**
	 * 整数校验
	 * @param str 需要校验的字符串
	 * @return
	 */
	public static boolean isInteger(String str){
		return pattern(str,"^[-+]?\\d+$");
	}

	/**
	 * 小数校验
	 * @param str 需要校验的字符串
	 * @return
	 */
	public static boolean isDecimal(String str){
		return pattern(str,"^[-+]?\\d+\\.\\d+$");
	}
	
	/**
	 * 中文校验
	 * @param str 
	 * @return
	 */
	public static boolean isChinese(String str){
		return pattern(str,"^[\u4e00-\u9fa5]+$");
	}
	
	/**
	 * 电子邮箱地址校验
	 * @param str 需要校验的字符串
	 * @return
	 */
	public static boolean isEmail(String str){
		return pattern(str,"^[a-zA-Z0-9]+_?@\\[a-zA-Z0-9]+\\.[a-zA-Z]+$");
	}
	
	/**
	 * 字符串长度校验
	 * @param str 需要校验的字符串
	 * @param min 最小长度
	 * @param max 最大长度
	 * @return
	 */
	public static boolean checkLength(String str,int min,int max){
		if(!isEmpty(str)){
			if(str.length() >= min && str.length() <= max){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 校验身份证号
	 * 
	 * @param str
	 *            身份证
	 * @param year
	 *            出生年
	 * @param month
	 *            出生月
	 * @param day
	 *            出生日
	 * @param sex
	 *            性别 （0-女；1-男）
	 * @return int 	0 成功；
	 * 				1身份证校验失败
	 * 				2证件号码中的出生日期与录入的出生日期不一致
	 * 				3证件号码中的性别与录入的性别不一致
	 */
	public static int checkIDCard(String str, String year, String month, String day,
			String sex){
		HashMap<String, String> hashMap = IDCardValidate.validate(str);
		if ("1".equals(hashMap.get("status"))) {
			String idCardYear = hashMap.get("year");
			String idCardMonth = hashMap.get("month");
			String idCardDay = hashMap.get("day");
			String idCardSex = hashMap.get("sex");

			if (!idCardYear.equals(year) || !idCardMonth.equals(month) || !idCardDay.equals(day)) {
				return 2;
			}
			if (!idCardSex.equals(sex)) {
				return 3;
			}
			return 0;
		}else{
			return 1;
		}
	}
	
	/**
	 * 日期校验
	 * @param str 需要校验的字符串
	 * @param format 日期格式
	 * @return
	 */
    public static boolean isDate(String str,String format){
    	try {
			new SimpleDateFormat(format).parse(str);
			return true;
		} catch (java.text.ParseException e) {
			return false;
		}
    }
	
	/**
	 * 根据给定匹配模式进行校验
	 * @param str 需要校验的字符串
	 * @param pattern 匹配模式
	 * @return
	 */
	public static boolean pattern(String str,String pattern){
		if(!isEmpty(str)){
			return str.matches(pattern);
		}
		return false;
	}

	public static void main(String[] args) {
		System.out.println(isInteger("-100"));
		System.out.println(isDecimal("+100.0"));
		System.out.println(isChinese("测测"));
		System.out.println(checkIDCard("321121199005230010","1990","05","23","0"));
	}
	
}