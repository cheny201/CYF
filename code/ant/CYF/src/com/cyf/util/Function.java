package com.cyf.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.security.MessageDigest;

/**
 * 数据、字符串处理工具类
 * @author ChenY201
 * @version 1.0
 * @created 2014-11-12 下午8:34:20
 */
public class Function implements Serializable {
	private static final long serialVersionUID = 1L;

	public Function() {

	}

	/**
	 * 判断参数是否为空，为空则返回""，否则返回其值
	 * 
	 * @param
	 * @return 字符串
	 */
	public String getString(String sSource) {
		String sReturn = "";
		if (sSource != null) {
			sReturn = sSource;
		}
		return sReturn;
	}

	/**
	 * 判断参数是否为0，为0则返回""，否则返回其值
	 * 
	 * @param iSource
	 * @return 字符串
	 */
	public static String getString(int iSource) {
		if (iSource == 0) {
			return "";
		} else {
			return "" + iSource;
		}
	}

	/**
	 * 转码：GBK ----> iso-8859-1
	 * 
	 * @param s
	 * @return 
	 */
	public static String GBKtoISO(String s) {
		try {
			if (s == null) {
				return "";
			} else {
				s = s.trim();
				s = new String(s.getBytes("GBK"), "iso-8859-1");
				return s;
			}
		} catch (Exception e) {
		}
		return s;
	}

	/**
	 * 转码：iso-8859-1 ----> GBK
	 * 
	 * @param s
	 * @return 
	 */
	public static String ISOtoGBK(String s) {
		try {
			if (s == null) {
				return "";
			} else {
				s = s.trim();
				s = new String(s.getBytes("iso-8859-1"), "GBK");
				return s;
			}
		} catch (Exception e) {
		}
		return s;
	}

	/**
	 * 转码： 从UTF-8到GBK
	 * 
	 * @param s
	 * @return 
	 */
	public static String UTF8toGBK(String s) {
		try {
			if (s == null) {
				return "";
			} else {
				s = s.trim();
				s = new String(s.getBytes("UTF-8"), "GBK");
				return s;
			}
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 转码： 从GBK到UTF-8
	 * 
	 * @param s
	 * @return 
	 */
	public static String GBK2UTF8(String s) {
		try {
			if (s == null) {
				return "";
			} else {
				s = new String(s.getBytes("GBK"), "UTF-8");
				return s;
			}
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 判断参数是否为空，为空则返回一个长度为0的字符串数组，否则返回其值
	 * 
	 * @param 
	 * @return 
	 */
	public String[] getArray(String[] aSource) {
		String aReturn[] = new String[0];
		if (aSource != null) {
			aReturn = aSource;
		}
		return aReturn;
	}

	/**
	 * 判断参数是否为空，为空则返回0,不为空则返回其整型值
	 * 
	 * @param 
	 * @return 
	 */
	public int getInt(String sSource) {
		int iReturn = 0;
		if (sSource != null && !sSource.equals("")) {
			iReturn = Integer.parseInt(sSource);
		}
		return iReturn;
	}

	/**
	 * 判断参数是否为空，为空则返回一个长度为0的整形数组，否则返回其值
	 * 
	 * @param 
	 * @return 
	 */
	public int[] getIntArray(String[] aSource) {
		int iReturn[] = new int[0];
		if (aSource != null) {
			iReturn = new int[aSource.length];
			for (int i = 0; i < aSource.length; i++) {
				iReturn[i] = Integer.parseInt(aSource[i]);
			}
		}
		return iReturn;
	}

	/**
	 * 判断参数是否为空，为空则返回0,不为空则返回其整型值
	 * 
	 * @param 
	 * @return 
	 */
	public double getDouble(String sSource) {
		double dReturn = 0.00;
		if (sSource != null && !sSource.equals("")) {
			dReturn = (new Double(sSource)).doubleValue();
		}
		return dReturn;
	}

	/**
	 * 查找以逗号分隔的源字符串是否包含给定字符串
	 * 
	 * @param 
	 * @return 
	 */
	public boolean isContain(String sSource, String sItem) {
		boolean isReturn = false;
		StringTokenizer st = null;
		st = new StringTokenizer(sSource, ",");
		while (st.hasMoreTokens()) {
			if (sItem.equals(st.nextToken())) {
				isReturn = true;
				break;
			}
		}
		return isReturn;
	}

	/**
	 * 查找源字符串数组中是否包含给定字符串
	 * 
	 * @param 
	 * @return 是否包含
	 */
	public boolean isContain(String[] aSource, String sItem) {
		boolean isReturn = false;
		for (int i = 0; i < aSource.length; i++) {
			if (sItem.equals(aSource[i])) {
				isReturn = true;
				break;
			}
		}
		return isReturn;
	}

	/**
	 * 将指定字符串从源字符串中删除掉，并返回替换后的结果字符串
	 * 
	 * @param 
	 * @param 要删除的字符
	 * @return 替换后的字符串
	 */
	public String delete(String source, String subString) {
		StringBuffer output = new StringBuffer();
		// 源字符串长度
		int lengthOfSource = source.length();
		// 开始搜索位置
		int posStart = 0;
		// 搜索到老字符串的位置
		int pos;
		while ((pos = source.indexOf(subString, posStart)) >= 0) {
			output.append(source.substring(posStart, pos));
			posStart = pos + 1;
		}
		if (posStart < lengthOfSource) {
			output.append(source.substring(posStart));
		}
		return output.toString();
	}

	/**
	 * 将给定的源字符串加1 例如：“0001” 经本函数转换后返回为“0002”
	 * 
	 * @param 
	 * @return 返回字符串
	 */
	public String increaseOne(String sSource) {
		String sReturn = null;
		int iSize = 0;
		iSize = sSource.length();
		long l = (new Long(sSource)).longValue();
		l++;
		sReturn = String.valueOf(l);
		for (int i = sReturn.length(); i < iSize; i++) {
			sReturn = "0" + sReturn;
		}
		return sReturn;
	}

	/**
	 * 将给定的整数转化成字符串，结果字符串的长度为给定长度,不足位数的左端补"0" 例如val=10，len=5，那么生成的字符串为"00010"
	 * 
	 * @param val 将被转化成字符串的整数
	 * @param len 转化后的长度
	 * @return String 返回值
	 */
	public String intToStr(int val, int len) {
		String sReturn = new String();
		sReturn = String.valueOf(val);
		if (sReturn.length() < len) {
			for (int i = len - sReturn.length(); i > 0; i--) {
				sReturn = "0" + sReturn;
			}
		}
		return sReturn;
	}

	/**
	 * 将数组中的每个元素两端加上给定的符号
	 * 
	 * @param aSource 源数组
	 * @param sChar 符号
	 * @return 处理后的字符串数组
	 */
	public String[] arrayAddSign(String[] aSource, String sChar) {
		String aReturn[] = new String[aSource.length];
		for (int i = 0; i < aSource.length; i++) {
			aReturn[i] = sChar + aSource[i] + sChar;
		}
		return aReturn;
	}

	/**
	 * 将数组中的元素连成一个以逗号分隔的字符串
	 * 
	 * @param aSource 源数组
	 * @return 字符串
	 */
	public String arrayToString(int[] aSource) {
		String sReturn = "";
		for (int i = 0; i < aSource.length; i++) {
			if (i > 0) {
				sReturn += ",";
			}
			sReturn += aSource[i];
		}
		return sReturn;
	}

	/**
	 * 将数组中的元素连成一个以给定字符分隔的字符串
	 * 
	 * @param aSource 源数组
	 * @param sChar 分隔符
	 * @return 字符串
	 */
	public String arrayToString(String[] aSource, String sChar) {
		String sReturn = "";
		for (int i = 0; i < aSource.length; i++) {
			if (i > 0) {
				sReturn += sChar;
			}
			sReturn += aSource[i];
		}
		return sReturn;
	}

	/**
	 * 将两个字符串的所有元素连结为一个字符串数组
	 * 
	 * @param 
	 * @return String[]
	 */
	public String[] arrayAppend(String[] array1, String[] array2) {
		int iLen = 0;
		String aReturn[] = null;
		if (array1 == null) {
			array1 = new String[0];
		}
		if (array2 == null) {
			array2 = new String[0];
		}
		iLen = array1.length;
		aReturn = new String[iLen + array2.length];
		// 将第一个字符串数组的元素加到结果数组中
		for (int i = 0; i < iLen; i++) {
			aReturn[i] = array1[i];
		}
		// 将第二个字符串数组的元素加到结果数组中
		for (int i = 0; i < array2.length; i++) {
			aReturn[iLen + i] = array2[i];
		}
		return aReturn;
	}

	/**
	 * 将两个对象数组中的所有元素连结为一个对象数组
	 */
	public Object[] arrayAppend(Object[] array1, Object[] array2) {
		int iLen = 0;
		Object aReturn[] = null;
		if (array1 == null) {
			array1 = new Object[0];
		}
		if (array2 == null) {
			array2 = new Object[0];
		}
		iLen = array1.length;
		aReturn = new Object[iLen + array2.length];
		// 将第一个对象数组的元素加到结果数组中
		for (int i = 0; i < iLen; i++) {
			aReturn[i] = array1[i];
		}
		// 将第二个对象数组的元素加到结果数组中
		for (int i = 0; i < array2.length; i++) {
			aReturn[iLen + i] = array2[i];
		}
		return aReturn;
	}

	/**
	 * 拆分以逗号分隔的字符串,并存入String数组中
	 */
	public String[] strToArray(String sSource) {
		String aReturn[] = null;
		StringTokenizer st = null;
		st = new StringTokenizer(sSource, ",");
		aReturn = new String[st.countTokens()];
		int i = 0;
		while (st.hasMoreTokens()) {
			aReturn[i] = st.nextToken();
			i++;
		}
		return aReturn;
	}

	/**
	 * 拆分以给定分隔符分隔的字符串,并存入字符串数组中
	 */
	public static String[] strToArray(String sSource, String sChar) {
		String aReturn[] = null;
		StringTokenizer st = null;
		st = new StringTokenizer(sSource, sChar);
		int i = 0;
		aReturn = new String[st.countTokens()];
		while (st.hasMoreTokens()) {
			aReturn[i] = st.nextToken();
			i++;
		}
		return aReturn;
	}

	/**
	 * 拆分以给定分隔符分隔的字符串,并存入整型数组中
	 */
	public static int[] strToArray(String sSource, char sChar) {
		int aReturn[] = null;
		StringTokenizer st = null;
		st = new StringTokenizer(sSource, String.valueOf(sChar));
		int i = 0;
		aReturn = new int[st.countTokens()];
		while (st.hasMoreTokens()) {
			aReturn[i] = Integer.parseInt(st.nextToken());
			i++;
		}
		return aReturn;
	}

	/**
	 * 将以逗号分隔的字符串的每个元素加上单引号 如： 1000,1001,1002 --> '1000','1001','1002'
	 */
	public String addMark(String sSource) {
		String sReturn = "";
		StringTokenizer st = null;
		st = new StringTokenizer(sSource, ",");
		if (st.hasMoreTokens()) {
			sReturn += "'" + st.nextToken() + "'";
		}
		while (st.hasMoreTokens()) {
			sReturn += "," + "'" + st.nextToken() + "'";
		}
		return sReturn;
	}


	/**
	 * 对字符串进行md5加密
	 * 
	 * @param s 要加密的字符串
	 * @return md5加密后的字符串
	 */
	public final static String MD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = s.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte b = md[i];
				str[k++] = hexDigits[b >>> 4 & 0xf];
				str[k++] = hexDigits[b & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 分割字符串
	 * 
	 * @param str 原始字符串
	 * @param splitsign 分隔符
	 * @return String[] 分割后的字符串数组
	 */

	public static String[] split(String str, String splitsign) {
		int index;
		if (str == null || splitsign == null) {
			return null;
		}
		ArrayList<String> al = new ArrayList<String>();
		while ((index = str.indexOf(splitsign)) != -1) {
			al.add(str.substring(0, index));
			str = str.substring(index + splitsign.length());
		}
		al.add(str);
		return (String[]) al.toArray(new String[0]);
	}

	/**
	 * 替换字符串
	 * 
	 * @param from 原始字符串
	 * @param to 目标字符串
	 * @param source 母字符串
	 * @return String 替换后的字符串
	 */
	public static String replace(String from, String to, String source) {
		if (source == null || from == null || to == null)
			return null;
		StringBuffer str = new StringBuffer("");
		int index = -1;
		while ((index = source.indexOf(from)) != -1) {
			str.append(source.substring(0, index) + to);
			source = source.substring(index + from.length());
			index = source.indexOf(from);
		}
		str.append(source);
		return str.toString();
	}

	/**
	 * 替换字符串，能能够在HTML页面上直接显示(替换双引号和小于号)
	 * 
	 * @param str 原始字符串
	 * @return String 替换后的字符串
	 */
	public static String htmlencode(String str) {
		if (str == null) {
			return null;
		}
		return replace("\"", "&quot;", replace("<", "&lt;", str));
	}

	/**
	 * 替换字符串，将被编码的转换成原始码（替换成双引号和小于号）
	 * 
	 * @param str
	 * @return String
	 */
	public static String htmldecode(String str) {
		if (str == null) {
			return null;
		}

		return replace("&quot;", "\"", replace("&lt;", "<", str));
	}

	private static final String _BR = "<br/>";

	/**
	 * 在页面上直接显示文本内容，替换小于号，空格，回车，TAB
	 * 
	 * @param Str 原始字符串
	 * @return String 替换后的字符串
	 */
	public static String htmlshow(String str) {
		if (str == null) {
			return null;
		}

		str = replace("<", "&lt;", str);
		str = replace(" ", "&nbsp;", str);
		str = replace("\r\n", _BR, str);
		str = replace("\n", _BR, str);
		str = replace("\t", "&nbsp;&nbsp;&nbsp;&nbsp;", str);
		return str;
	}

	/**
	 * 返回指定字节长度的字符串
	 * 
	 * @param str 字符串
	 * @param length int 指定长度
	 * @return String 返回的字符串
	 */
	public static String toLength(String str, int length) {
		if (str == null) {
			return null;
		}
		if (length <= 0) {
			return "";
		}
		try {
			if (str.getBytes("GBK").length <= length) {
				return str;
			}
		} catch (Exception e) {
		}
		StringBuffer buff = new StringBuffer();

		int index = 0;
		char c;
		length -= 3;
		while (length > 0) {
			c = str.charAt(index);
			if (c < 128) {
				length--;
			} else {
				length--;
				length--;
			}
			buff.append(c);
			index++;
		}
		buff.append("...");
		return buff.toString();
	}

	/**
	 * 判断是否为整数
	 * 
	 * @param str 传入的字符串
	 * @return 是整数返回true,否则返回false
	 */
	public static boolean isInteger(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]+$");
		return pattern.matcher(str).matches();
	}

	/**
	 * 判断是否为浮点数，包括double和float
	 * 
	 * @param str 传入的字符串
	 * @return 是浮点数返回true,否则返回false
	 */
	public static boolean isDouble(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?\\d+\\.\\d+$");
		return pattern.matcher(str).matches();
	}

	/**
	 * 判断是不是合法字符
	 */
	public static boolean isLetter(String str) {
		if (str == null || str.length() < 0) {
			return false;
		}
		Pattern pattern = Pattern.compile("[\\w\\.-_]*");
		return pattern.matcher(str).matches();
	}

	/**
	 * 从指定的字符串中提取Email content 指定的字符串
	 * 
	 * @param content
	 * @return 
	 */
	public static String parse(String content) {
		String email = null;
		if (content == null || content.length() < 1) {
			return email;
		}
		// 找出含有@
		int beginPos;
		int i;
		String token = "@";
		String preHalf = "";
		String sufHalf = "";

		beginPos = content.indexOf(token);
		if (beginPos > -1) {
			// 前项扫描
			String s = null;
			i = beginPos;
			while (i > 0) {
				s = content.substring(i - 1, i);
				if (isLetter(s))
					preHalf = s + preHalf;
				else
					break;
				i--;
			}
			// 后项扫描
			i = beginPos + 1;
			while (i < content.length()) {
				s = content.substring(i, i + 1);
				if (isLetter(s))
					sufHalf = sufHalf + s;
				else
					break;
				i++;
			}
			// 判断合法性
			email = preHalf + "@" + sufHalf;
			if (isEmail(email)) {
				return email;
			}
		}
		return null;
	}

	/**
	 * 判断输入的字符串是否符合Email样式.
	 * 
	 * @param str 传入的字符串
	 * @return 是Email样式返回true,否则返回false
	 */
	public static boolean isEmail(String email) {
		if (email == null || email.length() < 1 || email.length() > 256) {
			return false;
		}
		Pattern pattern = Pattern
				.compile("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
		return pattern.matcher(email).matches();
	}

	/**
	 * 判断输入的字符串是否为纯汉字
	 * 
	 * @param str 传入的字符串
	 * @return 如果是纯汉字返回true,否则返回false
	 */
	public static boolean isChinese(String str) {
		Pattern pattern = Pattern.compile("[\u0391-\uFFE5]+$");
		return pattern.matcher(str).matches();
	}

	/**
	 * 是否为空,包括null和""
	 * 
	 * @param str
	 * @return 
	 */
	public static boolean isBlank(String str) {
		return str == null || str.trim().length() == 0;
	}

	/**
	 * 人民币转成大写
	 * 
	 * @param str 数字字符串
	 * @return String 人民币转换成大写后的字符串
	 */
	public static String hangeToBig(String str) {
		double value;
		try {
			value = Double.parseDouble(str.trim());
		} catch (Exception e) {
			return null;
		}
		char[] hunit = { '拾', '佰', '仟' }; // 段内位置表示
		char[] vunit = { '万', '亿' }; // 段名表示
		char[] digit = { '零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖' }; // 数字表示
		long midVal = (long) (value * 100); // 转化成整形
		String valStr = String.valueOf(midVal); // 转化成字符串

		String head = valStr.substring(0, valStr.length() - 2); // 取整数部分
		String rail = valStr.substring(valStr.length() - 2); // 取小数部分
		String prefix = ""; // 整数部分转化的结果
		String suffix = ""; // 小数部分转化的结果
		// 处理小数点后面的数
		if (rail.equals("00")) { // 如果小数部分为0
			suffix = "整";
		} else {
			suffix = digit[rail.charAt(0) - '0'] + "角"
					+ digit[rail.charAt(1) - '0'] + "分"; // 否则把角分转化出来
		}
		// 处理小数点前面的数
		char[] chDig = head.toCharArray(); // 把整数部分转化成字符数组
		char zero = '0'; // 标志'0'表示出现过0
		byte zeroSerNum = 0; // 连续出现0的次数
		for (int i = 0; i < chDig.length; i++) { // 循环处理每个数字
			int idx = (chDig.length - i - 1) % 4; // 取段内位置
			int vidx = (chDig.length - i - 1) / 4; // 取段位置
			if (chDig[i] == '0') { // 如果当前字符是0
				zeroSerNum++; // 连续0次数递增
				if (zero == '0') { // 标志
					zero = digit[0];
				} else if (idx == 0 && vidx > 0 && zeroSerNum < 4) {
					prefix += vunit[vidx - 1];
					zero = '0';
				}
				continue;
			}
			zeroSerNum = 0; // 连续0次数清零
			if (zero != '0') { // 如果标志不为0,则加上,例如万,亿什么的
				prefix += zero;
				zero = '0';
			}
			prefix += digit[chDig[i] - '0']; // 转化该数字表示
			if (idx > 0)
				prefix += hunit[idx - 1];
			if (idx == 0 && vidx > 0) {
				prefix += vunit[vidx - 1]; // 段结束位置应该加上段名,如万,亿
			}
		}

		if (prefix.length() > 0)
			prefix += '圆'; // 如果整数部分存在,则有圆的字样
		return prefix + suffix; // 返回正确表示
	}

	/**
	 * 去掉字符串中重复的子字符串
	 * 
	 * @param str 原字符串，如果有子字符串则用空格隔开以表示子字符串
	 * @return String 返回去掉重复子字符串后的字符串
	 */
	public static String removeSameString(String str) {
		Set<String> mLinkedSet = new LinkedHashSet<String>();
		String[] strArray = str.split(" ");// 根据空格(正则表达式)分割字符串
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < strArray.length; i++) {
			if (!mLinkedSet.contains(strArray[i])) {
				mLinkedSet.add(strArray[i]);
				sb.append(strArray[i] + " ");
			}
		}
		return sb.toString();
	}

	/**
	 * 过滤特殊字符
	 * 
	 * @param src
	 * @return 
	 */
	public static String encoding(String src) {
		if (src == null)
			return "";
		StringBuilder result = new StringBuilder();
		if (src != null) {
			src = src.trim();
			for (int pos = 0; pos < src.length(); pos++) {
				switch (src.charAt(pos)) {
				case '\"':
					result.append("&quot;");
					break;
				case '<':
					result.append("&lt;");
					break;
				case '>':
					result.append("&gt;");
					break;
				case '\'':
					result.append("&apos;");
					break;
				case '&':
					result.append("&amp;");
					break;
				case '%':
					result.append("&pc;");
					break;
				case '_':
					result.append("&ul;");
					break;
				case '#':
					result.append("&shap;");
					break;
				case '?':
					result.append("&ques;");
					break;
				default:
					result.append(src.charAt(pos));
					break;
				}
			}
		}
		return result.toString();
	}

	/**
	 * 判断是不是合法的手机号码
	 * 
	 * @param handset
	 * @return boolean
	 */
	public static boolean isHandset(String handset) {
		try {
			String regex = "^1[\\d]{10}$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(handset);
			return matcher.matches();
		} catch (RuntimeException e) {
			return false;
		}
	}

	public static void main(String args[]) {
		
		String S=Function.hangeToBig("354689.32");
		System.out.println("大写金额 = "+ S);
		
	}
}