package test.office.excel;

public class DateModel {
	
	private String year;
	private String month;
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj == null){
			return false;
		}
		if(obj instanceof DateModel){
			DateModel tmp = (DateModel)obj;
			String str1 = this.year+this.month+"";
			String str2 = tmp.getYear()+tmp.getMonth()+"";
			return str1.equals(str2);
		}
		return false;
	}
	
	

}
