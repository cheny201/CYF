package com.cy.cyf.net;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.methods.HttpPost;

import com.cy.cyf.log.CYFLog;
import com.cy.cyf.net.dto.HttpRequestDTO;
import com.cy.cyf.net.http.HttpClientUtil;

/**
 * Hello world!
 *
 */
public class MainTest 
{
    public static void main( String[] args ) throws Exception
    {
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
