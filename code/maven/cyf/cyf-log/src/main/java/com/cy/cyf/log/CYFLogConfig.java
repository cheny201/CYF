package com.cy.cyf.log;

import org.apache.log4j.Level;

/**
 * 日志邮件配置
 * 
 * @author ChenY201
 * @date 2015年7月10日
 */
public class CYFLogConfig {
	
	private static String fromAddress;//发件人
	private static String userName;//用户名
	private static String password;//密码
	private static String host;//服务器地址
	private static int port=25;//服务器地址
	private static String protocol="smtp";
	
	private static String subject;//主题
	private static String toAddress;//收件人
	private static String ccAddress;//抄送人
	private static String bccAddress;//暗送人
	
	private static Level level;//发送日志的级别
	private static int bufferSize=0;//日志文件大小（单位kb）
	
	
	public static Level getLevel() {
		return level;
	}
	public static void setLevel(Level level) {
		CYFLogConfig.level = level;
	}
	public static String getProtocol() {
		return protocol;
	}
	public static void setProtocol(String protocol) {
		CYFLogConfig.protocol = protocol;
	}
	public static int getPort() {
		return port;
	}
	public static void setPort(int port) {
		CYFLogConfig.port = port;
	}
	public static String getFromAddress() {
		return fromAddress;
	}
	public static void setFromAddress(String fromAddress) {
		CYFLogConfig.fromAddress = fromAddress;
	}
	public static String getUserName() {
		return userName;
	}
	public static void setUserName(String userName) {
		CYFLogConfig.userName = userName;
	}
	public static String getPassword() {
		return password;
	}
	public static void setPassword(String password) {
		CYFLogConfig.password = password;
	}
	public static String getHost() {
		return host;
	}
	public static void setHost(String host) {
		CYFLogConfig.host = host;
	}
	public static String getToAddress() {
		return toAddress;
	}
	public static void setToAddress(String toAddress) {
		CYFLogConfig.toAddress = toAddress;
	}
	public static String getCcAddress() {
		return ccAddress;
	}
	public static void setCcAddress(String ccAddress) {
		CYFLogConfig.ccAddress = ccAddress;
	}
	public static String getBccAddress() {
		return bccAddress;
	}
	public static void setBccAddress(String bccAddress) {
		CYFLogConfig.bccAddress = bccAddress;
	}
	public static String getSubject() {
		return subject;
	}
	public static void setSubject(String subject) {
		CYFLogConfig.subject = subject;
	}
	public static int getBufferSize() {
		return bufferSize;
	}
	public static void setBufferSize(int bufferSize) {
		CYFLogConfig.bufferSize = bufferSize;
	}
}
