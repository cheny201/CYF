package com.cy.project.template.webservice.dto;

public class Request {
	
	private RequestHead head;
	private RequestBody body;
	public RequestHead getHead() {
		return head;
	}
	public void setHead(RequestHead head) {
		this.head = head;
	}
	public RequestBody getBody() {
		return body;
	}
	public void setBody(RequestBody body) {
		this.body = body;
	}
	
}
