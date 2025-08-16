package com.cy.framework.data.mybatis.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MybatisCustomHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
        this.strictInsertFill(metaObject, "updateTime", Date.class, new Date());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 检查是否在执行软删除操作（deleted 被设为 true）
        if (metaObject.hasSetter("deleted") &&
                metaObject.getValue("deleted") != null &&
                (Boolean) metaObject.getValue("deleted")) {
            this.strictUpdateFill(metaObject, "deleteTime", Date.class, new Date());
        }else{
            this.strictInsertFill(metaObject, "updateTime", Date.class, new Date());
        }
    }


}
