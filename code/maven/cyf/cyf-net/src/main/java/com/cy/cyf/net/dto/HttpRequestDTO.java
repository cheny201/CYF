package com.cy.cyf.net.dto;

import java.io.File;
import java.util.Map;

import org.apache.http.HttpHost;

public class HttpRequestDTO {
	
	private String url;
	private String method;
	private Map<String, String> params;
	private Map<String, File> files;
	private AuthDTO authDTO;
	private HttpHost proxy;
	
	public HttpRequestDTO(String url, String method, Map<String, String> params) {
		this.url = url;
		this.method = method;
		this.params = params;
	}
	
	public HttpRequestDTO(String url, String method,
			Map<String, String> params, Map<String, File> files,
			boolean needLogin) {
		this.url = url;
		this.method = method;
		this.params = params;
		this.files = files;
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
	public Map<String, String> getParams() {
		return params;
	}
	public void setParams(Map<String, String> params) {
		this.params = params;
	}
	public Map<String, File> getFiles() {
		return files;
	}
	public void setFiles(Map<String, File> files) {
		this.files = files;
	}
	public AuthDTO getAuthDTO() {
		return authDTO;
	}
	public void setAuthDTO(AuthDTO authDTO) {
		this.authDTO = authDTO;
	}
	public HttpHost getProxy() {
		return proxy;
	}
	public void setProxy(HttpHost proxy) {
		this.proxy = proxy;
	}

}
