package com.cyf.util.mail;

import java.io.File;

/**
 * 附件信息
 * @author ChenY201
 * @version 1.0
 * @created 2014-11-22 下午1:23:37
 */
public class AttachmentInfo {

	private String fileName;
	private File file;
	
	public String getFileName() {
		return (fileName==null || "".equals(fileName))?file.getName():fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
}
