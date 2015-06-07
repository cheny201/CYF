package test.webservice.example;

import com.cyf.demo.webservice.client.WebServiceClientDemo;
import com.cyf.demo.webservice.client.dto.CertiNoticeResponse;

public class ExampleClient {
	
	public static void main(String[] args) {
		CertiNoticeResponse resp = new WebServiceClientDemo().queryUser("admin","123");
		System.out.println("响应消息：");
		System.out.println(resp);
	}

}
