package com.cyf.util.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.cyf.log.CYFLog;

/**
 * 文件工具类
 * @author ChenY201
 * @version 1.0
 * @created 2014-11-12 下午8:33:19
 */
public final class FileUtil {

	/**
	 * 加载Properties文件
	 * @param path 路径
	 * @return
	 */
	public static Properties loadProperties(String path) {
		File f = new File(path);
		Properties p = null;
		if (f.exists()) {
			p = new Properties();
			try {
				p.load(new FileInputStream(f));
			} catch (Exception e) {
				CYFLog.error("加载Properties文件失败：" + path, e);
				p = null;
			}
		}
		return p;
	}

	/**
	 * 加载资源
	 * @param path 包的相对路径
	 * @return
	 */
	public static Properties loadResource(String path) {
		Properties p = null;
		try {
			p = new Properties();
			p.load(FileUtil.class.getResourceAsStream(path));
		} catch (Exception e) {
			CYFLog.error("加载资源失败：" + path, e);
			p = null;
		}
		return p;
	}

	/**
	 * 读取文件
	 * @param path 待读取的文件路径
	 * @return
	 * @throws IOException
	 */
	public static String loadFile(String path) throws IOException {
		File f = new File(path);
		StringBuffer str = new StringBuffer();
		if (f.exists() && f.isFile()) {
			str.append(readFile(f));
		}
		return str.toString();
	}

	/**
	 * 读取文件
	 * @param f 待读取的文件
	 * @return
	 * @throws IOException
	 */
	public static String readFile(File f) throws IOException {
		StringBuffer str = new StringBuffer();
		if (f.exists() && f.isFile()) {
			InputStream in = new FileInputStream(f);
			int len = 0;
			byte[] b = new byte[1024 * 10];
			while ((len = in.read(b)) != -1) {
				str.append(new String(b, 0, len));
			}
			in.close();
		}
		return str.toString();
	}

	/**
	 * 写文件
	 * @param in 输入流
	 * @param targetPath 目标文件路径
	 * @throws IOException
	 */
	public static void writeToFile(InputStream in, String targetPath)
			throws IOException {
		File f = new File(targetPath);
		File parentFile = f.getParentFile();
		if (!parentFile.exists()) {
			parentFile.mkdirs();
		}
		if (!f.exists()) {
			f.createNewFile();
		}
		write(in, new FileOutputStream(f));
	}

	/**
	 * 写入
	 * @param in 输入流
	 * @param out 输出流
	 */
	public static void write(InputStream in, OutputStream out) {
		BufferedOutputStream bos = null;
		try {
			bos = new BufferedOutputStream(out);

			int len = 0;
			byte[] b = new byte[1024 * 10];
			while ((len = in.read(b)) != -1) {
				bos.write(b, 0, len);
			}
			bos.flush();
		} catch (Exception e) {
			CYFLog.error("", e);
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					CYFLog.error("", e);
				}
			}
		}
	}
	
	/**
	 * 将字符串写入流
	 * @param str 要写入的字符串
	 * @param out 输出流
	 */
	public static void writeToFile(String str, OutputStream out){
		BufferedOutputStream bos = null;
		try {
			bos = new BufferedOutputStream(out);
			bos.write(str.getBytes());
			bos.flush();
		} catch (Exception e) {
			CYFLog.error("", e);
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					CYFLog.error("", e);
				}
			}
		}
	}

	/**
	 * 替换文件里的字符串
	 * @param fromStr 用来匹配此字符串
	 * @param toStr 用来替换每个匹配项的字符串
	 * @param fileType 文件类型
	 * @param dir 待替换的文件或目录
	 * @param replaceChildren 子目录是否替换
	 * @throws IOException
	 */
	public static void replaceFiles(String fromStr, String toStr,
			String[] fileType, File dir, boolean replaceChildren)
			throws IOException {
		if (dir != null) {
			if (dir.isFile()) {
				for (int i = 0; i < fileType.length; i++) {
					if (dir.getName().endsWith(fileType[i])) {
						String str = readFile(dir);
						System.out.println("==============");
						System.out.println(str);
						str = str.replaceAll(fromStr, toStr);
						File tmp = new File(dir.getAbsolutePath()+".tmp");
						if(!tmp.exists()){
							tmp.createNewFile();
						}
						writeToFile(str.toString(), new FileOutputStream(tmp));
						String path = dir.getAbsolutePath();
						dir.delete();
						tmp.renameTo(new File(path));
						break;
					}
				}
			} else if (dir.isDirectory()) {
				File[] files = dir.listFiles();
				for (int i = 0; i < files.length; i++) {
					File f = files[i];
					if ((f.isDirectory() && replaceChildren) || f.isFile()) {
						replaceFiles(fromStr, toStr, fileType, f,
								replaceChildren);
					}
				}
			}
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
            System.out.println(">>>>>> 待压缩的文件目录：" + sourceFilePath + " 不存在. <<<<<<");
        } else {
            try {
                File zipFile = new File(zipFilePath + "/" + fileName + ".zip");
                if(zipFile.exists()) {
                    System.out.println(">>>>>> " + zipFilePath + " 目录下存在名字为：" + fileName + ".zip" + " 打包文件. <<<<<<");
                } else {
                    File[] sourceFiles = sourceFile.listFiles();
                    if(null == sourceFiles || sourceFiles.length < 1) {
                        System.out.println(">>>>>> 待压缩的文件目录：" + sourceFilePath + " 里面不存在文件,无需压缩. <<<<<<");
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
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            } finally {
                // 关闭流
                try {
                    if(null != bis) bis.close();
                    if(null != zos) zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        }
         
        return flag;
    }
}
