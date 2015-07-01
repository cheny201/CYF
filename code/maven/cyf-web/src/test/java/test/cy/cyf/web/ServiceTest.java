package test.cy.cyf.web;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cy.project.weixin.service.WeiXinService;

public class ServiceTest extends TestBase{
	
	@Autowired
	private WeiXinService weiXinService;

	@Test
	public void test1(){
		weiXinService.doService();
	}
	
}
