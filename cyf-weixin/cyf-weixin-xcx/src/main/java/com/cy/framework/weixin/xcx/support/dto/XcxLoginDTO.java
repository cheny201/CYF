package com.cy.framework.weixin.xcx.support.dto;

import com.cy.framework.weixin.common.support.dto.WeiXinLoginDTO;
import lombok.Data;

@Data
public class XcxLoginDTO extends WeiXinLoginDTO {

    private String sessionKey;

}
