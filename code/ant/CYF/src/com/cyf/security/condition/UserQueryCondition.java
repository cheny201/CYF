package com.cyf.security.condition;

 
/**
 * 
 * @author dingkaiqin
 * @since 2014-3-25
 * 
 */
public class UserQueryCondition {
	private String companyId;//所属机构
	private String centerId;//中支
	private String branchId;//支公司
	
	private String userCode;
	private String userName;
	
	public String getCenterId() {
		return centerId;
	}
	public void setCenterId(String centerId) {
		this.centerId = centerId.replace("null", "");
	}
	public String getBranchId() {
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId.replace("null", "");
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId.replace("null", "");
	}
	
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
