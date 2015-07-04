package com.cy.project.template.webservice.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.WebParam.Mode;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.cy.project.template.webservice.dto.Request;
import com.cy.project.template.webservice.dto.Response;

@WebService
@SOAPBinding(style=Style.DOCUMENT)
public interface WebServiceDemo {
	
	@WebMethod(operationName="test1")
	@WebResult(name="response")
	public Response test1(@WebParam(name="request",mode=Mode.IN) Request request);
	
	@WebMethod(operationName="test2")
	public String test2(@WebParam(name="request",mode=Mode.IN) String request);
	
	@WebMethod(operationName="test3")
	public int test3();

}
