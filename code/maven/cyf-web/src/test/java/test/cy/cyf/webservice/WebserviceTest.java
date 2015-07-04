package test.cy.cyf.webservice;

import com.cy.cyf.net.webservice.WebServiceUtil;

public class WebserviceTest {
	
	public static void main(String[] args) {
		String url = "http://127.0.0.1/cyf-web/webservice/webServiceDemo?wsdl";
		String resp = WebServiceUtil.invoke(url, "test2", "测试");
		System.out.println(resp);
	}

}
