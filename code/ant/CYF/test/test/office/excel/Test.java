package test.office.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.cxf.tools.corba.utils.FileOutputStreamFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;


import com.cyf.util.Arith;
import com.cyf.util.Tools;

public class Test {
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) throws Exception {
		String[] dirPaths = {"C:\\Users\\ChenY201\\Desktop\\新建文件夹 (2)"};
		//楼号-分区-房间号-时间-分数
		Map<String,Map<String,Map<String, Map<String, List>>>> map = new HashMap<String,Map<String,Map<String, Map<String, List>>>>();
		
		for (int i = 0; i < dirPaths.length; i++) {
			File dir = new File(dirPaths[i]);
			File[] files = dir.listFiles();
			for (int j = 0; j < files.length; j++) {
				String fileName = files[j].getName();
				String[] tmp = fileName.split("-");
				String buildNo = tmp[0];
				String date = tmp[1].substring(0, tmp[1].indexOf(".xls"));
				String month = date.split("\\.")[0];
				Map<String,Map<String, Map<String, List>>> map1 = map.get(buildNo);//楼号
				if(map1 == null){
					map1 = new HashMap<String,Map<String, Map<String, List>>>();
					map.put(buildNo, map1);
				}
				HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(files[j]));
				for (int k = 0; k < wb.getNumberOfSheets(); k++) {
					HSSFSheet sheet = wb.getSheetAt(k);
					Map<String, Map<String, List>> map2 = map1.get(sheet.getSheetName());//分区
					if(map2 == null){
						map2 = new HashMap<String, Map<String, List>>();
						map1.put(sheet.getSheetName(), map2);
					}
					
					
					Iterator<Row> it = sheet.rowIterator();
					boolean start = false;
					while(it.hasNext()){
						HSSFRow row = (HSSFRow) it.next();
						HSSFCell roomNo = row.getCell(0);
						if(roomNo != null){
							String value = Test.getStringCellValue(roomNo);//房间号
							if("房号".equals(value)){
								start = true;
								continue;
							}
							
							if(start){
								if(Tools.isBlank(value)){
									continue;
								}
								
								HSSFCell sum1 = row.getCell(9);
								HSSFCell sum2 = row.getCell(17);
								String d1 = Test.getStringCellValue(sum1);
								String d2 = Test.getStringCellValue(sum2);
								if(!Tools.isBlank(d1)){
									Map<String, List> map3 = map2.get(value);//房间号
									if(map3 == null){
										map3 = new HashMap<String, List>();
										map2.put(value, map3);
									}
									List ls = map3.get(date);//时间
									if(ls == null){
										ls = new ArrayList();
										map3.put(date, ls);
										ls.add(month);
									}
									try {
										if(Tools.isBlank(d1)){
											d1 = "0.0";
										}
										if(Tools.isBlank(d2)){
											d2 = "0.0";
										}
										ls.add(Double.parseDouble(d1));
										ls.add(Double.parseDouble(d2));
									} catch (NumberFormatException e) {
										System.out.println(row.getRowNum()+"-------");
										e.printStackTrace();
									}
								}
							}
						}
					}
				}
			}
		}
		//月统计------------------------------------------
		//楼号-分区-房间号
		Map<String,Map<String,Map<String, List<Double[]>>>> monthSumMap = new HashMap<String, Map<String,Map<String,List<Double[]>>>>();
		
		//周统计------------------------------------------
		//楼号-分区-房间号
		Map<String,Map<String,Map<String, List<Double[][]>>>> weekSumMap = new HashMap<String, Map<String,Map<String,List<Double[][]>>>>();
				
		Iterator<String> it1 = map.keySet().iterator();
		while(it1.hasNext()){
			String buildNo = it1.next();//楼号
			Map<String,Map<String, Map<String, List>>> map1 = map.get(buildNo);
			Iterator<String> it2 = map1.keySet().iterator();
			while(it2.hasNext()){
				String fqNo = it2.next();//分区
				Map<String, Map<String, List>> map2 = map1.get(fqNo);
				Iterator<String> it3 = map2.keySet().iterator();
				while(it3.hasNext()){
					String rooNo = it3.next();//房间号
					Map<String, List> map3 = map2.get(rooNo);
					Double[] sum1 = {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
					Double[] sum2 = {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
					int[] count1 = {0,0,0,0,0,0,0,0,0,0,0,0};//月次数
					
					Double[][] weekSum1 = {{0.0,0.0,0.0,0.0,0.0},{0.0,0.0,0.0,0.0,0.0},{0.0,0.0,0.0,0.0,0.0},{0.0,0.0,0.0,0.0,0.0},{0.0,0.0,0.0,0.0,0.0},{0.0,0.0,0.0,0.0,0.0},{0.0,0.0,0.0,0.0,0.0},{0.0,0.0,0.0,0.0,0.0},{0.0,0.0,0.0,0.0,0.0},{0.0,0.0,0.0,0.0,0.0},{0.0,0.0,0.0,0.0,0.0},{0.0,0.0,0.0,0.0,0.0}};
					Double[][] weekSum2 = {{0.0,0.0,0.0,0.0,0.0},{0.0,0.0,0.0,0.0,0.0},{0.0,0.0,0.0,0.0,0.0},{0.0,0.0,0.0,0.0,0.0},{0.0,0.0,0.0,0.0,0.0},{0.0,0.0,0.0,0.0,0.0},{0.0,0.0,0.0,0.0,0.0},{0.0,0.0,0.0,0.0,0.0},{0.0,0.0,0.0,0.0,0.0},{0.0,0.0,0.0,0.0,0.0},{0.0,0.0,0.0,0.0,0.0},{0.0,0.0,0.0,0.0,0.0}};
					int[][] count2 = {{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0}};//周次数
					Iterator<String> it4 = map3.keySet().iterator();
					while(it4.hasNext()){
						String queryDate = it4.next();//时间
						List ls = map3.get(queryDate);
						int index = Integer.parseInt((String)ls.get(0))-1;
						
						sum1[index] = Arith.add(sum1[index], (Double)ls.get(1));
						sum2[index] = Arith.add(sum2[index], (Double)ls.get(2));
//						sum1[index] += (Double)ls.get(1);
//						sum2[index] += (Double)ls.get(2);
						count1[index] ++;
						
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(Tools.converDate(queryDate, "mm.dd"));
						int weekNo = calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH);
						weekSum1[index][weekNo] = Arith.add(weekSum1[index][weekNo], (Double)ls.get(1));
						weekSum2[index][weekNo] = Arith.add(weekSum2[index][weekNo], (Double)ls.get(2));
						count2[index][weekNo] ++;
					}
					
					for (int i = 0; i < 12; i++) {
						if(count1[i] != 0){
							sum1[i] = Arith.div(sum1[i], count1[i], 2);
							sum2[i] = Arith.div(sum2[i], count1[i], 2);
//							sum1[i] = sum1[i]/count1[i];
//							sum2[i] = sum2[i]/count1[i];
						}
						
						for (int j = 0; j < 5; j++) {
							weekSum1[i][j] = Arith.div(weekSum1[i][j], count2[i][j],2);
							weekSum2[i][j] = Arith.div(weekSum2[i][j], count2[i][j],2);
						}
					}
					
					
					Map<String,Map<String, List<Double[]>>> m1 = monthSumMap.get(buildNo);
					if(m1 == null){
						m1 = new HashMap<String, Map<String,List<Double[]>>>();
						monthSumMap.put(buildNo, m1);
					}
					Map<String, List<Double[]>> m2 = m1.get(fqNo);
					if(m2 == null){
						m2 = new HashMap<String, List<Double[]>>();
						m1.put(fqNo, m2);
					}
					List<Double[]> ls1 = new ArrayList<Double[]>();
					ls1.add(sum1);
					ls1.add(sum2);
					m2.put(rooNo, ls1);
					
					Map<String,Map<String, List<Double[][]>>> w1 = weekSumMap.get(buildNo);
					if(w1 == null){
						w1 = new HashMap<String, Map<String,List<Double[][]>>>();
						weekSumMap.put(buildNo, w1);
					}
					Map<String, List<Double[][]>> w2 = w1.get(fqNo);
					if(w2 == null){
						w2 = new HashMap<String, List<Double[][]>>();
						w1.put(fqNo, w2);
					}
					List<Double[][]> ls2 = new ArrayList<Double[][]>();
					ls2.add(weekSum1);
					ls2.add(weekSum2);
					w2.put(rooNo, ls2);
				}
			}
		}
		
		
		//生成报表
		
		Iterator<String> it11 = map.keySet().iterator();
		while(it11.hasNext()){
			String buildNo = it11.next();//楼号
			Map<String,Map<String, List<Double[]>>> mapTmp = monthSumMap.get(buildNo);
			for (int j = 0; j < 12; j++) {
				String fileNameString = dirPaths[0]+File.separator+"生成文件"+File.separator+buildNo+"号楼综合评分"+(j+1)+"月.xls";
				File newFile = new File(fileNameString);
				File parentFile = newFile.getParentFile();
				if(!parentFile.exists()){
					parentFile.mkdirs();
				}
				if(!newFile.exists()){
					newFile.createNewFile();
				}
				
				HSSFWorkbook book = Test.createExcelByMonth(mapTmp, j);
				FileOutputStream ops = new FileOutputStream(newFile);
				book.write(ops);
				ops.flush();
				ops.close();
			}
			
			
		}
		
		
		
		
		
		
		
		
		
		
		
		

		System.out.println("结束");
	}
	
	/**
     * 获取单元格数据内容为字符串类型的数据
     * 
     * @param cell Excel单元格
     * @return String 单元格数据内容
     */
    public static String getStringCellValue(HSSFCell cell) {
        String strCell = "";
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
        case HSSFCell.CELL_TYPE_STRING:
            strCell = cell.getStringCellValue();
            break;
        case HSSFCell.CELL_TYPE_NUMERIC:
            strCell = String.valueOf(cell.getNumericCellValue());
            break;
        case HSSFCell.CELL_TYPE_BOOLEAN:
            strCell = String.valueOf(cell.getBooleanCellValue());
            break;
        case HSSFCell.CELL_TYPE_BLANK:
            strCell = "";
            break;
        default:
            strCell = "";
            break;
        }
        if (strCell.equals("") || strCell == null) {
            return "";
        }
        return strCell;
    }
    
    /**
     * 
     * @param map 分区-房间号-分数
     * @return
     */
    public static HSSFWorkbook createExcelByMonth(Map<String, Map<String, List<Double[]>>> map,int month){
    	HSSFWorkbook workbook = new HSSFWorkbook();// 创建新的Excel 工作簿
		try{  
			// 在Excel工作簿中建一工作表，其名为缺省值  
			// 如要新建一名为"效益指标"的工作表，其语句为：  
			// HSSFSheet sheet = workbook.createSheet("效益指标");
			Iterator<String> it1 = map.keySet().iterator();
			while(it1.hasNext()){
				String fqNo = it1.next();//分区
				  
				HSSFSheet sheet = workbook.createSheet(fqNo);// 在Excel工作簿中建一工作表，其名为缺省值
				HSSFRow row = sheet.createRow(0);// 在索引0的位置创建行（最顶端的行）
				HSSFCell cell = row.createCell(0);//在索引0的位置创建单元格（左上端）  
				cell.setCellValue("综合情况周统计表（8）栋（"+fqNo+"）区　日期：11.10-11.14");
				HSSFRow titleRow = sheet.createRow(1);
				HSSFCell cell1 = titleRow.createCell(0);
				HSSFCell cell2 = titleRow.createCell(1);
				HSSFCell cell3 = titleRow.createCell(2);
				cell1.setCellValue("房号");
				cell2.setCellValue("宿舍违纪评分（满７０分）");
				cell3.setCellValue("内务卫生检查评分（满３０分）");
				
				Map<String, List<Double[]>> map1 = map.get(fqNo);
				Iterator<String> it2 = map1.keySet().iterator();
				int rowNo = 1;
				while(it2.hasNext()){
					rowNo ++;
					String rooNo = it2.next();//房间号
					List<Double[]> ls = map1.get(rooNo);
					
					if(rooNo.indexOf(".") != -1){
						rooNo = rooNo.substring(0,rooNo.indexOf("."));
					}
					
					HSSFRow dataRow = sheet.createRow(rowNo);
					HSSFCell dataCell1 = dataRow.createCell(0);
					HSSFCell dataCell2 = dataRow.createCell(1);
					HSSFCell dataCell3 = dataRow.createCell(2);
					
					dataCell1.setCellType(HSSFCell.CELL_TYPE_STRING);// 定义单元格为字符串类型  
					dataCell2.setCellType(HSSFCell.CELL_TYPE_STRING);// 定义单元格为字符串类型  
					dataCell3.setCellType(HSSFCell.CELL_TYPE_STRING);// 定义单元格为字符串类型  
					
					dataCell1.setCellValue(rooNo);
					dataCell2.setCellValue(ls.get(0)[month].toString());
					dataCell3.setCellValue(ls.get(1)[month].toString());
				}
			}
		}catch(Exception e) {  
			e.printStackTrace(); 
		}
		return workbook;
    }
}
