package test.cy.cyf.util;

import java.util.List;

import com.cy.cyf.util.excel.XlsxReader;

public class Excel2007Test extends XlsxReader {

	@Override
	public void dealData(int sheetIndex, int curRow, List<String> rowlist)
			throws Exception {
		System.out.println(sheetIndex+"\t"+curRow +"\t" +rowlist);
	}
	
	public static void main(String[] args) throws Exception {
		Excel2007Test t = new Excel2007Test();
		t.setOptions(true, new int[]{9}, new int[]{0});
		t.process("C:/Users/ChenY201/Desktop/test.xlsx");
	}

}
