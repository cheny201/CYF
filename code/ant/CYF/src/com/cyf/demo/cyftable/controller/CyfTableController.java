package com.cyf.demo.cyftable.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cyf.base.BaseController;
import com.cyf.demo.cyftable.dto.UserDTO;
import com.cyf.util.page.Pager;

/**
 * CYFTable控制层
 * @author ChenY201
 * @version 1.0
 * @created 2014-11-12 下午8:24:47
 */
@Controller
public class CyfTableController extends BaseController{

	@RequestMapping(value = "/demo/table", method = RequestMethod.POST)
	public void showChart(Pager<Object> page,HttpServletRequest request, HttpServletResponse resp)
			throws Exception {
//		Pager<Object> page = new Pager<Object>();
		List<Object> list = new ArrayList<Object>();
		for (int i = 0; i < 100; i++) {
			UserDTO u = new UserDTO();
			u.setAge(i+"");
			u.setName("测试"+i);
			u.setSex("男"+i);
			list.add(u);
		}
		
		page.setPageCount(50);
		page.setEntityCount(100);
		
		int start =  (page.getPageNo()-1) * page.getPageSize();
		int end = start + page.getPageSize();
		if(start > page.getEntityCount()){
			start = page.getEntityCount();
		}else if(start < 0){
			start = 0;
		}
		if(end > page.getEntityCount()){
			end = page.getEntityCount();
		}else if(end < 0){
			end = page.getPageSize();
		}
		
		List<Object> list1 = new ArrayList<Object>();
		for (int i = start; i < end; i++) {
			list1.add(list.get(i));
		}
		
		
		
		
		page.setData(list1);
		
		
		
		this.renderJsonData(resp, page);
	}
	
}
