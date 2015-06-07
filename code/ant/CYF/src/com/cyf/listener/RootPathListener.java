package com.cyf.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * 设置RootPath
 * @author ChenY201
 * @version 1.0
 * @created 2014-11-12 下午8:29:51
 */
public class RootPathListener implements ServletContextListener {

	static Logger logger = LogManager.getLogger(RootPathListener.class);
	/**
	 * Default constructor.
	 */
	public RootPathListener() {
		logger.info("----------------RootPathListener---------------");
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent sc) {
		logger.info("----------------setRootPath---------------");
		String rootPath = sc.getServletContext().getInitParameter("rootPath");
		if (StringUtils.isBlank(rootPath)) {
			logger.error("Can not get rootPath");
		}
		logger.debug("rootPath:" + rootPath);
		sc.getServletContext().setAttribute("rootPath", rootPath);
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent sc) {
		sc.getServletContext().removeAttribute("rootPath");
		logger.info("----------------RootPathListener Destroyed---------------");
	}
}
