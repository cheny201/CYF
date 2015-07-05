package com.cy.cyf.util.excel;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.cy.cyf.core.Constant;
import com.cy.cyf.util.ValidateUtil;


/**
 * 读取CSV文件
 * 使用方法：
 * 	1.继承CSVRead
 *  2.实现dealData方法去处理每一行数据
 * 
 * @author ChenYing
 * @createDate 2014-8-20 下午2:19:50
 * 
 */
public abstract class CSVRead {

	protected int titleRow = 0;// 标题行
	private int colNum = 0;//列数
	protected boolean addTitle = false;//是否有标题行

	/**
	 * 每读取一行数据后所调用的方法， 实现该方法即可进行数据处理
	 * 
	 * @param currentRow
	 *            当前行
	 * @param list
	 *            当前行数据
	 * @throws Exception
	 */
	public abstract void dealData(int currentRow, List<String> list)
			throws Exception;

	/**
	 * 执行文件校验
	 * 
	 * @param filePath
	 *            文件路径
	 * @param encoding
	 *            文件编码
	 * @throws Exception
	 */
	public void process(String filePath, String encoding) throws Exception {
		if(ValidateUtil.isEmpty(encoding)){
			encoding = Constant.ENCODING;
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream(filePath), encoding));
		String line = "";
		StringBuffer tmp = new StringBuffer();
		int rowNum = 0;
		while ((line = br.readLine()) != null) {
			tmp.append(line);
			if (isEnd(tmp.toString())) {
				List<String> list = splitStr(tmp.toString(), ',');// 把一行数据分割成多个字段
				if(rowNum == titleRow){
					colNum = list.size();
				}
				
				if(addTitle){
					if (rowNum >= titleRow) {
						addBlank(list);
						dealData(rowNum, list);
					}
				}else{
					if (rowNum > titleRow) {
						addBlank(list);
						dealData(rowNum, list);
					}
				}
				rowNum++;
				tmp.setLength(0);
			}
		}
		br.close();
	};

	/**
	 * 判断一行是否完整
	 * 
	 * @param str
	 * @return
	 */
	private boolean isEnd(String str) {
		int index = -1;
		int startIndex = 0;
		int count = 0;
		while ((index = str.indexOf('"', startIndex)) != -1) {
			count++;
			if (index + 1 < str.length() - 1) {
				startIndex = index + 1;
			} else {
				break;
			}
		}
		return (count % 2 == 0);
	}

	/**
	 * 根据指定字符分割字符串
	 * 
	 * @param str
	 * @param splitChar
	 * @return
	 * @throws Exception
	 */
	private List<String> splitStr(String str, char splitChar) throws Exception {
		List<String> list = new ArrayList<String>();
		if (str.indexOf(splitChar) < 0) {
			list.add(str);
		} else {
			String tmp = str;
			while (true) {
				String splitStr = null;
				int j = tmp.indexOf(splitChar);
				if (j < 0){
					if(tmp.length() > 0){
						list.add(tmp.trim());
					}
					break;
				}
				splitStr = tmp.substring(0, j);
				list.add(splitStr.trim());
				tmp = tmp.substring(j + 1);
			}
		}
		return list;
	}
	
	/**
	 * 添加空格
	 * @param list
	 */
	private void addBlank(List<String> list){
		int len = colNum-list.size();
		for (int i = 0; i < len; i++) {
			list.add("");
		}
	}
	
}
