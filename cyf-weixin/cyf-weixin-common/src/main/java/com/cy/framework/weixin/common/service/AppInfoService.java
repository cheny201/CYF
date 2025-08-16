package com.cy.framework.weixin.common.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.framework.common.constant.Constant;
import com.cy.framework.common.dto.DataStoreDTO;
import com.cy.framework.data.mybatis.util.PageUtils;
import com.cy.framework.weixin.common.domain.AppInfo;
import com.cy.framework.weixin.common.mapper.AppInfoMapper;
import com.cy.framework.weixin.common.support.dto.AppInfoDTO;
import com.cy.framework.weixin.common.support.dto.AppInfoSearchDTO;
import com.cy.framework.weixin.common.support.vo.AppInfoVO;
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

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class AppInfoService extends ServiceImpl<AppInfoMapper,AppInfo> {

    /**
     * 分页查询
     * @param userId 当前用户
     * @param dto 查询参数
     * @param pageable 分页信息
     * @return
     */
    @Transactional(readOnly = true)
    public DataStoreDTO<AppInfoVO> page(String userId, AppInfoSearchDTO dto, Pageable pageable){
        Page<AppInfo> page = PageUtils.transferPage(pageable);
        IPage<AppInfo> result = this.page(page, buildQuery(dto));
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
    public List<AppInfoVO> list(String userId, AppInfoSearchDTO dto, Sort sort){
        QueryWrapper<AppInfo> queryWrapper = buildQuery(dto);
        PageUtils.transferSort(queryWrapper, sort);
        List<AppInfo> result = this.list(queryWrapper);
        return transform(result);
    }

    /**
     * 新增
     * @param userId 当前用户
     * @param dto 查询参数
     */
    public void save(String userId, AppInfoDTO dto){
        checkParam(userId,dto);
        AppInfo obj = transform(userId,dto);
        setDefaultValue(userId,obj);
        this.save(obj);
    }

    /**
     * 更新
     * @param userId 当前用户
     * @param dto 查询参数
     */
    public void update(String userId, AppInfoDTO dto){
        checkParam(userId,dto);
        AppInfo obj = transform(userId,dto);
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
    private QueryWrapper<AppInfo> buildQuery(AppInfoSearchDTO dto){
        QueryWrapper<AppInfo> queryWrapper = new QueryWrapper<>();
        if(dto != null){
            queryWrapper.lambda()
                    .eq(StringUtils.isNotBlank(dto.getAppKey()),AppInfo::getAppKey, dto.getAppKey())
                    .eq(StringUtils.isNotBlank(dto.getAppType()),AppInfo::getAppType, dto.getAppType())
                    .like(StringUtils.isNotBlank(dto.getName()),AppInfo::getName, dto.getName())
            ;
        }
        return queryWrapper;
    }

    /**
     * 数据转换 DB -> VO
     * @param list
     * @return
     */
    private List<AppInfoVO> transform(List<AppInfo> list){
        List<AppInfoVO> voLs = Lists.newArrayList();
        for (AppInfo v : list) {
            AppInfoVO vo = new AppInfoVO();
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
    private AppInfo transform(String userId, AppInfoDTO dto){
        AppInfo obj = new AppInfo();
        BeanUtils.copyProperties(dto,obj, Constant.DB_COMMON_FIELDS);
        return obj;
    }

    /**
     * 参数校验
     * @param userId 当前用户
     * @param dto
     */
    private void checkParam(String userId,AppInfoDTO dto){
        Assert.hasText(userId,"用户不能为空");
        Assert.notNull(dto,"参数不能为空");
        Assert.hasText(dto.getName(),"名称不能为空");
        Assert.hasText(dto.getAppType(),"应用类型不能为空");
        Assert.hasText(dto.getAppKey(),"名应用Key不能为空");
        Assert.hasText(dto.getAppSecret(),"应用秘钥不能为空");
    }

    /**
     * 设置默认值
     * @param userId 当前用户
     * @param obj
     */
    private void setDefaultValue(String userId,AppInfo obj){
        obj.setUpdateUserId(userId);
    }

}
