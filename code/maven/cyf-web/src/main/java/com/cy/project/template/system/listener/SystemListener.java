package com.cy.project.template.system.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.cy.cyf.log.CYFLog;
import com.cy.cyf.log.CYFLogConfig;

public class SystemListener implements ServletContextListener,HttpSessionListener{

	@Override
	public void contextInitialized(ServletContextEvent event) {
		CYFLog.debug("程序运行目录["+event.getServletContext().getRealPath("/")+"]");
		String rootPath = event.getServletContext().getInitParameter("rootPath");
		event.getServletContext().setAttribute("rootPath", rootPath);
		CYFLogConfig.setSendMail(true);
		CYFLog.debug("设置RootPath["+rootPath+"]");
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		event.getServletContext().removeAttribute("rootPath");
		CYFLog.info("----------------SystemListener Destroyed---------------");
	}
	
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		CYFLog.debug("创建Session,id["+event.getSession().getId()+"]");
	}
	
	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		CYFLog.debug("销毁Session,id["+event.getSession().getId()+"]");
	}

}
