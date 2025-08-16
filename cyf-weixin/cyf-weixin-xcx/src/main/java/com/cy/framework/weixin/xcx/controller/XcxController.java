package com.cy.framework.weixin.xcx.controller;

import com.cy.framework.common.dto.RestResultDTO;
import com.cy.framework.weixin.common.service.AppInfoService;
import com.cy.framework.weixin.common.support.dto.AppInfoSearchDTO;
import com.cy.framework.weixin.common.support.dto.WeiXinLoginDTO;
import com.cy.framework.weixin.common.support.enums.AppTypeEnum;
import com.cy.framework.weixin.common.support.vo.AppInfoVO;
import com.cy.framework.weixin.xcx.service.XcxWeiXinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = {"xcx"},method = {RequestMethod.GET,RequestMethod.POST})
public class XcxController {

    @Autowired
    private AppInfoService appInfoService;
    @Autowired
    private XcxWeiXinService service;


    /**
     * 登录
     * @param appId
     * @param code
     * @return
     */
    @RequestMapping(value = "login")
    public RestResultDTO<WeiXinLoginDTO> login(@RequestParam String appId,
                                               @RequestParam String code){
        AppInfoSearchDTO dto = new AppInfoSearchDTO();
        dto.setAppKey(appId);
        dto.setAppType(AppTypeEnum.XCX.getKey());
        List<AppInfoVO> ls = appInfoService.list(null,dto,null);
        Assert.notEmpty(ls,"未找到应用信息");
        WeiXinLoginDTO loginDTO = service.login(ls.get(0),code);
        Assert.notNull(loginDTO,"登录失败");
        return RestResultDTO.newSuccess(loginDTO,"登录成功");
    }

}
