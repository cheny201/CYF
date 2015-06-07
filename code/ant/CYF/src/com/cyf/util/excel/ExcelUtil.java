package com.cyf.util.excel;

import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;

/**
 * 日期： 2011-08-02
 */
public abstract class ExcelUtil{
	
	protected HSSFWorkbook wb = null;
	protected HSSFSheet sheet = null;
	protected HSSFRow row = null;
	
	public ExcelUtil(){
		wb = new HSSFWorkbook();
	}
	
	protected HSSFSheet createSheet(String name){
		sheet = wb.createSheet(name);
		return sheet;
	}
	
	/**
	 * 设置列自适应宽度
	 * @param auto
	 */
	protected void autoSizeColumn(int colNum){
		for(short r = 0; r < colNum; r++){
			sheet.autoSizeColumn(r);
		}
	}
	
	protected void createHead(String[] colNames,short width){
		if(colNames==null || colNames.length<=0)
			return;
		for(int x=0;x<colNames.length;x++){
			createCell(row, (short)x, (short)(256*width)).setCellValue(new HSSFRichTextString(colNames[x]));
		}
	}
	
	/**
	 * 合并单元格
	 * @param rowFrom 起始行
	 * @param colFrom 起始列
	 * @param rowTO 终止行
	 * @param colTo 终止列
	 */
	protected int mergingCells(int rowFrom,short colFrom,int rowTO,short colTo){
		return sheet.addMergedRegion(new Region(rowFrom,colFrom,rowTO,colTo));
	}
	
	protected HSSFRow createRow(int rowNum){
		return sheet.createRow(rowNum);
	}
	
	/**
	 * 创建行，并设置行的高度
	 * @param rowNum
	 * @param height
	 * @return 
	 */
	protected HSSFRow createRow(int rowNum,short height){
		HSSFRow row = sheet.createRow(rowNum);
		row.setHeight((short)(height*20));
		return row;
	}
	
	/**
	 * 创建指定位置的列
	 * @param row
	 * @param cellNum
	 * @return
	 */
	protected HSSFCell createCell(HSSFRow row,short cellNum) {
		HSSFCell cell = row.createCell(cellNum);
		
		HSSFCellStyle cellStyle = wb.createCellStyle();;
		cellStyle.setWrapText(true);
		cell.setCellStyle(cellStyle);
		
		//数据居中
		cellCenter(cell);
		return cell;
	}
	
	/**
	 * 创建指定位置的列，并设置列的宽度
	 * @param row
	 * @param cellNum
	 * @param width
	 * @return
	 */
	protected HSSFCell createCell(HSSFRow row,short cellNum,short width) {
		HSSFCell cell = row.createCell(cellNum);
		
		HSSFCellStyle cellStyle = wb.createCellStyle();;
		cellStyle.setWrapText(true);

		cell.setCellStyle(cellStyle);
		sheet.setColumnWidth(cellNum, width);
		//数据居中
		cellCenter(cell);
		return cell;
	}
	
	/**
	 * 创建指定位置的列，并设置列的宽度和边框
	 * @param row
	 * @param cellNum
	 * @param width
	 * @return 
	 */
	protected HSSFCell createCell(HSSFRow row,short cellNum,short width,boolean border) {
		HSSFCell cell = row.createCell(cellNum);
		
		HSSFCellStyle cellStyle = wb.createCellStyle();;
		cellStyle.setWrapText(true);

		cell.setCellStyle(cellStyle);
		sheet.setColumnWidth(cellNum, width);
		//数据居中
		cellCenter(cell);
		if(border){
			//设置边框
			cellBorder(cell);
		}
		return cell;
	}
	
	/**
	 * 设置单元格边框为黑色
	 * @param cell
	 */
	protected void cellBorder(HSSFCell cell){
		HSSFCellStyle style = cell.getCellStyle();
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setTopBorderColor(new HSSFColor.BLACK().getIndex());
		style.setRightBorderColor(new HSSFColor.BLACK().getIndex());
		style.setBottomBorderColor(new HSSFColor.BLACK().getIndex());
		style.setLeftBorderColor(new HSSFColor.BLACK().getIndex());
	}
	
	/**
	 * 字体加粗
	 * @param cell
	 */
	protected void blobFont(HSSFCell cell){
		HSSFCellStyle style = cell.getCellStyle();
		HSSFFont font = wb.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		style.setFont(font);
		
	}
	
	/**
	 * 设置字体大小
	 * @param cell
	 * @param size
	 */
	protected void fontSize(HSSFCell cell,short size){
		HSSFCellStyle style = cell.getCellStyle();
		HSSFFont font = wb.createFont();
		font.setFontHeight((short)(size*20));
		style.setFont(font);
	}
	
	/**
	 * 设置字体加粗及设置大小
	 * @param cell
	 * @param size
	 */
	protected void boldSizeFont(HSSFCell cell,short size){
		HSSFCellStyle style = cell.getCellStyle();
		HSSFFont font = wb.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontHeight((short)(size*20));
		style.setFont(font);
	}
	
	/**
	 * 设置单元格字体
	 * @param cell
	 * @param size
	 */
	protected void boldSizeFont(HSSFCell cell,HSSFFont font){
		HSSFCellStyle style = cell.getCellStyle();
		style.setFont(font);
	}
	
	protected void writeData(HSSFRow row,short cellNum,String value){
		if(value == null)
			value = "";
		row.getCell(cellNum).setCellValue(new HSSFRichTextString(value));
	}
	
	protected void writeData(HSSFRow row,short cellNum,Double value){
		row.getCell(cellNum).setCellValue(value);
	}

	protected void writeData(HSSFRow row,short cellNum,int value){
		row.getCell(cellNum).setCellValue(value);
	}
	
	protected void writeData(HSSFRow row,short cellNum,Date value){
		HSSFDataFormat dataFormat = wb.createDataFormat();

		HSSFCellStyle style = row.getCell(cellNum).getCellStyle();
		style.setDataFormat(dataFormat.getFormat("yyyy-MM-dd"));
		
		row.getCell(cellNum).setCellStyle(style);
		if(value==null)
			row.getCell(cellNum).setCellValue(new HSSFRichTextString(""));
		else
			row.getCell(cellNum).setCellValue(value);
	}
	
	protected void writeData(HSSFRow row,short cellNum,Date value,String format){
		HSSFDataFormat dataFormat = wb.createDataFormat();

		HSSFCellStyle style = row.getCell(cellNum).getCellStyle();
		style.setDataFormat(dataFormat.getFormat(format));
		
		row.getCell(cellNum).setCellStyle(style);
		row.getCell(cellNum).setCellValue(value);
	}
	/**
	 * 列内容居中显示
	 * @param cell
	 */
	protected void cellCenter(HSSFCell cell) {
		HSSFCellStyle cellStyle = cell.getCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	}
	
	/**
	 * 为单元格设置背景颜色
	 * @param cell
	 * @param color：new HSSFColor.RED().getIndex(); 
	 */
	protected void cellBackColor(HSSFCell cell,short color){
		HSSFCellStyle cellStyle = cell.getCellStyle();
		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cellStyle.setFillForegroundColor(color);
	}
	
	/**
	 * 在指定行中创建一定数量的列
	 * @param row
	 * @param size
	 */
	protected void createCells(HSSFRow row ,int size) {
		for(int i = 0;i<size;i++){
			createCell(row, (short)i);
		}
	}
	
	/**
	 * 在指定行中创建一定数量的列并设置边框
	 * @param row
	 * @param size
	 * @param border
	 */
	protected void createCells(HSSFRow row ,int size,boolean border) {
		HSSFCell cell = null;
		for(int i = 0;i<size;i++){
			cell = createCell(row, (short)i);
			if(border){
				cellBorder(cell);
			}
		}
	}
	
	public abstract HSSFWorkbook createExcel() throws Exception;
	
	protected abstract void buildHeader();
}
