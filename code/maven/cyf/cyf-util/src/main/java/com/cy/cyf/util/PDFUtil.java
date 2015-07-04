package com.cy.cyf.util;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.icepdf.core.exceptions.PDFException;
import org.icepdf.core.exceptions.PDFSecurityException;
import org.icepdf.core.pobjects.Document;
import org.icepdf.core.pobjects.Page;
import org.icepdf.core.util.GraphicsRenderingHints;

import com.cy.cyf.log.CYFLog;

public class PDFUtil {

	public static void main(String[] args) throws Exception {
		String filePath = "f:/11/lcpoldata_20150601_08_21_47_86110020150210045039.pdf";
		String outDir = "f:/11/picture";
		// PDFUtil.convertToJPGByPDFBox(filePath, outDir);
		PDFUtil.convertToJPGByIcePDF(filePath, outDir);

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
	 * 将pdf转换成jpg
	 * 
	 * @param filePath
	 *            pdf路径
	 * @param outDir
	 *            输出图片目录
	 * @throws IOException
	 * @throws PDFSecurityException
	 * @throws PDFException
	 */
	public static void convertToJPGByIcePDF(String filePath, String outDir)
			throws IOException, PDFException, PDFSecurityException {
		long start = System.currentTimeMillis();

		Document document = new Document();
		document.setFile(filePath);

		// save page caputres to file.
		float scale = 3f;
		float rotation = 0f;

		// Paint each pages content to an image and write the image to file
		for (int i = 0; i < document.getNumberOfPages(); i++) {
			BufferedImage image = (BufferedImage) document.getPageImage(i,
					GraphicsRenderingHints.SCREEN, Page.BOUNDARY_CROPBOX,
					rotation, scale);
			RenderedImage rendImage = image;
			// capture the page image to file
			CYFLog.debug("\t capturing page " + i);
			ImageIO.write(rendImage, "jpg", new File(outDir + File.separator
					+ i + ".jpg"));
			image.flush();
		}
		// clean up resources
		document.dispose();

		long end = System.currentTimeMillis();
		CYFLog.debug("[" + filePath + "]转换完成，耗时[" + (end - start) + "]ms");
	}

}
