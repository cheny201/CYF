package com.cy.cyf.core;

public final class Constant {
	
	public static final String ENCODING = "UTF-8";
	public static final int FTP_TIMEOUT = 60000;//单位:ms
	public static final int WS_TIMEOUT = 60000;//单位:ms
	public static final int TCP_SERVER_SIZE = 50;
	public static final int TCP_POOL_SIZE = 50;
	
	public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final int IO_SIZE = 1024*1024;
	
	public static enum SECURITY_ROLE{
		
		LOGIN_VALID("LOGIN_VALID","登陆并且拥有权限才能访问"),
		LOGIN("LOGIN","登录才能访问"),
		NONE("NONE","不验证");
		
		private final String code;
		private final String label;
		
		SECURITY_ROLE(String code,String label){
			this.code = code;
			this.label = label;
		}
		
		public String getCode(){
			return this.code;
		}

		public String getLabel() {
			return label;
		}

	}
	
	public static enum ERROR_CODE{
		
		NO_USER("100001","用户不存在"),
		BAD_PASSWORD("100002","无效的密码");
		
		private final String code;
		private final String label;
		
		ERROR_CODE(String code,String label){
			this.code = code;
			this.label = label;
		}
		
		public String getCode(){
			return this.code;
		}

		public String getLabel() {
			return label;
		}

	}
}
