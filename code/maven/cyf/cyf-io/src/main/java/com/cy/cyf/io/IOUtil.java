package com.cy.cyf.io;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Properties;

import com.cy.cyf.log.CYFLog;

public class IOUtil {
	private static final int SIZE = 1024;

	/**
	 * 加载Properties文件
	 * 
	 * @param path 文件的相对路径
	 * @return
	 */
	public static Properties loadPropertiesByRelative(String path) {
		Properties p = null;
		InputStream inp = null;
		try {
			inp = IOUtil.class.getResourceAsStream(path);
			p = new Properties();
			p.load(inp);
			CYFLog.debug("Properties文件路径[" + path+"]加载成功");
		} catch (IOException e) {
			CYFLog.error("Properties文件路径[" + path+"]加载异常",e);
			p = null;
		} finally {
			closeInputStream(inp);
		}
		return p;
	}

	
	/**
	 * 加载Properties文件
	 * @param path 文件的绝对路径
	 * @return
	 */
	public static Properties loadPropertiesByAbsolute(String path) {
		Properties p = null;
		InputStream inp = null;
		try {
			inp = new FileInputStream(path);
			p = new Properties();
			p.load(inp);
			CYFLog.debug("Properties文件路径[" + path+"]加载成功");
		} catch (Exception e) {
			CYFLog.error("Properties文件路径[" + path+"]加载异常",e);
			p = null;
		} finally {
			closeInputStream(inp);
		}
		return p;
	}

	/**
	 * 从输入流里读取字符串
	 * @param ips
	 * @return
	 */
	public static String readStream(InputStream ips) {
		StringBuffer sb = new StringBuffer();
		BufferedInputStream bis = null;
		try {
			bis = new BufferedInputStream(ips);
			byte[] b = new byte[SIZE];
			int len = 0;
			while ((len = bis.read(b)) != -1) {
				sb.append(new String(b, 0, len));
			}
		} catch (IOException e) {
			CYFLog.error("读取输入流失败", e);
		} finally{
			closeInputStream(bis);
		}
		return sb.toString();
	}
	
	/**
	 * 从输入流里读取字符串
	 * @param ips
	 * @return
	 */
	public static String readStream(InputStream ips,String encoding) {
		StringBuffer sb = new StringBuffer();
		BufferedReader bis = null;
		try {
			bis = new BufferedReader(new InputStreamReader(ips, encoding));
			char[] c = new char[SIZE];
			int len = 0;
			while ((len = bis.read(c)) != -1) {
				sb.append(c, 0, len);
			}
		} catch (IOException e) {
			CYFLog.error("读取输入流失败", e);
		} finally{
			
		}
		return sb.toString();
	}

	public static void closeInputStream(InputStream in) {
		try {
			if (in != null) {
				in.close();
			}
		} catch (IOException e) {
			CYFLog.error("关闭输入流异常", e);
		}
	}

	public static void closeOutputStream(OutputStream out) {
		try {
			if (out != null) {
				out.close();
			}
		} catch (IOException e) {
			CYFLog.error("关闭输出流异常", e);
		}
	}

}
