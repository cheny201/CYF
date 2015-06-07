package com.cyf.util.mail;

/**
 * 内嵌资源
 * @author ChenY201
 * @version 1.0
 * @created 2014-11-22 下午1:23:47
 */
public class InlineAttachmentInfo extends AttachmentInfo{

	//内嵌资源id
	private String cid;

	/**
	 * 获取内嵌资源id
	 * @return
	 */
	public String getCid() {
		return cid;
	}

	/**
	 * 设置内嵌资源id
	 * @param cid
	 */
	public void setCid(String cid) {
		this.cid = cid;
	}
}
