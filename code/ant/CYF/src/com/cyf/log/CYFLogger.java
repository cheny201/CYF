package com.cyf.log;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.apache.log4j.spi.LoggingEvent;

/**
 * CYF日志类
 * @author ChenY201
 * @version 1.0
 * @created 2014-11-12 下午8:30:55
 */
public class CYFLogger extends Logger{
	
	private static final String FQCN = CYFLog.class.getName();

	protected CYFLogger(String name) {
	    super(name);
	}
	
	protected void forcedLog(String fqcn, Priority level, Object message, Throwable t) {
	   callAppenders(new LoggingEvent(FQCN, this, level, message, t));
	}
}
