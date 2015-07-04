package com.cy.project.template.webservice.service;

import javax.jws.WebService;

import org.springframework.stereotype.Service;

import com.cy.cyf.log.CYFLog;
import com.cy.project.template.webservice.dto.Request;
import com.cy.project.template.webservice.dto.Response;
import com.cy.project.template.webservice.dto.ResponseHead;

@Service("webServiceDemo")
@WebService(endpointInterface="com.cy.project.template.webservice.service.WebServiceDemo")
public class WebServiceDemoImpl implements WebServiceDemo {

	@Override
	public Response test1(Request request) {
		Response resp = new Response();
		ResponseHead head = new ResponseHead();
		head.setCode("1");
		resp.setHead(head);
		return resp;
	}

	@Override
	public String test2(String request) {
		CYFLog.debug("接收数据："+request);
		return "==============";
	}

	@Override
	public int test3() {
		CYFLog.debug("返回数据：0");
		return 0;
	}

}
