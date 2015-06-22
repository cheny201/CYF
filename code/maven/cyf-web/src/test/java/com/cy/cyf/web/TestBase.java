package com.cy.cyf.web;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Copyright FuJian Trust Information Technology CO.,LTD
 * <p>
 * 测试基类，用于初始化测试环境，销毁测试数据
 * @author MaoZhulan(mzl0517@gmail.com)
 * @since 2014-4-30
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring.xml"})
//@ContextConfiguration({"/WEB-INF/lib/cyf-framework-0.0.1.jar*/com/cy/cyf/frameworw/config/spring/spring.xml"})
//lib/validator-rest-1.0.jar*/cms-validator-common.xml
public class TestBase {

	@Autowired
	protected ApplicationContext context;
	
	@Before
	public void init(){
		
	}
	
	@After
	public void destory(){
		
	}
}
