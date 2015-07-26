package com.cy.cyf.framework.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cy.cyf.core.Constant;
import com.cy.cyf.core.exception.CYFException;
import com.cy.cyf.framework.handler.ExceptionHandlerService;
import com.cy.cyf.log.CYFLog;
import com.cy.cyf.util.IOUtil;
import com.cy.cyf.util.ValidateUtil;
import com.google.gson.GsonBuilder;

@Controller
public class BaseController {
	
	private static GsonBuilder gsonBuilder = null;
	
	{
		gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss");
	}
	
	@Autowired
	private ExceptionHandlerService exceptionHandlerService;
	
	@ExceptionHandler(Exception.class)
	public void exceptionHandler(Exception e, HttpServletResponse resp) {
		CYFLog.error("Controller异常..................", e);
		exceptionHandlerService.doHandler(e, resp);
		if (e instanceof org.springframework.web.multipart.MaxUploadSizeExceededException) {
		}
	}
	
	/**
	 * 向页面写数据
	 * @param obj
	 * @param resp
	 */
	protected void writeToPage(Object obj, HttpServletResponse resp,String dateFormat) {
		try {
			resp.setCharacterEncoding(Constant.ENCODING);// 设置编码
			resp.setHeader("Cache-Control", "no-cache");
			String str = null;
			if(obj instanceof String){
				resp.setContentType("text/xml;charset="+Constant.ENCODING);
				str = (String) obj;
			}else{
				resp.setContentType("text/json;charset="+Constant.ENCODING);
				if(ValidateUtil.isEmpty(dateFormat)){
					dateFormat = Constant.DATE_FORMAT;
				}
				str = new GsonBuilder().setDateFormat(dateFormat).create().toJson(obj);
			}
			resp.getWriter().print(str);
		} catch (Exception e) {
			CYFLog.error("向页面输出数据失败:" + obj.toString(), e);
		}
	}
	
	/**
	 * 下载
	 * @param response
	 * @param path
	 * @param encoding
	 */
	protected void downLoad(HttpServletResponse response,String path,String encoding){
		if(ValidateUtil.isEmpty(encoding)){
			encoding = Constant.ENCODING;
		}
		response.setCharacterEncoding(encoding);
		File f = new File(path);
		if(!f.exists()){
			throw new CYFException("文件不存在");
		}
		String externalName = "";
		try {
			externalName = URLEncoder.encode(f.getName(), encoding);
		} catch (UnsupportedEncodingException e) {
			throw new CYFException("编码文件名失败",e);
		}
		response.addHeader("Content-Disposition", "attachment;filename="+externalName);
		
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(f);
			IOUtil.write(fis, response.getOutputStream());
		} catch (Exception e) {
			CYFLog.error("输出文件失败",e);
		}finally{
			IOUtil.closeInputStream(fis);
		}
		
	}

}
