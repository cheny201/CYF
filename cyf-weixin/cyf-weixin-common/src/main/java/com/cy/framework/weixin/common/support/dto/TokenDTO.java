package com.cy.framework.weixin.common.support.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenDTO {

    private String token;
    /**
     * 过期时间（秒）
     */
    private Long expireTime;

}
