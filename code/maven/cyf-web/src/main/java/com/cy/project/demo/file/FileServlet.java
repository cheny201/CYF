package com.cy.project.demo.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cy.cyf.core.Constant;
import com.cy.cyf.log.CYFLog;
import com.cy.cyf.util.IOUtil;

public class FileServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding(Constant.ENCODING);
		String path = req.getParameter("path");
		File f = new File(path);
		if(!f.exists()){
			CYFLog.debug("文件不存在["+f.getAbsolutePath()+"]");
			return;
		}
		IOUtil.write(new FileInputStream(path), resp.getOutputStream());
	}
	
	

}
