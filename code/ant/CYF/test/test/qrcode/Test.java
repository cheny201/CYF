package test.qrcode;

import com.cyf.qrcode.QRCodeDecoderHandler;
import com.cyf.qrcode.QRCodeEncoderHandler;

public class Test {
	

	public static void main(String[] args) throws Exception {
		
		String imgPath = "F:/Workspace/Eclipse/CYF/test/test/qrcode/11.png";
//		String content = "医保通：http://10.99.73.172:8080/ybt";
//
//		QRCodeEncoderHandler encoderHandler = new QRCodeEncoderHandler();
//		encoderHandler.encoderQRCode(content, imgPath);
//		System.out.println("encoder QRcode success");

		QRCodeDecoderHandler decoderHandler = new QRCodeDecoderHandler();
		String decoderContent = decoderHandler.decoderQRCode(imgPath,null);
		System.out.println("解析结果如下：");
		System.out.println(decoderContent);
		System.out.println("decoder success");
	}

}
