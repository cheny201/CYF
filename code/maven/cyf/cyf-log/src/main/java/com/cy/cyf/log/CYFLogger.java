package com.cy.cyf.log;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.apache.log4j.spi.LoggingEvent;

/**
 * 
 * @author ChenY201
 * @date 2015年6月2日
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
