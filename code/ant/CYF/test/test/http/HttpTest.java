package test.http;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class HttpTest {
	
	public static void main(String[] args) {

		String requestUrl = "http://shixin.court.gov.cn/search";//请求url
			try {
				URL url = new URL(requestUrl);
				HttpURLConnection urlConn = (HttpURLConnection) url
						.openConnection();
				
				urlConn.setConnectTimeout(100000);
				urlConn.setReadTimeout(100000);
				System.out.println(URLEncoder.encode("袁奇峰", "utf-8"));
				urlConn.addRequestProperty("pName", URLEncoder.encode("袁奇峰", "utf-8"));
				urlConn.addRequestProperty("pProvince", "0");
				urlConn.addRequestProperty("pCardNum", "");
//				urlConn.setDoOutput(true);
				urlConn.setDoInput(true);
				urlConn.setRequestMethod("POST");
				
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
