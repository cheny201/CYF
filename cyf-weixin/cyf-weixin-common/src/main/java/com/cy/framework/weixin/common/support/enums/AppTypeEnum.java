package com.cy.framework.weixin.common.support.enums;

import lombok.Getter;

import java.util.Objects;

@Getter
public enum AppTypeEnum {

    XCX("xcx","小程序"),
    ;

    private String key;
    private String name;

    private AppTypeEnum(String key,String name){
        this.key = key;
        this.name = name;
    }

    public static AppTypeEnum getByKey(String key){
        for (AppTypeEnum v : AppTypeEnum.values()) {
            if(Objects.equals(v.getKey(),key)){
                return v;
            }
        }
        return null;
    }

}
