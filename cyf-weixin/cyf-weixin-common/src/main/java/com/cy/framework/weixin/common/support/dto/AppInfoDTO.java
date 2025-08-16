package com.cy.framework.weixin.common.support.dto;

import com.cy.framework.common.dto.BaseDTO;
import lombok.Data;

@Data
public class AppInfoDTO extends BaseDTO {

    private String name;
    private String appType;
    private String appKey;
    private String appSecret;

}
