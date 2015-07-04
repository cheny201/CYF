package com.cy.project.template.system.voter;

import org.springframework.stereotype.Service;

import com.cy.cyf.framework.security.pojo.User;
import com.cy.cyf.framework.security.voter.URLAccessDecisionVoter;

@Service("urlAccessDecisionVoter")
public class URLAccessDecisionVoterImpl extends URLAccessDecisionVoter{

	@Override
	public boolean hasResource(String requestUrl, User user) {
		// TODO Auto-generated method stub
		return true;
	}

}
