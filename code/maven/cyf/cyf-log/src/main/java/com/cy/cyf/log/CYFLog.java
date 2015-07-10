package com.cy.cyf.log;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.net.SMTPAppender;
import org.apache.log4j.varia.LevelRangeFilter;
/**
 * 日志工具类
 * 
 * @author ChenY201
 * @date 2015年6月2日
 */
public class CYFLog{

    private static Logger log = Logger.getLogger(CYFLog.class.getName(),new CYFLoggerFactory());
    
    static{
    	
		if(CYFLogConfig.getFromAddress() != null){
			log.debug("Log4j添加邮件功能--------------开始");
			SMTPAppender appender = new SMTPAppender();  
		        try {  
		        	appender.setName("SMTPAppender");
		        	
		            appender.setSMTPUsername(CYFLogConfig.getUserName());  
		            appender.setSMTPPassword(CYFLogConfig.getPassword());  
		            appender.setTo(CYFLogConfig.getToAddress());  
		            appender.setFrom(CYFLogConfig.getFromAddress());  
		            appender.setSMTPHost(CYFLogConfig.getHost());  
		            appender.setSubject(CYFLogConfig.getSubject());
		            appender.setBcc(CYFLogConfig.getBccAddress());
		            appender.setCc(CYFLogConfig.getCcAddress());
		            appender.setSMTPPort(CYFLogConfig.getPort());
		            appender.setSMTPProtocol(CYFLogConfig.getProtocol());
		            appender.setThreshold(CYFLogConfig.getLevel());//指定日志消息的输出最低级别
		            
		            //设置日志格式
		            PatternLayout patternLayout = new PatternLayout();
		            patternLayout.setConversionPattern("[%d{HH:mm:ss}] [%p] (%F:%L) %m%n");
		            appender.setLayout(patternLayout);
		            
		            //设置日至级别过滤
		            LevelRangeFilter filter = new LevelRangeFilter();
		            filter.setLevelMax(CYFLogConfig.getLevel());
		            filter.setLevelMin(CYFLogConfig.getLevel());
		            appender.addFilter(filter);
		            
		            appender.setBufferSize(CYFLogConfig.getBufferSize());
		            appender.setSendOnClose(true);
		            appender.activateOptions();
		            log.addAppender(appender);  
		        } catch (Exception e) {  
		            log.error("Printing ERROR Statements", e);  
		        } 
		        log.debug("Log4j添加邮件功能--------------结束");
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
