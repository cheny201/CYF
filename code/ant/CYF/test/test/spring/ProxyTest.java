package test.spring;

import org.springframework.context.ApplicationContext;

public class ProxyTest extends BaseTest{
	
	public static void main(String[] args) {
		new ProxyTest().test();
	}
	
	public void test(){
		ApplicationContext ac = super.initSpring();
		Object object = ac.getBean("targetObject");
		System.out.println(object);
	}

}
