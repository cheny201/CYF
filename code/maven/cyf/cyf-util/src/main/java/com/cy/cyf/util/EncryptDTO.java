package com.cy.cyf.util;

import com.cy.cyf.core.Constant;

public class EncryptDTO {
	
	private String type;
	private String str;
	private String encoding = Constant.ENCODING;
	private String salt;
	private String token;
	
	public EncryptDTO(String type, String str) {
		this.type = type;
		this.str = str;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public String getEncoding() {
		return encoding;
	}
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	
	@Override
	public String toString() {
		return "EncryptDTO [type=" + type + ", str=" + str + ", encoding="
				+ encoding + ", salt=" + salt + ", token=" + token + "]";
	}

}
