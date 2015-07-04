package com.cy.cyf.util;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import com.cy.cyf.log.CYFLog;
import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;

public class PDFUtil {

	public static void main(String[] args) throws Exception {
		String filePath = "f:/11/Maven实战.pdf";
		String outDir = "f:/11/picture";
//		PDFUtil.convertToJPGByPDFBox(filePath, outDir);
		PDFUtil.convertToJPGByPDFRender(filePath, outDir);
	}

	/**
	 * 将pdf转换成jpg，但如果文件过大的话会占用大量的内存 适用于小文件
	 * 
	 * @param filePath
	 *            pdf路径
	 * @param outDir
	 *            输出图片目录
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public static void convertToJPGByPDFBox(String filePath, String outDir)
			throws IOException {
		long start = System.currentTimeMillis();
		PDDocument doc = PDDocument.load(filePath);
		List<PDPage> pages = doc.getDocumentCatalog().getAllPages();
		for (int i = 0; i < pages.size(); i++) {
			PDPage page = pages.get(i);
			BufferedImage image = page.convertToImage();
			ImageIO.write(image, "jpg", new File(outDir + File.separator + i
					+ ".jpg"));
		}
		doc.close();
		long end = System.currentTimeMillis();
		CYFLog.debug("[" + filePath + "]转换完成，耗时[" + (end - start) + "]ms");
	}

	/**
	 * 将pdf转换成jpg，中文文件会有问题
	 * 
	 * @param filePath
	 *            pdf路径
	 * @param outDir
	 *            输出图片目录
	 * @throws IOException
	 */
	public static void convertToJPGByPDFRender(String filePath, String outDir) throws IOException {
		long start = System.currentTimeMillis();
		// load a PDF from a byte buffer
		RandomAccessFile raf = new RandomAccessFile(filePath, "r");
		FileChannel channel = raf.getChannel();
		ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0,
				channel.size());
		raf.close();
		PDFFile pdffile = new PDFFile(buf);
		CYFLog.debug("页数： " + pdffile.getNumPages());
		for (int i = 1; i <= pdffile.getNumPages(); i++) {
			// draw the first page to an image
			PDFPage page = pdffile.getPage(i);

			// get the width and height for the doc at the default zoom
			Rectangle rect = new Rectangle(0, 0, (int) page.getBBox()
					.getWidth(), (int) page.getBBox().getHeight());
			System.out.println(i+"==============="+page.getBBox().getHeight());
			// generate the image
			Image img = page.getImage(rect.width, rect.height, // width & height
					rect, // clip rect
					null, // null for the ImageObserver
					true, // fill background with white
					true // block until drawing is done
					);
			BufferedImage tag = new BufferedImage(rect.width, rect.height,
					BufferedImage.TYPE_INT_RGB);
			tag.getGraphics().drawImage(img, 0, 0, rect.width, rect.height,
					null);
			ImageIO.write(tag, "jpg", new File(outDir + File.separator + i
					+ ".jpg"));
		}
		long end = System.currentTimeMillis();
		CYFLog.debug("[" + filePath + "]转换完成，耗时[" + (end - start) + "]ms");
	}

}
