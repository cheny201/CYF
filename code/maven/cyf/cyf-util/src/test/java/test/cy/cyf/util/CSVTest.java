package test.cy.cyf.util;

import java.util.List;

import com.cy.cyf.util.excel.CSVRead;

public class CSVTest extends CSVRead {

	
	public static void main(String[] args) throws Exception {
		CSVTest c = new CSVTest();
		c.titleRow=-1;
		c.process("C:/Users/ChenY201/Desktop/test.csv","gbk");
	}

	@Override
	public void dealData(int currentRow, List<String> list) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(currentRow +"\t" +list);
	}

}
