package com.cy.framework.data.mybatis.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
public class BaseModel implements Serializable {

    @Id
    @Column(length = 100)
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    @Column(columnDefinition = "varchar(100) comment '更新用户'")
    private String updateUserId;
    @Column(columnDefinition = "datetime not null comment '创建时间'")
    @TableField(fill = FieldFill.INSERT,updateStrategy = FieldStrategy.NEVER)
    private Date createTime;
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    @Column(columnDefinition = "datetime not null comment '修改时间'")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    public Date getUpdateTime() {
        return updateTime;
    }

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
