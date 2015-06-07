package com.cyf.log;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * CYF日志类
 * @author ChenY201
 * @version 1.0
 * @created 2014-11-12 下午8:30:29
 */
public class CYFLog {
	
	private final static Logger log = CYFLogger.getLogger(CYFLog.class.getName(),new CYFLoggerFactory());
	
	public static void trace(String msg){
		log.trace(msg);
	}
	public static void trace(String msg,Throwable t){
		log.trace(msg,t);
	}
	
	public static void debug(String msg){
		log.debug(msg);
	}
	public static void debug(String msg,Throwable t){
		log.debug(msg,t);
	}
	
	public static void info(String msg){
		log.info(msg);
	}
	public static void info(String msg,Throwable t){
		log.info(msg,t);
	}
	
	public static void warn(String msg){
		log.warn(msg);
	}
	public static void warn(String msg,Throwable t){
		log.warn(msg,t);
	}
	
	public static void error(String msg){
		log.error(msg);
	}
	public static void error(String msg,Throwable t){
		log.error(msg,t);
	}
	
	/**
	 * 从当前Category开始查找非null的Level，若全为空则返回Root Category中的Level
	 * @return  org.apache.log4j.Level
	 * @since 2013-9-2
	 */
	public static Level getLevel(){
		return log.getEffectiveLevel();
	}
}
