package com.cyf.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * 通用流操作类
 * @author ChenY201
 * @version 1.0
 * @created 2014-11-12 下午8:34:32
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class StreamOperation {
	/**
	 * 将指定文件拷贝到指定路径下,支持多文件拷贝。 如果拷贝文件在指定目录中存在，此时判断是否覆盖旧文件。
	 * @param file   需要拷贝的文件数组
	 * @param toPath 文件拷贝到的目录路径
	 * @param isDelete  是否覆盖旧文件，true：覆盖，false：不覆盖
	 */
	public void copyFile(File[] file, String toPath, boolean isDelete) {
		for (int i = 0; i < file.length; i++) {
			FileInputStream fis = null;
			FileOutputStream fos = null;
			try {
				File fileTo = new File(toPath + file[i].getName());

				if (fileTo.exists()) {
					if (isDelete) {
						fileTo.delete();
						System.out.println("overwrite old file " + file[i].getName());
					} else {
						System.out.println("not overwrite old file " + file[i].getName());
						continue;
					}
				}

				fis = new FileInputStream(file[i]);
				fos = new FileOutputStream(fileTo);
				byte[] inByte = new byte[1024];
				int len = 0;

				if ((len = fis.read(inByte)) != -1) {
					fos.write(inByte, 0, len);
				}

			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e2) {
				e2.printStackTrace();
			} finally {
				try {
					fis.close();
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		System.out.println("copy file success");

	}


   /**
	* 从文件中读入字符流，返回对应字符串
	* @param filePath 文件路径
	* @return allStr 所有要读取的字符
	*/
	public String readLineStr(String filePath) {
		BufferedReader br = null;
		String lineStr = null;
		String allStr = null;
		StringBuffer sb = new StringBuffer();

		try {
			br = new BufferedReader(new FileReader(filePath));
			while ((lineStr = br.readLine()) != null) {
				allStr = sb.append(lineStr + "\r\n").toString();
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e3) {
				e3.printStackTrace();
			}
		}

		return allStr;
	}

   /**
	*
	* 把字符流的写入文件尾部
	* 首先判断写入的文件是否存在，如果存在，先读取文件内容，然后和新字符串一起写入，
	* 如果不存在，先创建文件，然后写入文件。
	* @param filePath 文件路径
	* @param str 要写入的字符
	* @return
	*/
	public void writeStrToEnd(String filePath, String str) {
		BufferedReader br = null;
		String lineStr = "";
		String allStr = "";
		StringBuffer sb = new StringBuffer();

		BufferedWriter bw = null;
		try {
			File file = new File(filePath);
			if (!file.exists()) {
				file.createNewFile();
			}

			br = new BufferedReader(new FileReader(filePath));
			while ((lineStr = br.readLine()) != null) {
				allStr = sb.append(lineStr + "\r\n").toString();
			}
			bw = new BufferedWriter(new FileWriter(filePath));
			bw.write(allStr + str);
			bw.flush();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		} finally {
			try {
				bw.close();
			} catch (IOException e3) {
				e3.printStackTrace();
			}
		}
	}

	/**
	* 在指定文件中查找特定字符并进行批量替换。
	* @param filePath 文件路径
	* @param findStr 查找的字符
	* @param replaceStr 需要替换的字符
	* @return newStr 替换后的字符串
	*/
	public String findAndReplaceAll(String filePath, String findStr,
			String replaceStr) {
		BufferedReader br = null;
		BufferedWriter bw = null;
		String lineStr = "";
		String allStr = "";
		String newStr = "";
		StringBuffer sb = new StringBuffer();
		try {
			br = new BufferedReader(new FileReader(filePath));
			while ((lineStr = br.readLine()) != null) {
				allStr = sb.append(lineStr + "\r\n").toString();
			}
			//开始查找替换，并把替换后的字符串写入文件。
			int find = allStr.indexOf(findStr);
			if (find != -1) {
				newStr = allStr.replaceAll(findStr, replaceStr);
				bw = new BufferedWriter(new FileWriter(filePath));
				bw.write(newStr);
				bw.flush();
			} else {
				newStr = allStr;
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		} finally {
			try {
				br.close();
				bw.close();
			} catch (IOException e3) {
				e3.printStackTrace();
			}
		}

		return newStr;
	}

	/**
	* 在查找字符的前面或后面插入指定字符，默认在指定字符后面插入。
	* @param filePath 文件路径
	* @param findStr 查找的字符
	* @param insert 插入的字符
	* @return
	*/
	public String insertStr(String filePath, String findStr, String insert) {
		return insertStr(filePath, findStr, insert, null);
	}

	/**
	* 在查找字符的前面或后面插入指定字符，本方法只对所有超找的字符前或后都插入字符。
	* @param filePath 文件路径
	* @param findStr 查找的字符
	* @param insert 插入的字符
	* @param location 插入位置：location值为"before"代表在查找字符前面插入，
	* @return
	*/
	public String insertStr(String filePath, String findStr, String insert,
			String location) {
		BufferedReader br = null;
		BufferedWriter bw = null;
		String lineStr = "";
		String allStr = "";
		String newStr = "";
		StringBuffer sb = new StringBuffer();
		try {
			br = new BufferedReader(new FileReader(filePath));
			while ((lineStr = br.readLine()) != null) {
				allStr = sb.append(lineStr + "\r\n").toString();
			}
			int find = allStr.indexOf(findStr);
			if (find != -1) {
				if ("before".equals(location)) {
					newStr = allStr.replaceAll(findStr, insert + findStr);
				} else {
					newStr = allStr.replaceAll(findStr, findStr + insert);
				}

				bw = new BufferedWriter(new FileWriter(filePath));
				bw.write(newStr);
				bw.flush();
			} else {
				newStr = allStr;
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		} finally {
			try {
				br.close();
				bw.close();
			} catch (IOException e3) {
				e3.printStackTrace();
			}
		}

		return newStr;
	}

	/**
	* 检索字符文档中指定的字符串个数
	* 首先读取所有字符，然后检索指定字符第一次出现的位置，然后从下一个位置开始截取到尾部字符串，计数器+1，
	* 循环截取，如果计数器不等于0，把第一次截取计入计数器。
	* @param filePath 文件路径
	* @param findStr 检索的字符
	* @return count 检索的到的字符个数
	*/
	public int countStr(String filePath, String findStr) {
		BufferedReader br = null;
		String lineStr = "";
		String allStr = "";
		int count = 0;
		StringBuffer sb = new StringBuffer();
		try {
			br = new BufferedReader(new FileReader(filePath));
			while ((lineStr = br.readLine()) != null) {
				allStr = sb.append(lineStr).toString();
			}
			System.out.println("Characters total:" + allStr.length());
			for (int i = 0; i < allStr.length(); i++) {
				int find = allStr.indexOf(findStr);
				if (find != -1) {
					allStr = allStr.substring(find + 1);
					count++;
				}
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e3) {
				e3.printStackTrace();
			}
		}
		if (count != 0) {
			count = count + 1;
		}
		System.out.println("Retrieved a total of " + count + " '" + findStr
				+ "'");
		return count;
	}

	/**
	* 从指定文件中读取map封装的指定对象，文件必须是用map封装的对象
	* @param path 文件路径
	* @param objKey 读取对象关联的key
	* @return 读取的对象
	*/
	public Object readObject(String path, String objKey) {
		Object object = null;
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream(path);
			ois = new ObjectInputStream(fis);

			Map<?, ?> m = (Map<?, ?>) ois.readObject();
			object = (Object) m.get(objKey);

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();

		} catch (IOException e2) {
			e2.printStackTrace();

		} catch (ClassNotFoundException e2) {
			e2.printStackTrace();
		} finally {
			try {
				fis.close();
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return object;
	}

	/**
	* 读取指定文件中map封装的所有对象
	* @param path 文件路径
	* @return objectList map封装的对象集合
	*/

	public List<?> readObjects(String path) {
		List<Object> objectList = new ArrayList<Object>();
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream(path);
			ois = new ObjectInputStream(fis);

			Map<String, Object> map = (Map<String, Object>) ois.readObject();
			if (map.isEmpty()) {
				return null;
			} else {
				Iterator iterator = map.entrySet().iterator();
				while (iterator.hasNext()) {
					Map.Entry entry = (Map.Entry) iterator.next();
					Object object = (Object) entry.getValue();
					objectList.add(object);
				}
			}

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();

		} catch (IOException e2) {
			e2.printStackTrace();

		} catch (ClassNotFoundException e3) {
			e3.printStackTrace();
		} finally {
			try {
				fis.close();
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return objectList;
	}

	/**
	* 把对象流写入到指定文件。
	* 本方法用map封装对象，然后写入指定文件。
	* 先读取文件对象，查找是否有重名key，如果重名，覆盖对象，不重名，对象写入。
	* @param path 文件路径
	* @param objKey 放入map时对象关联的key
	* @param obj 存储对象
	*/

	public void writeObject(String path, String objKey, Object obj) {
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;

		try {
			fis = new FileInputStream(path);

			ois = new ObjectInputStream(fis);
			Map<String, Object> map = (Map<String, Object>) ois.readObject();
			map.put(objKey, obj);

			fos = new FileOutputStream(path);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(map);
			oos.flush();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		} catch (ClassNotFoundException e3) {
			e3.printStackTrace();
		} finally {
			try {
				fis.close();
				ois.close();
				fos.close();
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 功能描述：列出某文件夹及其子文件夹下面的文件，并可根据扩展名过滤
	 *  @param path    文件夹
	 */
	public static void list(File path) {
		if (!path.exists()) {
			System.out.println("文件名称不存在!");
		} else {
			if (path.isFile()) {
				if (path.getName().toLowerCase().endsWith(".pdf")
						|| path.getName().toLowerCase().endsWith(".doc")
						|| path.getName().toLowerCase().endsWith(".chm")
						|| path.getName().toLowerCase().endsWith(".html")
						|| path.getName().toLowerCase().endsWith(".htm")) {// 文件格式
					System.out.println(path);
					System.out.println(path.getName());
				}
			} else {
				File[] files = path.listFiles();
				for (int i = 0; i < files.length; i++) {
					list(files[i]);
				}
			}
		}
	}
	
	
}