package com.cy.framework.weixin.common.support.dto;

import com.cy.framework.common.dto.BaseDTO;
import lombok.Data;

@Data
public class AppInfoSearchDTO extends BaseDTO {

    private String name;
    private String appType;
    private String appKey;
}
