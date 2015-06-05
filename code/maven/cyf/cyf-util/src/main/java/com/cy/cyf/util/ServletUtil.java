package com.cy.cyf.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cy.cyf.log.CYFLog;
import com.google.gson.GsonBuilder;

public class ServletUtil {
	
	public static void addRequestHead(HttpServletRequest req){
		try {
			req.setCharacterEncoding(Constant.ENCODING_UTF8);
		} catch (UnsupportedEncodingException e) {
			CYFLog.error("",e);
		}
	}
	
	public static void setResponseHead(HttpServletResponse response,String contenttype,String filename,String encoding){
		response.setCharacterEncoding(encoding);
		response.setContentType(contenttype);
		String externalName = "";
		try {
			externalName = URLEncoder.encode(filename, encoding);
		} catch (UnsupportedEncodingException e) {
			CYFLog.error("",e);
		}
		response.addHeader("Content-Disposition", "attachment;filename="+externalName);
	}
	
	/**
	 * 向页面写数据
	 * @param obj
	 * @param resp
	 */
	public static void writeJsonToPage(Object obj, HttpServletResponse resp,String dateFormat) {
		try {
			resp.setContentType("text/xml;charset="+Constant.ENCODING_UTF8);
			resp.setCharacterEncoding(Constant.ENCODING_UTF8);// 设置编码
			resp.setHeader("Cache-Control", "no-cache");
			String str = null;
			if(obj instanceof String){
				str = (String) obj;
			}else{
				if(ValidateUtil.isEmail(dateFormat)){
					dateFormat = Constant.DATE_FORMAT;
				}
				str = new GsonBuilder().setDateFormat(dateFormat).create().toJson(obj);
			}
			resp.getWriter().print(str);
		} catch (Exception e) {
			CYFLog.error("向页面输出数据失败:" + obj.toString(), e);
		}
	}
	
}
