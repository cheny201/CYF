package com.cy.framework.weixin.common.scheduler;

import com.cy.framework.weixin.common.component.TokenComponent;
import com.cy.framework.weixin.common.service.AppInfoService;
import com.cy.framework.weixin.common.support.vo.AppInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class TokenTask {

    @Autowired
    private AppInfoService appInfoService;
    @Autowired
    private TokenComponent tokenComponent;

    @Scheduled(fixedRate = 60000)
    public void execute(){
        List<AppInfoVO> list = appInfoService.list(null,null,null);
        long t = System.currentTimeMillis();
        log.error("刷新token........开始,数量:{}",list.size());
        for (AppInfoVO appInfoVO : list) {
            try {
                tokenComponent.refresh(appInfoVO);
            } catch (Exception e) {
                log.error("刷新token异常,ID:{}",appInfoVO.getId());
            }
        }
        log.error("刷新token........结束,耗时:{}ms",System.currentTimeMillis()-t);
    }

}
