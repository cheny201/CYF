package com.cy.framework.weixin.common.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cy.framework.data.mybatis.domain.BaseModel;
import com.cy.framework.weixin.common.support.constant.Constant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity(name = AppInfo.TABLE_NAME)
@Table(appliesTo =AppInfo.TABLE_NAME, comment = "应用信息")
@TableName(value = AppInfo.TABLE_NAME)
@javax.persistence.Table(name = AppInfo.TABLE_NAME,indexes = {
        @Index(name = "idx_name",columnList = "name"),
})
public class AppInfo extends BaseModel {
    public final static String TABLE_NAME = Constant.TABLE_PREFIX+"app_info";

    @Column(columnDefinition = "varchar(100) not null COMMENT '名称'")
    private String name;

    /**
     * @see AppTypeEnum
     */
    @Column(columnDefinition = "varchar(100) not null COMMENT '应用类型'")
    private String appType;
    @Column(columnDefinition = "varchar(100) not null COMMENT '应用Key'")
    private String appKey;
    @Column(columnDefinition = "varchar(200) not null COMMENT '应用秘钥'")
    private String appSecret;
}
