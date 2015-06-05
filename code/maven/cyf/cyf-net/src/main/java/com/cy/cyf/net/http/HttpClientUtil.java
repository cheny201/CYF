package com.cy.cyf.net.http;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.cy.cyf.log.CYFLog;
import com.cy.cyf.net.Constant;
import com.cy.cyf.net.dto.HttpRequestDTO;
import com.cy.cyf.util.IOUtil;

public class HttpClientUtil {
	private static PoolingHttpClientConnectionManager cm;
	
	{	
		//初始化http认证
		cm = new PoolingHttpClientConnectionManager();
		cm.setMaxTotal(10);
	}
	
	
	public static String sendRequest(HttpRequestDTO httpRequestDTO) {
		HttpRequestBase request = null;
		HttpResponse resp = null;
		HttpEntity respEntity = null;
		String respStr = null;
		try {
			String url = httpRequestDTO.getUrl();
			switch (httpRequestDTO.getMethod()) {
			case HttpGet.METHOD_NAME:
				url += parseGetParams(httpRequestDTO.getParams());
				request = new HttpGet(url);
				break;
			case HttpPost.METHOD_NAME:
				request = new HttpPost(url);
				HttpEntity httpEntity = parsePostParams(httpRequestDTO.getParams(), httpRequestDTO.getFiles());
				((HttpPost)request).setEntity(httpEntity);
				break;
			default:
				break;
			}
			
			HttpClient httpClient = getClient(httpRequestDTO);
			if(httpRequestDTO.getAuthDTO() != null){
				resp = httpClient.execute(request,getAuthContext(httpRequestDTO));
			}else{
				resp = httpClient.execute(request);
			}
			
			if (resp != null
					&& resp.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				respEntity = resp.getEntity();
				respStr = IOUtil.readStream(respEntity.getContent());
				CYFLog.debug("http返回信息:"+respStr);
			}
		} catch (Exception e) {
			CYFLog.error(e.getMessage(), e);
		} finally{
			try {
				if(respEntity != null){
					EntityUtils.consume(respEntity);
				}
				if(request != null){
					request.releaseConnection();
				}
			} catch (IOException e) {
				CYFLog.error("断开HTTP连接异常", e);
			}
		}
		return respStr;
	}
	
	private static HttpClient getClient(HttpRequestDTO httpRequestDTO){
		HttpClientBuilder builder = HttpClients.custom();
		builder.setConnectionManager(cm);
		if(httpRequestDTO.getProxy() != null){
			DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(httpRequestDTO.getProxy());
			builder.setRoutePlanner(routePlanner);
		}
		return builder.build();
	}
	
	private static HttpClientContext getAuthContext(HttpRequestDTO httpRequestDTO){
		HttpClientContext context = HttpClientContext.create();
		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(
		    new AuthScope(new HttpHost(httpRequestDTO.getUrl())), 
		    new UsernamePasswordCredentials(httpRequestDTO.getAuthDTO().getUserName(), httpRequestDTO.getAuthDTO().getPassword()));
		context.setCredentialsProvider(credsProvider);
		return context;
	}

	private static HttpEntity parsePostParams(Map<String, String> params)
			throws UnsupportedEncodingException, FileNotFoundException {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		if (params != null) {
			Iterator<String> it = params.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next();
				nvps.add(new BasicNameValuePair(key, params.get(key)));
			}
		}
		return new UrlEncodedFormEntity(nvps, Constant.ENCODING);
	}

	private static HttpEntity parsePostParams(Map<String, String> params,
			Map<String, File> files) throws UnsupportedEncodingException,
			FileNotFoundException {
		if (files == null || files.isEmpty()) {
			return parsePostParams(params);
		}
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		builder.setCharset(Charset.forName(Constant.ENCODING));
		if (params != null) {
			Iterator<String> it = params.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next();
				builder.addTextBody(key, params.get(key),
						ContentType.DEFAULT_BINARY);
			}
		}
		Iterator<String> it = files.keySet().iterator();
		File f = null;
		while (it.hasNext()) {
			String key = it.next();
			f = files.get(key);
			if (!f.exists()) {
				throw new FileNotFoundException("文件[" + f.getPath() + "]不存在");
			}
			builder.addBinaryBody(key, f, ContentType.DEFAULT_BINARY,
					f.getName());
		}
		return builder.build();
	}

	private static String parseGetParams(Map<String, String> params)
			throws UnsupportedEncodingException {
		StringBuffer result = new StringBuffer();
		if (params != null) {
			Iterator<String> it = params.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next();
				result.append("&");
				result.append(key);
				result.append("=");
				result.append(params.get(key));
			}
			if (result.length() > 0) {
				result.replace(0, 1, "?");
			}
		}
		return result.toString();
	}

}
