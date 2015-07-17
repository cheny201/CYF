package com.cy.cyf.util.qrcode;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.cy.cyf.core.Constant;
import com.cy.cyf.log.CYFLog;
import com.swetake.util.Qrcode;

/**
 * 二维码生成类
 * @author ChenY201
 * @version 1.0
 * @created 2014-11-12 下午8:33:01
 */
public class QRCodeEncoderHandler {

	private int IMG_WIDTH = 0;// 图片宽度
	private final int UNIT_WIDTH = 10;// 单位宽度
	private final int PIXOFF = 10;// 设置偏移量 不设置可能导致解析出错

	/**
	 * 生成二维码(QRCode)图片
	 * 
	 * @param content
	 * @param imgPath
	 */
	public void encoderQRCode(String content, String imgPath) {
		try {

			Qrcode qrcodeHandler = new Qrcode();
			/*
			 * 错误修正容量 L水平 7%的字码可被修正 M水平 15%的字码可被修正 Q水平 25%的字码可被修正 H水平 30%的字码可被修正
			 * QR码有容错能力，QR码图形如果有破损，仍然可以被机器读取内容，最高可以到7%~30%面积破损仍可被读取。
			 * 相对而言，容错率愈高，QR码图形面积愈大。所以一般折衷使用15%容错能力。
			 * 纠错信息同样存储在二维码中，纠错级别越高，纠错信息占用的空间越多，那么能存储的有用讯息就越少
			 */
			qrcodeHandler.setQrcodeErrorCorrect('M');/* L','M','Q','H' */
			qrcodeHandler.setQrcodeEncodeMode('B');

			/*
			 * 二维码的版本号，也象征着二维码的信息容量；二维码可以看成一个黑白方格矩阵，版本不同，矩阵长宽方向方格的总数量分别不同，版本1为21*
			 * 21矩阵，版本每增1，二维码的两个边长都增4；所以版本7为45*45的矩阵；最高版本为是40，是177*177的矩阵
			 * 
			 * 经测试当version大于10时，解码失败
			 */
			qrcodeHandler.setQrcodeVersion(5);

			byte[] contentBytes = content.getBytes(Constant.ENCODING);
			// 输出内容 > 二维码
			if (contentBytes.length > 0) {
				boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);

				IMG_WIDTH = codeOut.length * UNIT_WIDTH + PIXOFF * 2;

				BufferedImage bufImg = new BufferedImage(IMG_WIDTH, IMG_WIDTH,
						BufferedImage.TYPE_INT_RGB);

				Graphics2D gs = bufImg.createGraphics();

				gs.setBackground(Color.WHITE);//背景色
				gs.clearRect(0, 0, IMG_WIDTH, IMG_WIDTH);

				gs.setColor(Color.BLACK);// 设定图像颜色

				for (int i = 0; i < codeOut.length; i++) {
					for (int j = 0; j < codeOut.length; j++) {
						if (codeOut[j][i]) {
							gs.fillRect(j * UNIT_WIDTH + PIXOFF, i * UNIT_WIDTH
									+ PIXOFF, UNIT_WIDTH, UNIT_WIDTH);
						}
					}
				}

				waterMark(gs, IMG_WIDTH, "陈");
				
				gs.dispose();
				bufImg.flush();

				File imgFile = new File(imgPath);
				// 生成二维码QRCode图片
				ImageIO.write(bufImg, "png", imgFile);
			} else {
				CYFLog.error("QRCode content bytes length < 0");
			}

		} catch (Exception e) {
			CYFLog.error("",e);
		}

	}
	
	/**
	 * 添加水印
	 * @param gs
	 * @param imgWidth
	 * @param str
	 */
	private void waterMark(Graphics2D gs,int imgWidth,String str){
		int markWidth = 80;
		Font font = new Font("华文行楷", Font.PLAIN, markWidth/str.length());// 添加字体的属性设置
		int x = (imgWidth - markWidth)/2;
		gs.setBackground(Color.WHITE);
		gs.clearRect(x, x, markWidth, markWidth);
		gs.setColor(Color.BLUE);
		gs.setFont(font);
		gs.drawString(str, x, x+markWidth-15);
	}

}
