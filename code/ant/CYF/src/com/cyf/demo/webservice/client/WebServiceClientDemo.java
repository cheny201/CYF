package com.cyf.demo.webservice.client;

import com.cyf.base.BaseWebServiceClient;
import com.cyf.demo.webservice.client.dto.CertiNotice;
import com.cyf.demo.webservice.client.dto.CertiNoticeRequest;
import com.cyf.demo.webservice.client.dto.CertiNoticeResponse;

/**
 * WebService客户端示例
 * @author ChenY201
 * @version 1.0
 * @created 2014-11-12 下午8:59:28
 */
public class WebServiceClientDemo extends BaseWebServiceClient{
	
	/**
	 * 用户查询
	 * @param username 用户名
	 * @param password 密码
	 * @return
	 */
	public CertiNoticeResponse queryUser(String username,String password){
		CertiNotice service = super.createService("http://127.0.0.1:8080/CYF/webservice/certiNotice", CertiNotice.class);
		CertiNoticeRequest certiNoticeRequest = new CertiNoticeRequest();
		return service.certinotice(certiNoticeRequest);
	}

}
