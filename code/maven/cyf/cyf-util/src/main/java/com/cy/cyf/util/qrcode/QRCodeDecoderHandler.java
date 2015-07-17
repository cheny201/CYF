package com.cy.cyf.util.qrcode;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.cy.cyf.core.Constant;
import com.cy.cyf.log.CYFLog;
import com.cy.cyf.util.ValidateUtil;

import jp.sourceforge.qrcode.QRCodeDecoder;
import jp.sourceforge.qrcode.data.QRCodeImage;
import jp.sourceforge.qrcode.exception.DecodingFailedException;

/**
 * 二维码解码类
 * @author ChenY201
 * @version 1.0
 * @created 2014-11-12 下午8:32:38
 */
public class QRCodeDecoderHandler {

	/**
	 * 解码二维码
	 * 
	 * @param imgPath
	 * @return String
	 */
	public String decoderQRCode(String imgPath,String encode) {

		if(ValidateUtil.isEmpty(encode)){
			encode = Constant.ENCODING;
		}
		
		// QRCode 二维码图片的文件
		File imageFile = new File(imgPath);

		BufferedImage bufImg = null;
		String decodedData = null;
		try {
			bufImg = ImageIO.read(imageFile);

			QRCodeDecoder decoder = new QRCodeDecoder();
			decodedData = new String(decoder.decode(new J2SEImage(bufImg)),encode);
		} catch (IOException e) {
			CYFLog.error("",e);
		} catch (DecodingFailedException dfe) {
			CYFLog.error("",dfe);
		}
		return decodedData;
	}

	class J2SEImage implements QRCodeImage {
		BufferedImage bufImg;

		public J2SEImage(BufferedImage bufImg) {
			this.bufImg = bufImg;
		}

		public int getWidth() {
			return bufImg.getWidth();
		}

		public int getHeight() {
			return bufImg.getHeight();
		}

		public int getPixel(int x, int y) {
			return bufImg.getRGB(x, y);
		}

	}
}
