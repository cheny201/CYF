package com.cy.cyf.net.mail;

import java.io.File;

import com.cy.cyf.core.exception.MailException;
import com.cy.cyf.util.ValidateUtil;

public class MailInfo {
	
	private String toAddress;//收件人
	private String charset;//字符集
	private String ccAddress;//抄送人
	private String bccAddress;//暗送人
	
	private String subject;//主题
	private File[] files;//附件
	private String content;//内容
	private boolean isHTML;//是否是html
	
	public void check(){
		if(ValidateUtil.isEmpty(this.toAddress)){
			throw new MailException("收件人不能为空");
		}
	}
	
	public boolean isHTML() {
		return isHTML;
	}
	public void setHTML(boolean isHTML) {
		this.isHTML = isHTML;
	}
	public String getToAddress() {
		return toAddress;
	}
	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}
	public String getCharset() {
		return charset;
	}
	public void setCharset(String charset) {
		this.charset = charset;
	}
	public String getCcAddress() {
		return ccAddress;
	}
	public void setCcAddress(String ccAddress) {
		this.ccAddress = ccAddress;
	}
	public String getBccAddress() {
		return bccAddress;
	}
	public void setBccAddress(String bccAddress) {
		this.bccAddress = bccAddress;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public File[] getFiles() {
		return files;
	}
	public void setFiles(File[] files) {
		this.files = files;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
