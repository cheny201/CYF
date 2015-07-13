package com.cy.cyf.util.qrcode;

/**
 * 二维码工具类
 * 
 * @author ChenY201
 * @date 2015年7月13日
 */
public class QRCodeUtil {
	
	/**
	 * 生成二维码(QRCode)图片（图片格式为png）
	 * @param content 二维码信息
	 * @param imgPath 生成图片路径
	 */
	public static void encoderQRCode(String content, String imgPath){
		new QRCodeEncoderHandler().encoderQRCode(content, imgPath);
	}
	
	/**
	 * 解码二维码(QRCode)
	 * @param imgPath 需要解析的图片路径
	 * @param encode 信息编码
	 * @return
	 */
	public static String decoderQRCode(String imgPath,String encode) {
		return new QRCodeDecoderHandler().decoderQRCode(imgPath, encode);
	}

}
