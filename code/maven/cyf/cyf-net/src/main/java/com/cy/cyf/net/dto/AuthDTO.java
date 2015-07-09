package com.cy.cyf.net.dto;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class AuthDTO extends Authenticator{
	
	private String userName;
	private String password;
	
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(userName, password);
	}
	
	public AuthDTO(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
