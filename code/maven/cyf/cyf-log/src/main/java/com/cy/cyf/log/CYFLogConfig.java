package com.cy.cyf.log;

public class CYFLogConfig {
	
	public static boolean isSendMail;

	public static boolean isSendMail() {
		return isSendMail;
	}

	public static void setSendMail(boolean isSendMail) {
		CYFLogConfig.isSendMail = isSendMail;
	}
	
}
