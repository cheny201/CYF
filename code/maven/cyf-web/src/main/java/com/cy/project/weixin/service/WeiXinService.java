package com.cy.project.weixin.service;

import java.util.Map;

public interface WeiXinService {
	
	public String doReceiveMessage(Map<String, String> map);
	
	public String doReceiveEventPush(Map<String, String> map);
	

}
