package test.spring;

import org.springframework.beans.factory.annotation.Autowired;

import com.cyf.security.service.UserService;

public class Test extends TestBase{
	
	@Autowired
	private UserService userService;
	
	@org.junit.Test
	public void test1(){
		try {
			userService.aa();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
