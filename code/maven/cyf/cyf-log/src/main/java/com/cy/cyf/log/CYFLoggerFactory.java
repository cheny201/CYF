package com.cy.cyf.log;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

/**
 * CYF日志工厂
 * 
 * @author ChenY201
 * @date 2015年6月2日
 */
public class CYFLoggerFactory implements LoggerFactory {

	/* (non-Javadoc)
	 * @see org.apache.log4j.spi.LoggerFactory#makeNewLoggerInstance(java.lang.String)
	 */
	public Logger makeNewLoggerInstance(String name) {
		return new CYFLogger(name);
	}

}
