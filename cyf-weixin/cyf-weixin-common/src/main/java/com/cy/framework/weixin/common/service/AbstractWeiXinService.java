package com.cy.framework.weixin.common.service;

import com.cy.framework.weixin.common.support.dto.TokenDTO;
import com.cy.framework.weixin.common.support.dto.WeiXinLoginDTO;
import com.cy.framework.weixin.common.support.vo.AppInfoVO;

public abstract class AbstractWeiXinService {

    /**
     * 获取token
     * @param appInfo
     * @return
     */
    public abstract TokenDTO getToken(AppInfoVO appInfo);

    /**
     * 登录
     * @param appInfo
     * @param code
     * @return
     */
    public abstract WeiXinLoginDTO login(AppInfoVO appInfo, String code);

}
