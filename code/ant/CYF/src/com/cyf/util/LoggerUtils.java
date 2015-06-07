package com.cyf.util;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import com.cyf.config.Constants;

/**
 * 日志工具类
 * @author ChenY201
 * @version 1.0
 * @created 2014-11-12 下午8:20:06
 */
public class LoggerUtils {
	
	public static void main(String[] args) {
		Logger log = LoggerUtils.getLogger("F:/logs/ThreadLog/cc.log", "aa");
		log.info("ssss");
	}
	
	/**
	 * 生成日志类
	 * @param path 日志路径
	 * @param name 日志名
	 * @return
	 */
	public static <T> Logger getLogger(String path,String name) {
		// 生成新的Logger
		// 如果已經有了一個Logger實例返回現有的
		Logger logger = Logger.getLogger(name);
		// 清空Appender。特別是不想使用現存實例時一定要初期化
		logger.removeAllAppenders();
		// 設定Logger級別。
		logger.setLevel(Level.INFO);
		// 設定是否繼承父Logger。
		// 默認為true。繼承root輸出。
		// 設定false後將不輸出root。
		logger.setAdditivity(true);
		// 生成新的Appender
		FileAppender appender = new FileAppender();
		PatternLayout layout = new PatternLayout();
		// log的输出形式
		String conversionPattern = "[%d{HH:mm:ss}] [%p] %m%n";
		layout.setConversionPattern(conversionPattern);
		appender.setLayout(layout);
		// log输出路径
		// 这里使用了环境变量[catalina.home]，只有在tomcat环境下才可以取到
		appender.setFile(path);
		// log的文字码
		appender.setEncoding(Constants.ENCODING);
		// true:在已存在log文件后面追加 false:新log覆盖以前的log
		appender.setAppend(true);
		// 适用当前配置
		appender.activateOptions();
		// 将新的Appender加到Logger中
		logger.addAppender(appender);
		return logger;
	}
}
