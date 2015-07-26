package com.cy.cyf.util.encrypt;

import com.cy.cyf.core.Constant;

public class EncryptDTO {
	
	private int type;
	private String str;
	private String encoding = Constant.ENCODING;
	private String salt;
	private String token;
	
	public EncryptDTO(int type, String str) {
		this.type = type;
		this.str = str;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
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
