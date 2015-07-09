package com.cy.cyf.net;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.methods.HttpPost;
import org.apache.xmlbeans.impl.tool.XSTCTester.TestCase;
import org.junit.Test;

import com.cy.cyf.log.CYFLog;
import com.cy.cyf.net.dto.HttpRequestDTO;
import com.cy.cyf.net.http.HttpClientUtil;
import com.cy.cyf.net.mail.MailClient;
import com.cy.cyf.net.mail.MailInfo;

/**
 * Hello world!
 *
 */
public class MainTest extends TestCase{
	
	@Test
	public void testMail() throws Exception{
		MailInfo m1 = new MailInfo();
		m1.setToAddress("404369230@qq.com");
		m1.setCharset("utf-8");
		m1.setContent("没有附件");
		m1.setSubject("测试1");
		MailInfo m2 = new MailInfo();
		m2.setToAddress("404369230@qq.com");
		m2.setCharset("utf-8");
		m2.setContent("有附件");
		m2.setSubject("测试2");
		m2.setFiles(new File[]{new File("C:/Users/Administrator.2013-20140324PL/Desktop/1.png")});
		MailClient client = new MailClient("smtp.163.com", "cheny201@163.com","陈颖","cheny201", "chenying201.", false);
		client.send(new MailInfo[]{m1,m2});
	}
	
	
	
    public void httpTest() throws Exception{
    	Map<String,String> params = new HashMap<String,String>();
    	Map<String,File> files = new HashMap<String,File>();
    	params.put("userName", "222");
    	params.put("pwd", "333");
    	params.put("wd", "maven");
    	File dir = new File("F:\\temp");
    	File[] fileLs = dir.listFiles();
    	for (int i = 0; i < fileLs.length; i++) {
    		files.put("file"+i, fileLs[i]);
		}
    	CYFLog.info("EEEEEEEEEEEE");
    	HttpRequestDTO httpRequestDTO = new HttpRequestDTO("http://127.0.0.1:8080/web/test",HttpPost.METHOD_NAME,params,files,false);
        HttpClientUtil.sendRequest(httpRequestDTO);
        CYFLog.info("TTTTTTTTTTT");
    }
}
