package com.cy.cyf.util.excel;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import com.cy.cyf.core.exception.CYFException;
import com.cy.cyf.log.CYFLog;
import com.cy.cyf.util.ValidateUtil;


/**
 * 读取大数据量07版Excel
 * 
 * @author ChenYing
 * @createDate 2014-6-26 上午9:21:25
 * 
 */
public abstract class XlsxReader extends DefaultHandler {

	private SharedStringsTable sst;
	private String lastName;
	private String lastContents;
	private boolean nextIsString;

	private int sheetIndex = -1;
	private List<String> rowlist = new ArrayList<String>();
	private int curRow = 0;

	private String colName1 = "A1";//上一次添加值时的列名
	private String colName2 = "";//当前列名
	private int colCount = 0;//总列数
	protected int titleRowNum = 0;// 标题行号
	
	private boolean setOptions = false;
	private int[] colCounts = null;
	private int[] dataStartRow = null;
	
	protected void setOptions(boolean setOptions,int[] colCounts,int[] dataStartRow){
		this.setOptions = setOptions;
		this.colCounts = colCounts;
		this.dataStartRow = dataStartRow;
	}
	
	/**
	 * excel记录行操作方法，以sheet索引，行索引和行元素列表为参数，对sheet的一行元素进行操作，元素为String类型
	 * @param sheetIndex
	 * @param curRow
	 * @param rowlist
	 * @throws Exception
	 */
	public abstract void dealData(int sheetIndex, int curRow,
			List<String> rowlist) throws Exception;

	// 只遍历一个sheet，其中sheetId为要遍历的sheet索引，从1开始，1-3
	public void processOneSheet(String filename, int sheetId) throws Exception {
		OPCPackage pkg = OPCPackage.open(filename);
		XSSFReader r = new XSSFReader(pkg);
		SharedStringsTable sst = r.getSharedStringsTable();

		XMLReader parser = fetchSheetParser(sst);

		// rId2 found by processing the Workbook
		// 根据 rId# 或 rSheet# 查找sheet
		InputStream sheet2 = r.getSheet("rId" + sheetId);
		sheetIndex++;
		InputSource sheetSource = new InputSource(sheet2);
		parser.parse(sheetSource);
		sheet2.close();
	}

	/**
	 * 遍历 excel 文件
	 */
	public void process(String filename) throws Exception {
		OPCPackage pkg = OPCPackage.open(filename);
		XSSFReader r = new XSSFReader(pkg);
		SharedStringsTable sst = r.getSharedStringsTable();

		XMLReader parser = fetchSheetParser(sst);

		Iterator<InputStream> sheets = r.getSheetsData();
		try {
			sheetIndex = -1;
			while (sheets.hasNext()) {
				curRow = 0;
				sheetIndex++;
				if(setOptions){
					if(sheetIndex < colCounts.length){
						colCount = colCounts[sheetIndex];
						titleRowNum = dataStartRow[sheetIndex]-1;
					}else{
						colCount = colCounts[colCounts.length-1];
						titleRowNum = dataStartRow[dataStartRow.length-1]-1;
					}
				}
				
				InputStream sheet = sheets.next();
				InputSource sheetSource = new InputSource(sheet);
				parser.parse(sheetSource);
				sheet.close();
			}
		} catch (Exception e) {
			throw new CYFException(e);
		}
	}

	public XMLReader fetchSheetParser(SharedStringsTable sst)
			throws SAXException {
		XMLReader parser = XMLReaderFactory
				.createXMLReader("org.apache.xerces.parsers.SAXParser");
		this.sst = sst;
		parser.setContentHandler(this);
		return parser;
	}

	public void startElement(String uri, String localName, String name,
			Attributes attributes) throws SAXException {
//		if("row".equals(name)){
//			curRow = Integer.parseInt(attributes.getValue("r"));
//		}
		
		
		
		if ("c".equals(name)) {// c => 单元格
			// 如果下一个元素是 SST 的索引，则将nextIsString标记为true
			String cellType = attributes.getValue("t");
			if (cellType != null && cellType.equals("s")) {
				nextIsString = true;
			} else {
				nextIsString = false;
			}
			colName2 = attributes.getValue("r");
		}
		// 置空
		lastContents = "";
	}

	public void endElement(String uri, String localName, String name)
			throws SAXException {
		// 根据SST的索引值的到单元格的真正要存储的字符串
		// 这时characters()方法可能会被调用多次
//		TAFLog.info("endElement:"+name);
		if (nextIsString) {
			if(!colName2.equals(lastName)){
				try {
					int idx = Integer.parseInt(lastContents);
					lastContents = new XSSFRichTextString(sst.getEntryAt(idx))
							.toString();
				} catch (Exception e) {
					CYFLog.error("",e);
				}
				lastName = colName2;
			}
		}

		// v => 单元格的值，如果单元格是字符串则v标签的值为该字符串在SST中的索引
		// 将单元格内容加入rowlist中，在这之前先去掉字符串前后的空白符
		if ("v".equals(name)) {
			// ------------判断单元格是否为空开始----------------
			if (curRow > titleRowNum) {
				if (!colName1.equals(colName2)) {
					
					if(getCurrentNum(colName1) != getCurrentNum(colName2)){
						curRow = getCurrentNum(colName2);
						colName1 = colName1.substring(0,getNumIndex(colName1))+curRow;
						curRow --;
					}
					
					while (!colName1.equals(colName2)) {
						rowlist.add("");
						colName1 = nextColName(colName1, curRow + 1);
					}
				}
				colName1 = nextColName(colName1, curRow + 1);
			}
			// ------------判断单元格是否为空结束----------------

			String value = lastContents.trim();
			rowlist.add(value);
		} else {
			// 如果标签名称为 row ，这说明已到行尾，调用 optRows() 方法
			if (name.equals("row")) {
				if (curRow == titleRowNum) {
					if(!setOptions){
						colCount = rowlist.size();
					}
				} else if (curRow > titleRowNum) {
					int len = colCount - rowlist.size();
					for (int i = 0; i < len; i++) {
						rowlist.add("");
					}
					
					try {
						if(!isListEmpty(rowlist)){
							dealData(sheetIndex, curRow, rowlist);
						}
					} catch (Exception e) {
						throw new SAXException(e);
					}
				}

				rowlist.clear();
				curRow++;
				colName1 = "A" + (curRow + 1);
			}
		}
	}

	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// 得到单元格内容的值
		lastContents += new String(ch, start, length);
		// System.out.println(lastContents);
	}

	public String nextColName(String str, int row) {
		int index = str.lastIndexOf(row + "");
		if (index != -1) {
			char[] tmp = str.substring(0, index).toCharArray();
			boolean isEnd = false;
			boolean isAdd = false;
			int i = tmp.length - 1;
			try {
				while (!isEnd) {
					tmp[i]++;
					if (tmp[i] > 'Z') {
						tmp[i] = 'A';
						i--;
					} else {
						isEnd = true;
					}
					if (i < 0) {
						isAdd = true;
						isEnd = true;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			String reStr = new String(tmp) + row;
			if (isAdd) {
				reStr = "A" + reStr;
			}
			return reStr;
		} else {
			return "";
		}
	}
	
	private boolean isListEmpty(List<String> list){
		boolean r = true;
		for (int i = 0; i < list.size(); i++) {
			if(!ValidateUtil.isEmpty(list.get(i))){
				r = false;
				break;
			}
		}
		return r;
	}
	
	private int getCurrentNum(String str){
		int index = getNumIndex(str);
		if(index !=-1){
			return Integer.parseInt(str.substring(index));
		}else{
			return -1;
		}
		
	}
	
	private int getNumIndex(String str){
		int index = -1;
		for (int i = 0; i < str.length(); i++) {
			try {
				Integer.parseInt(str.charAt(i)+"");
			} catch (NumberFormatException e) {
				continue;
			}
			index = i;
			break;
		}
		return index;
	}
	
}
