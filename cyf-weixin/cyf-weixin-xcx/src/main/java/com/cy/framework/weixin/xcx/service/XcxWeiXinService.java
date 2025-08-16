package com.cy.framework.weixin.xcx.service;

import com.alibaba.fastjson.JSONObject;
import com.cy.framework.common.http.RestTemplateComponent;
import com.cy.framework.weixin.common.component.TokenComponent;
import com.cy.framework.weixin.common.service.AbstractWeiXinService;
import com.cy.framework.weixin.common.support.dto.TokenDTO;
import com.cy.framework.weixin.common.support.dto.WeiXinLoginDTO;
import com.cy.framework.weixin.common.support.vo.AppInfoVO;
import com.cy.framework.weixin.xcx.support.dto.XcxLoginDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Objects;

@Slf4j
@Service
public class XcxWeiXinService extends AbstractWeiXinService {

    @Value("${cyf.weixin.xcx.server:https://api.weixin.qq.com}")
    private String server;
    @Autowired
    private TokenComponent tokenComponent;
    @Autowired
    private RestTemplateComponent restTemplateComponent;

    @Override
    public TokenDTO getToken(AppInfoVO appInfo) {
        String url = server + "/cgi-bin/token?grant_type=client_credential&appid="+appInfo.getAppKey()+"&secret="+appInfo.getAppSecret();
        String response = restTemplateComponent.get(url,null,String.class,null);
        JSONObject json = checkResult(response,"获取token");
        if(json != null){
            String token = json.getString("access_token");
            Long time = json.getLong("expires_in");
            return new TokenDTO(token,time);
        }else{
            return null;
        }
    }

    @Override
    public WeiXinLoginDTO login(AppInfoVO appInfo, String code) {
        Assert.hasText(code,"code不能为空");

        String url = server + "/sns/jscode2session?appid="+appInfo.getAppKey()+"&secret="+appInfo.getAppSecret()+"&js_code="+code+"&grant_type=authorization_code";
        String response = restTemplateComponent.get(url,null,String.class,null);
        JSONObject json = checkResult(response,"登录");
        if(json != null){
            String openid = json.getString("openid");
            String sessionKey = json.getString("session_key");
            String unionid = json.getString("unionid");
            XcxLoginDTO dto = new XcxLoginDTO();
            dto.setOpenId(openid);
            dto.setUnionid(unionid);
            dto.setSessionKey(sessionKey);
            return dto;
        }else{
            return null;
        }
    }

    private JSONObject checkResult(String response,String apiName){
        JSONObject json = null;
        if(StringUtils.isNotBlank(response)){
            json = JSONObject.parseObject(response);
            Integer errcode = json.getInteger("errcode");
            if(errcode != null && !Objects.equals(errcode,0)){
                log.error("调用微信小程序接口异常：api={},resp={}",apiName,response);
                json = null;
            }
        }
        return json;
    }

}
