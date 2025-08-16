package com.cy.framework.weixin.common.component;

import com.cy.framework.weixin.common.service.AbstractWeiXinService;
import com.cy.framework.weixin.common.support.constant.Constant;
import com.cy.framework.weixin.common.support.dto.TokenDTO;
import com.cy.framework.weixin.common.support.enums.AppTypeEnum;
import com.cy.framework.weixin.common.support.vo.AppInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class TokenComponent {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired(required = false)
    private AbstractWeiXinService xcxWeiXinService;

    public String getToken(AppInfoVO appInfo){
        String value = stringRedisTemplate.opsForValue().get(getRedisKey(appInfo));
        if(StringUtils.isNotBlank(value)){
            return value;
        }else{
            return refresh(appInfo);
        }
    }

    public String refresh(AppInfoVO appInfo){
        AppTypeEnum type = AppTypeEnum.getByKey(appInfo.getAppType());
        TokenDTO tokenDTO = null;
        switch (type){
            case XCX:
                tokenDTO = xcxWeiXinService.getToken(appInfo);
                break;
            default:
                break;
        }
        if(tokenDTO != null){
            stringRedisTemplate.opsForValue().set(getRedisKey(appInfo),tokenDTO.getToken(),tokenDTO.getExpireTime()-60, TimeUnit.SECONDS);
            return tokenDTO.getToken();
        }else{
            log.error("获取token失败，type={},appKey={}",appInfo.getAppType(),appInfo.getAppKey());
            return null;
        }
    }

    private static String getRedisKey(AppInfoVO appInfo){
        return Constant.REDIS_PREFIX + appInfo.getAppType()+":"+appInfo.getAppKey();
    }
}
