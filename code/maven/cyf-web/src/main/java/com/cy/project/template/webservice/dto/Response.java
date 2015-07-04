package com.cy.project.template.webservice.dto;

public class Response {
	
	private ResponseHead head;
	private ResponseBody body;
	public ResponseHead getHead() {
		return head;
	}
	public void setHead(ResponseHead head) {
		this.head = head;
	}
	public ResponseBody getBody() {
		return body;
	}
	public void setBody(ResponseBody body) {
		this.body = body;
	}

}
