package test.cy.cyf.log;

import org.apache.log4j.Level;

import com.cy.cyf.log.CYFLog;
import com.cy.cyf.log.CYFLogConfig;

public class LogTest {
	
	public static void main(String[] args) {
		CYFLogConfig.setFromAddress("");
		CYFLogConfig.setToAddress("cheny201@163.com");
		CYFLogConfig.setHost("smtp.qq.com");
		CYFLogConfig.setUserName("");
		CYFLogConfig.setPassword("");
		CYFLogConfig.setSubject("Log4j-项目预警");
		
		CYFLogConfig.setLevel(Level.FATAL);
		CYFLogConfig.setBufferSize(1);
		
		CC.ss();
		CYFLog.error("1");
		CYFLog.error("2");
		CYFLog.error("3测试");
	}
	
}
