package com.cy.test;

import com.cy.cyf.embed.server.tomcat.EmbeddedTomcat;

public class TomcatTest {
	
	public static void main(String[] args) throws Exception {
		String contextPath = "/web";
		String path = args[0]+"/web";
		System.out.println(path);
//		String path = "E:/workspace/self001/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/web";
		EmbeddedTomcat embeddedTomcat = new EmbeddedTomcat(8080, contextPath,path);
		embeddedTomcat.start();
	}

}
