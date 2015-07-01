package test.cy.cyf.web;

import com.cy.cyf.embed.server.tomcat.EmbeddedTomcat;

public class TomcatTest {
	
	public static void main(String[] args) throws Exception {
		String contextPath = "/web";
		String path = "E:/workspace/self001/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/web";
		
		EmbeddedTomcat embeddedTomcat = new EmbeddedTomcat(8080, contextPath,path);
		embeddedTomcat.start();
	}

}
