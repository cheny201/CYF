package com.cy.cyf.net.webservice;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.cy.cyf.core.Constant;
import com.cy.cyf.util.ValidateUtil;

/**
 * cxf客户端调用webservice基础类
 * @author ChenYing
 * @createDate 2014-7-29 上午11:15:48
 *
 */
public class BaseClient {
	
	protected void addTimeOut(Object service) {
		addTimeOut(service,Constant.WS_TIMEOUT,Constant.WS_TIMEOUT);
	}
	
	/**
	 * 超时设置
	 * @param service
	 * @param connectTimeout
	 * @param receiveTimeout
	 */
	protected void addTimeOut(Object service,int connectTimeout,int receiveTimeout) {
		Client client = ClientProxy.getClient(service);
		WebServiceUtil.addTimeOut(client, connectTimeout, receiveTimeout);
	}
	
	/**
	 * http认证
	 * @param service
	 */
	protected void httpLogin(Object service,String userName,String password){
		Client client = ClientProxy.getClient(service);
		WebServiceUtil.login(client, userName, password);
	}
	
	/**
	 * 创建服务
	 * @param address
	 * @param serviceClass
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected <T> T createService(String address,Class<T> serviceClass,String userName,String password){
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();    
	    factory.setAddress(address);
	    factory.setServiceClass(serviceClass);
	    Object service = factory.create();
	    addTimeOut(service);
	    if(!ValidateUtil.isEmpty(userName) && !ValidateUtil.isEmpty(password)){
	    	httpLogin(service, userName, password);
	    }
	    return (T) service;
	}
}
