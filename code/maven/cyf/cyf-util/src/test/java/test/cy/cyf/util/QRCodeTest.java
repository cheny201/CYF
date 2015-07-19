package test.cy.cyf.util;

import org.apache.xmlbeans.impl.tool.XSTCTester.TestCase;
import org.junit.Test;

import com.cy.cyf.util.qrcode.QRCodeUtil;

public class QRCodeTest extends TestCase{
	
	@Test
	public void testDecode(){
		String info = QRCodeUtil.decoderQRCode("C:\\Users\\ChenY201\\Desktop\\wx-1.jpg", null);
		System.out.println(info);
	}

}
