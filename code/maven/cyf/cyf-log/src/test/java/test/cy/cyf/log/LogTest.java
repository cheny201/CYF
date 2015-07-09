package test.cy.cyf.log;

import com.cy.cyf.log.CYFLog;
import com.cy.cyf.log.CYFLogConfig;

public class LogTest {
	
	public static void main(String[] args) {
		CYFLogConfig.setSendMail(true);
		CC.ss();
		CYFLog.error("1");
		CYFLog.error("2");
		CYFLog.error("3测试");
	}
	
}
