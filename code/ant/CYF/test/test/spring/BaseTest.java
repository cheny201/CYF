package test.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BaseTest {
	
	protected ApplicationContext initSpring(){
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				new String[] { "/com/config/spring/applicationContext.xml"});
		return ac;
	}

}
