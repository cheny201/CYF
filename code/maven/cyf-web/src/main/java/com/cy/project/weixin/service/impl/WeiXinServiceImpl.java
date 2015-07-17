package com.cy.project.weixin.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.cy.cyf.log.CYFLog;
import com.cy.project.weixin.service.WeiXinService;

@Service("weiXinService")
public class WeiXinServiceImpl implements WeiXinService {

	@Override
	public String doReceiveMessage(Map<String, String> map) {
		CYFLog.debug(map.toString());
		return "ok";
	}

	@Override
	public String doReceiveEventPush(Map<String, String> map) {
		CYFLog.debug(map.toString());
		return "ok";
	}

}
