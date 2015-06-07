package com.cyf.demo.adapter.http;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import com.cyf.config.Constants;

/**
 * HTTP客户端示例
 * @author ChenY201
 * @version 1.0
 * @created 2014-11-12 下午8:23:03
 */
public class HttpClientDemo {
	
	public static void main(String[] args) {

		String requestUrl = "http://127.0.0.1:27167/httpServer.do";//请求url
		String reqStr = "测试";
			try {
				String encoding = Constants.ENCODING;// 编码集
				
				URL url = new URL(requestUrl);
				HttpURLConnection urlConn = (HttpURLConnection) url
						.openConnection();
				
				urlConn.setConnectTimeout(100000);
				urlConn.setReadTimeout(100000);
				
				urlConn.setDoOutput(true);
				urlConn.setDoInput(true);
				urlConn.setRequestMethod("POST");
				
				
				OutputStream out = urlConn.getOutputStream();
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
						out, encoding));
				bw.write(reqStr.toCharArray());
				bw.flush();
				bw.close();
				
				while (urlConn.getContentLength() != -1) {
					if (urlConn.getResponseCode() == 200) {
						InputStream in = urlConn.getInputStream();
						BufferedReader reader = new BufferedReader(
								new InputStreamReader(in));
						String temp = "";
						while ((temp = reader.readLine()) != null) {
							System.out.println(temp);// 打印收到的信息
						}
						reader.close();
						in.close();
						urlConn.disconnect();
						break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

}
