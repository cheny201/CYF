package com.cy.cyf.embed.server.tomcat;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import com.cy.cyf.embed.server.BaseServer;
import com.cy.cyf.log.CYFLog;

public class EmbeddedTomcat extends BaseServer{
	
	private Tomcat tomcat = new Tomcat();
	
	public EmbeddedTomcat(int port, String contextPath, String path) {
		super(port, contextPath, path);
	}

	public void start(){
		try {
			long start = System.currentTimeMillis();
			tomcat.setPort(this.port);
			tomcat.addWebapp(this.contextPath, this.path);
			tomcat.start();
			long end = System.currentTimeMillis();
			CYFLog.info("Tomcat started ["+(end-start)+"ms]...........");
			tomcat.getServer().await();
		} catch (Exception e) {
			CYFLog.error("启动Tomcat异常",e);
		}
	}

	public void stop(){
		try {
			tomcat.stop();
			CYFLog.info("Tomcat stoped.................");
		} catch (LifecycleException e) {
			CYFLog.error("停止Tomcat异常",e);
		}
	}
}
