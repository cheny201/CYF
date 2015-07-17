package com.cy.project.template.system.voter;

import org.springframework.stereotype.Service;

import com.cy.cyf.framework.security.voter.InterfaceAccessDecisionVoter;
import com.cy.cyf.util.ValidateUtil;

@Service("interfaceAccessDecisionVoter")
public class InterfaceAccessDecisionVoterImpl extends InterfaceAccessDecisionVoter {

	@Override
	public boolean check(String requestUrl) {
		boolean flag = false;
		if(!ValidateUtil.isEmpty(requestUrl)){
			if(requestUrl.length()>10 && "webservice".equals(requestUrl.substring(1, 11))){
				flag = true;
			}else if(requestUrl.startsWith("/wechat/accept.do")){
				flag = true;
			}
		}
		return flag;
	}

}
