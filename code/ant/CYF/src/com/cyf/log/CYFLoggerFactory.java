package com.cyf.log;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

/**
 * CYFLogger工厂类
 * @author ChenY201
 * @version 1.0
 * @created 2014-11-12 下午8:31:40
 */
public class CYFLoggerFactory implements LoggerFactory {

	/* (non-Javadoc)
	 * @see org.apache.log4j.spi.LoggerFactory#makeNewLoggerInstance(java.lang.String)
	 */
	public Logger makeNewLoggerInstance(String name) {
		return new CYFLogger(name);
	}

}
