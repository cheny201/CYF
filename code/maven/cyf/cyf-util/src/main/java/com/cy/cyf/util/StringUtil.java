package com.cy.cyf.util;

import java.text.MessageFormat;

public class StringUtil {

	/**
	 * 使用args替换字符串中的占位符
	 * 
	 * @param pattern
	 *            字符串格式${索引值}
	 * @param args
	 *            替换的值
	 * @return
	 */
	public static String stringFormat(String pattern, Object... args) {
		return MessageFormat.format(pattern, args);
	}

}
