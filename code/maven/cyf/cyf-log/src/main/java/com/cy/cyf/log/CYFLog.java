package com.cy.cyf.log;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.net.SMTPAppender;
/**
 * 日志工具类
 * 
 * @author ChenY201
 * @date 2015年6月2日
 */
public class CYFLog{

    private static Logger log = Logger.getLogger(CYFLog.class.getName(),new CYFLoggerFactory());
    
    static{
    	System.out.println("--------------");
		if(CYFLogConfig.isSendMail){
			SMTPAppender appender = new SMTPAppender();  
		        try {  
		            appender.setSMTPUsername("cheny201");  
		            appender.setSMTPPassword("chenying201.");  
		            appender.setTo("404369230@qq.com");  
		            appender.setFrom("cheny201@163.com");  
		            // SMTP服务器 smtp.163.com  
		            appender.setSMTPHost("smtp.163.com");  
		            appender.setLocationInfo(true);  
		            appender.setSubject("Test Mail From Log4J");  
		            appender.setLayout(new PatternLayout()); 
		            appender.setThreshold(Level.DEBUG);
		            appender.setBufferSize(1);
		            appender.setEvaluator(new CYFTriggeringEventEvaluator());
//		            appender.setSendOnClose(true);
		            appender.activateOptions(); 
		            log.addAppender(appender);  
		        } catch (Exception e) {  
		            log.error("Printing ERROR Statements", e);  
		        }  
		}
	}
    
    public static void trace(String msg) {
        log.trace(msg);
    }

    public static void trace(String msg, Throwable t) {
        log.trace(msg, t);
    }

    public static void debug(String msg) {
        log.debug(msg);
    }

    public static void debug(String msg, Throwable t) {
        log.debug(msg, t);
    }

    public static void info(String msg) {
        log.info(msg);
    }

    public static void info(String msg, Throwable t) {
        log.info(msg, t);
    }

    /**
     * info日志规范
     * @param mokuai 模块名
     * @param leiming 类名
     * @param msg 信息
     */
    public static void info(String mokuai, String leiming, String msg) {
        String Message = "{CYF}-{" + mokuai + "}-{" + leiming + "}-【" + msg + "】";// 日志信息
        log.info(Message);
    }

    /**
     * error日志规范
     * @param mokuai 模块名
     * @param leiming 类名
     * @param msg 信息
     * @param e 异常
     */
    public static void error(String mokuai, String leiming, String msg, Throwable e) {
        String Message = "{CYF}-{" + mokuai + "}-{" + leiming + "}-【" + msg + "】";// 日志信息
        log.error(Message, e);
    }


    public static void warn(String msg) {
        log.warn(msg);
    }

    public static void warn(String msg, Throwable t) {
        log.warn(msg, t);
    }

    public static void error(String msg) {
        log.error(msg);
    }

    public static void error(String msg, Throwable t) {
        log.error(msg, t);
    }

    /**
     * 从当前Category开始查找非null的Level，若全为空则返回Root Category中的Level
     * 
     * @return org.apache.log4j.Level
     * @since 2013-9-2
     */
    public static Level getLevel() {
        return log.getEffectiveLevel();
    }
}
