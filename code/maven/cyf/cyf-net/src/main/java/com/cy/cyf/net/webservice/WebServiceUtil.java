package com.cy.cyf.net.webservice;

import org.apache.cxf.configuration.security.AuthorizationPolicy;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.dynamic.DynamicClientFactory;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;

import com.cy.cyf.core.exception.WebServiceException;
import com.cy.cyf.net.dto.WSRequestDTO;
import com.cy.cyf.util.ValidateUtil;

/**
 * WebService工具类
 * 
 * @author ChenY201
 * @date 2015年6月4日
 */
public class WebServiceUtil {
	
	public static final DynamicClientFactory dynamicClientFactory= DynamicClientFactory.newInstance();
	
	/**
	 * 调用webservice接口，请求参数和返回参数均为String
	 * @param url 请求地址
	 * @param method 请求方法
	 * @param requestStr 请求字符串
	 * @return
	 * @throws WebServiceException
	 */
	public static String invoke(String url,String method,String requestStr) throws WebServiceException{
		WSRequestDTO request = new WSRequestDTO(url,method);
		request.setParam(new Object[]{requestStr});
		Object[] resp = invoke(request);
		if(resp == null || resp.length == 0){
			throw new WebServiceException("返回信息异常");
		}
		return (String) resp[0];
	}
	
	/**
	 * 调用webservice接口
	 * @param request
	 * @return
	 * @throws WebServiceException
	 */
	public static Object[] invoke(WSRequestDTO request) throws WebServiceException{
		if(!check(request)){
			throw new WebServiceException("请求参数不正确");
		}
		Client client = null;
		try {
			client = dynamicClientFactory.createClient(request.getUrl());
			addTimeOut(client,request.getConnectTimeout(),request.getReceiveTimeout());
			if(request.getAuthDTO() != null){
				login(client, request.getAuthDTO().getUserName(), request.getAuthDTO().getPassword());
			}
			return client.invoke(request.getMethod(), request.getParam());
		} catch (Exception e) {
			throw new WebServiceException("调用接口异常",e);
		} finally{
			if(client != null){
				client.destroy();
			}
		}
	}
	
	/**
	 * 请求参数校验
	 * @param request
	 * @return
	 */
	private static boolean check(WSRequestDTO request){
		boolean flag = false;
		if(request!= null && !ValidateUtil.isEmpty(request.getMethod())){
			if(request.getAuthDTO() != null && !ValidateUtil.isEmpty(request.getAuthDTO().getUserName()) && !ValidateUtil.isEmpty(request.getAuthDTO().getPassword())){
				flag = true;
			}else if(request.getAuthDTO() == null){
				flag = true;
			}
		}
		return flag;
	}
	
	/**
	 * 超时设置
	 * @param service
	 * @param connectTimeout 请求超时
	 * @param receiveTimeout 接收超时
	 * @throws WebServiceException
	 */
	public static void addTimeOut(Client client,int connectTimeout,int receiveTimeout) throws WebServiceException{
		try {
			HTTPConduit http = (HTTPConduit) client.getConduit();
			HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
			httpClientPolicy.setConnectionTimeout(connectTimeout);
			httpClientPolicy.setAllowChunking(false);
			httpClientPolicy.setReceiveTimeout(receiveTimeout);
			http.setClient(httpClientPolicy);
//			addLogger(client);
		} catch (Exception e) {
			throw new WebServiceException("设置超时异常",e);
		}
	}
	
	/**
	 * 添加webservice日志
	 * @param client
	 */
	public static void addLogger(Client client){
		client.getOutInterceptors().add(new LoggingOutInterceptor());//输出请求报文
		client.getInInterceptors().add(new LoggingInInterceptor());//输出返回报文
	}
	
	/**
	 * http认证
	 * @param client
	 * @param userName 用户名
	 * @param password 密码
	 * @throws WebServiceException
	 */
	public static void login(Client client,String userName,String password) throws WebServiceException{
		try {
			AuthorizationPolicy authorization = new AuthorizationPolicy();
			authorization.setUserName(userName);
			authorization.setPassword(password);
			HTTPConduit http = (HTTPConduit) client.getConduit();
			http.setAuthorization(authorization);
		} catch (Exception e) {
			throw new WebServiceException("登录异常",e);
		}
	}
	
	public static void main(String[] args) {
		WSRequestDTO request = new WSRequestDTO("http://127.0.0.1:8080/CYF/webservice/certiNotice?wsdl","test1",new Object[]{"9999999",10});
		Object[] respObjs = invoke(request);
		for(Object obj:respObjs){
			System.out.println(obj);
		}
		System.out.println("=======================");
		System.out.println(invoke("http://127.0.0.1:8080/CYF/webservice/certiNotice?wsdl","test2","-----------"));
	}

}
