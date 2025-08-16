package com.cy.framework.weixin.common.support.vo;

import com.cy.framework.common.dto.BaseVO;
import lombok.Data;

@Data
public class AppInfoVO extends BaseVO {

    private String name;
    private String appType;
    private String appKey;
    private String appSecret;
}
