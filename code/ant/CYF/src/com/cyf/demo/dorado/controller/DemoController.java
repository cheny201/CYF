package com.cyf.demo.dorado.controller;

import java.util.Map;

import org.marmot.view.DataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.cyf.security.dao.UserDao;
import com.cyf.security.pojo.User;

@Controller("doradoDemoController")
public class DemoController{
	
	@Autowired
	private UserDao userDao;
	
	public void queryUser(DataSet dataset) throws Exception{
		Map<String,String> map = (Map<String, String>) dataset.getParameters();
		String usercode = map.get("usercode");
		User u = userDao.getUserByUserAccount(usercode);
		dataset.addRecord(u);
	}

}
