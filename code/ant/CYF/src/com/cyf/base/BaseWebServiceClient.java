package com.cyf.base;

import org.apache.cxf.configuration.security.AuthorizationPolicy;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;

/**
 * cxf客户端调用webservice基础类
 * @author ChenY201
 * @version 1.0
 * @created 2014-11-12 下午8:38:07
 */
public class BaseWebServiceClient {
	
	private final int CONNECTION_TIMEOUT = 30000;//连接超时
	private final int RECEIVE_TIMEOUT = 30000;//接收超时
	
	private final String USERNAME = "";//http认证用户名
	private final String PASSWORD = "";//http认证密码
	private final boolean isLogin = false;//是否需要认证
	
	/**
	 * 超时设置
	 * @param service
	 */
	protected void addTimeOut(Object service) {
		addTimeOut(service,CONNECTION_TIMEOUT,RECEIVE_TIMEOUT);
	}
	
	/**
	 * 超时设置
	 * @param service
	 * @param connectTimeout 连接超时时间(ms)
	 * @param receiveTimeout 接收超时时间(ms)
	 */
	protected void addTimeOut(Object service,int connectTimeout,int receiveTimeout) {
		Client client = ClientProxy.getClient(service);
		HTTPConduit http = (HTTPConduit) client.getConduit();
		HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
		httpClientPolicy.setConnectionTimeout(connectTimeout);
		httpClientPolicy.setAllowChunking(false);
		httpClientPolicy.setReceiveTimeout(receiveTimeout);
		http.setClient(httpClientPolicy);
//		client.getOutInterceptors().add(new LoggingOutInterceptor());//输出请求报文
//		client.getInInterceptors().add(new LoggingInInterceptor());//输出返回报文
	}
	
	/**
	 * http认证
	 * @param service
	 */
	protected void httpLogin(Object service){
		Client client = ClientProxy.getClient(service);
		HTTPConduit http = (HTTPConduit) client.getConduit();
		AuthorizationPolicy authorization = new AuthorizationPolicy();
		authorization.setUserName(USERNAME);
		authorization.setPassword(PASSWORD);
		http.setAuthorization(authorization);
	}
	
	/**
	 * 创建服务
	 * @param address
	 * @param serviceClass
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected <T> T createService(String address,Class<T> serviceClass){
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();    
	    factory.setAddress(address);
	    factory.setServiceClass(serviceClass);
	    Object service = factory.create();
	    addTimeOut(service);
	    
	    if(isLogin){
	    	httpLogin(service);
	    }
	    return (T) service;
	}
}
