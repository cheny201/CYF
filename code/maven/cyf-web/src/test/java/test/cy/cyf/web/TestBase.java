package test.cy.cyf.web;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
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
@ContextConfiguration({"classpath*:com/cy/config/spring/spring.xml",
	"classpath*:com/cy/config/spring/spring-hibernate.xml"})
public class TestBase {

	@Autowired
	protected ApplicationContext context;
	
	@Before
	public void init(){
		
	}
	
	@After
	public void destory(){
		
	}
	
	@Test
	public void test(){
		System.out.println("---test---");
	}
}
