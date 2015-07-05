package com.cy.project.weixin.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cy.cyf.core.exception.EncryptException;
import com.cy.cyf.framework.controller.BaseController;
import com.cy.cyf.util.EncryptDTO;
import com.cy.cyf.util.EncryptUtil;
import com.cy.project.weixin.service.WeiXinService;

@Controller
public class WXController extends BaseController{
	
//	private String EncodingAESKey = "OfMPPyjAvsWodgstqNbKbpdyjP04nw9CDW7nPcW0ija";
	private String TOKEN = "cyftest";
	
	
	@Autowired
	private WeiXinService weiXinService;
	
	@RequestMapping(value="/accept",method=RequestMethod.GET)
	public void accept(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		String signature = req.getParameter("signature");
		String timestamp = req.getParameter("timestamp");
		String nonce = req.getParameter("nonce");
		String echostr = req.getParameter("echostr");
		if(checkSign(TOKEN,signature,timestamp,nonce)){
			Map<String,String> map = getData(req.getInputStream());
			System.out.println(map);
			String MsgType = map.get("MsgType");
			switch (MsgType) {
			case "text":
				System.out.println(map.get("Content"));
				break;
			default:
				break;
			}
			
			weiXinService.doService();
			resp.getWriter().write(echostr);
		}
	}
	
	private boolean checkSign(String token,String signature,String timestamp,String nonce){
		boolean flag = false;
		try {
			String[] arr = new String[]{token,timestamp,nonce};
			Arrays.sort(arr);
			
			StringBuffer str = new StringBuffer();
			for (int i = 0; i < arr.length; i++) {
				str.append(arr[i]);
			}
			String encodeStr = EncryptUtil.encode(new EncryptDTO(EncryptUtil.SHA1, str.toString()));
			if(signature.equals(encodeStr)){
				flag = true;
			}
			System.out.println(encodeStr);
		} catch (EncryptException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	private Map<String,String> getData(InputStream inp){
		Map<String,String> map = new HashMap<String, String>();
		return map;
	}
}
