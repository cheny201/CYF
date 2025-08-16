package com.cy.framework.${projectName}.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.framework.${projectName}.domain.${modelName};
import com.cy.framework.${projectName}.mapper.${modelName}Mapper;
import com.cy.framework.${projectName}.support.constant.Constant;
import com.cy.framework.${projectName}.support.dto.${modelName}DTO;
import com.cy.framework.${projectName}.support.dto.${modelName}SearchDTO;
import com.cy.framework.${projectName}.support.dto.base.DataStoreDTO;
import com.cy.framework.${projectName}.support.vo.${modelName}VO;
import com.cy.framework.${projectName}.util.PageUtils;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.List;

/**
 * @author ${author}
 * @time ${time}
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class ${modelName}Service extends ServiceImpl<${modelName}Mapper,${modelName}> {

    /**
     * 分页查询
     * @param userId 当前用户
     * @param dto 查询参数
     * @param pageable 分页信息
     * @return
     */
    @Transactional(readOnly = true)
    public DataStoreDTO<${modelName}VO> page(String userId, ${modelName}SearchDTO dto, Pageable pageable){
        Page<${modelName}> page = PageUtils.transferPage(pageable);
        IPage<${modelName}> result = this.page(page, buildQuery(dto));
        return new DataStoreDTO(result.getTotal(),transform(result.getRecords()));
    }

    /**
     * 列表查询
     * @param userId 当前用户
     * @param dto 查询参数
     * @param sort 排序
     * @return
     */
    @Transactional(readOnly = true)
    public List<${modelName}VO> list(String userId,${modelName}SearchDTO dto, Sort sort){
        QueryWrapper<${modelName}> queryWrapper = buildQuery(dto);
        PageUtils.transferSort(queryWrapper, sort);
        List<${modelName}> result = this.list(queryWrapper);
        return transform(result);
    }

    /**
     * 新增
     * @param userId 当前用户
     * @param dto 查询参数
     */
    public void save(String userId, ${modelName}DTO dto){
        checkParam(userId,dto);
        ${modelName} obj = transform(userId,dto);
        setDefaultValue(userId,obj);
        this.save(obj);
    }

    /**
     * 更新
     * @param userId 当前用户
     * @param dto 查询参数
     */
    public void update(String userId, ${modelName}DTO dto){
        checkParam(userId,dto);
        ${modelName} obj = transform(userId,dto);
        obj.setId(dto.getId());
        setDefaultValue(userId,obj);
        this.updateById(obj);
    }

    /**
     * 删除
     * @param userId
     * @param ids
     */
    public void delete(String userId, Collection<String> ids){
        if(CollectionUtils.isNotEmpty(ids)){
            this.removeByIds(ids);
        }
    }

    /**
     * 封装查询条件
     * @param dto 查询参数
     * @return
     */
    private QueryWrapper<${modelName}> buildQuery(${modelName}SearchDTO dto){
        QueryWrapper<${modelName}> queryWrapper = new QueryWrapper<>();
        return queryWrapper;
    }

    /**
     * 数据转换 DB -> VO
     * @param list
     * @return
     */
    private List<${modelName}VO> transform(List<${modelName}> list){
        List<${modelName}VO> voLs = Lists.newArrayList();
        for (${modelName} v : list) {
            ${modelName}VO vo = new ${modelName}VO();
            BeanUtils.copyProperties(v,vo);
            voLs.add(vo);
        }
        return voLs;
    }

    /**
     * 数据转换 DTO -> DB
     * @param userId 当前用户
     * @param dto
     * @return
     */
    private ${modelName} transform(String userId, ${modelName}DTO dto){
        ${modelName} obj = new ${modelName}();
        BeanUtils.copyProperties(dto,obj, Constant.DB_COMMON_FIELDS);
        return obj;
    }

    /**
     * 参数校验
     * @param userId 当前用户
     * @param dto
     */
    private void checkParam(String userId,${modelName}DTO dto){
        Assert.hasText(userId,"用户不能为空");
        Assert.notNull(dto,"参数不能为空");
    }

    /**
     * 设置默认值
     * @param userId 当前用户
     * @param obj
     */
    private void setDefaultValue(String userId,${modelName} obj){
        obj.setUpdateUserId(userId);
    }
}
