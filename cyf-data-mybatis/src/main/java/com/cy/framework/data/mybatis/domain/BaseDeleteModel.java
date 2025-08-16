package com.cy.framework.data.mybatis.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@Data
@MappedSuperclass
public class BaseDeleteModel extends BaseModel {

    @Column(columnDefinition = "bit not null default 0 comment '是否已删除'")
    @TableLogic
    private Boolean deleted = false;
    @Column(columnDefinition = "varchar(100) comment '删除用户'")
    private String deleteUserId;
    @Column(columnDefinition = "datetime comment '删除时间'")
    @TableField(fill = FieldFill.INSERT_UPDATE,updateStrategy = FieldStrategy.NEVER)
    private Date deleteTime;

    public Date getDeleteTime() {
        return deleteTime;
    }

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }
}
