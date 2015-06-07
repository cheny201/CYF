package com.cyf.base;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.cyf.util.page.Pager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Controller层基础类
 * @author ChenY201
 * @version 1.0
 * @created 2014-11-12 下午8:19:22
 */
public class BaseController {
	
	protected void renderJsonData(HttpServletResponse response, Pager<Object> pager) throws Exception {
		response.setContentType("text/json; charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		PrintWriter out = response.getWriter();
		out.print(gson.toJson(pager));
	}

}
