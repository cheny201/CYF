package com.cy.cyf.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.cy.cyf.core.Constant;
import com.cy.cyf.core.exception.IOStreamException;
import com.cy.cyf.log.CYFLog;

public class IOUtil {
	

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
			byte[] b = new byte[Constant.IO_SIZE];
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
	 * 从输入流里以指定编码读取字符串
	 * @param ips
	 * @param encoding
	 * @return
	 */
	public static String readStream(InputStream ips,String encoding) {
		StringBuffer sb = new StringBuffer();
		BufferedReader bis = null;
		try {
			bis = new BufferedReader(new InputStreamReader(ips, encoding));
			char[] c = new char[Constant.IO_SIZE];
			int len = 0;
			while ((len = bis.read(c)) != -1) {
				sb.append(c, 0, len);
			}
		} catch (IOException e) {
			CYFLog.error("读取输入流失败", e);
		} finally{
			closeReader(bis);
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
	
	public static void closeReader(Reader reader) {
		try {
			if (reader != null) {
				reader.close();
			}
		} catch (IOException e) {
			CYFLog.error("关闭输入/输出流异常", e);
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
	
	/**
	 * 从输入流读取数据写入到输出流
	 * @param in 输入流
	 * @param out 输出流
	 */
	public static void write(InputStream in, OutputStream out) {
		BufferedOutputStream bos = null;
		try {
			bos = new BufferedOutputStream(out);
			int len = 0;
			byte[] b = new byte[Constant.IO_SIZE];
			while ((len = in.read(b)) != -1) {
				bos.write(b, 0, len);
			}
			bos.flush();
		} catch (Exception e) {
			CYFLog.error("", e);
		} finally {
			closeOutputStream(bos);
		}
	}

	/**
     * 将存放在sourceFilePath目录下的源文件,打包成fileName名称的ZIP文件,并存放到zipFilePath。
     * @param sourceFilePath 待压缩的文件路径
     * @param zipFilePath    压缩后存放路径
     * @param fileName       压缩后文件的名称
     * @return flag
     */
	public static boolean fileToZip(String sourceFilePath,String zipFilePath,String fileName) {
        boolean flag = false;
        File sourceFile = new File(sourceFilePath);
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        ZipOutputStream zos = null;
         
        if(sourceFile.exists() == false) {
            CYFLog.debug(">>>>>> 待压缩的文件目录：" + sourceFilePath + " 不存在. <<<<<<");
        } else {
            try {
                File zipFile = new File(zipFilePath + "/" + fileName + ".zip");
                if(zipFile.exists()) {
                	CYFLog.debug(">>>>>> " + zipFilePath + " 目录下存在名字为：" + fileName + ".zip" + " 打包文件. <<<<<<");
                } else {
                    File[] sourceFiles = sourceFile.listFiles();
                    if(null == sourceFiles || sourceFiles.length < 1) {
                    	CYFLog.debug(">>>>>> 待压缩的文件目录：" + sourceFilePath + " 里面不存在文件,无需压缩. <<<<<<");
                    } else {
                        fos = new FileOutputStream(zipFile);
                        zos = new ZipOutputStream(new BufferedOutputStream(fos));
                        byte[] bufs = new byte[1024*10];
                        for(int i=0;i<sourceFiles.length;i++) {
                            // 创建ZIP实体,并添加进压缩包
                            ZipEntry zipEntry = new ZipEntry(sourceFiles[i].getName());
                            zos.putNextEntry(zipEntry);
                            // 读取待压缩的文件并写进压缩包里
                            fis = new FileInputStream(sourceFiles[i]);
                            bis = new BufferedInputStream(fis,1024*10);
                            int read = 0;
                            while((read=bis.read(bufs, 0, 1024*10)) != -1) {
                                zos.write(bufs, 0, read);
                            }
                        }
                        flag = true;
                    }
                }
            } catch (Exception e) {
                throw new IOStreamException(e);
            } finally {// 关闭流
            	closeInputStream(bis);
            	closeInputStream(fis);
            	closeOutputStream(zos);
            	closeOutputStream(fos);
            }
        }
        return flag;
    }
	
}
