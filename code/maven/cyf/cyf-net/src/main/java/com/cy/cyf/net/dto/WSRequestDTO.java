package com.cy.cyf.net.dto;

import com.cy.cyf.core.Constant;

public class WSRequestDTO {
	
	private String url;//请求地址
	private String method;//请求方法名
	private Object[] param;//请求参数
	private AuthDTO authDTO;//认证信息
	private int connectTimeout;//请求超时时间ms
	private int receiveTimeout;//响应超时时间ms
	
	public WSRequestDTO(String url, String method) {
		this.url = url;
		this.method = method;
	}
	public WSRequestDTO(String url, String method, Object[] param) {
		this.url = url;
		this.method = method;
		this.param = param;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public Object[] getParam() {
		return param;
	}
	public void setParam(Object[] param) {
		this.param = param;
	}
	public int getConnectTimeout() {
		return connectTimeout == 0?Constant.WS_TIMEOUT:connectTimeout;
	}
	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}
	public int getReceiveTimeout() {
		return receiveTimeout == 0?Constant.WS_TIMEOUT:receiveTimeout;
	}
	public void setReceiveTimeout(int receiveTimeout) {
		this.receiveTimeout = receiveTimeout;
	}
	public AuthDTO getAuthDTO() {
		return authDTO;
	}
	public void setAuthDTO(AuthDTO authDTO) {
		this.authDTO = authDTO;
	}
	

}
