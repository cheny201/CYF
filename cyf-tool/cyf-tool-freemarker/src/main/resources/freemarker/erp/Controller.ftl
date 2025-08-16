package com.cy.framework.${projectName}.controller;

import com.cy.framework.${projectName}.service.${modelName}Service;
import com.cy.framework.${projectName}.support.dto.${modelName}DTO;
import com.cy.framework.${projectName}.support.dto.${modelName}SearchDTO;
import com.cy.framework.${projectName}.support.dto.base.DataStoreDTO;
import com.cy.framework.${projectName}.support.dto.base.RestResultDTO;
import com.cy.framework.${projectName}.support.vo.${modelName}VO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * @author ${author}
 * @time ${time}
 */
@CrossOrigin
@RestController
@RequestMapping(value = {"${firstSmallModelName}"},method = {RequestMethod.GET,RequestMethod.POST})
public class ${modelName}Controller {

    @Autowired
    private ${modelName}Service service;

    /**
     * 分页查询
     * @param pageable
     * @param userId
     * @param query
     * @return
     */
    @RequestMapping(value = "page")
    public RestResultDTO<DataStoreDTO<${modelName}VO>> page(@PageableDefault(sort = "createTime", direction = Sort.Direction.DESC) Pageable pageable,
                                                       @RequestParam String userId,
                                                       ${modelName}SearchDTO query){
        return RestResultDTO.newSuccess(service.page(userId,query,pageable),"查询成功");
    }

    /**
     * 列表
     * @param sort
     * @param userId
     * @param query
     * @return
     */
    @RequestMapping(value = "list")
    public RestResultDTO<List<${modelName}VO>> list(@SortDefault(sort = "createTime", direction = Sort.Direction.DESC) Sort sort,
                                               @RequestParam String userId,
                                               ${modelName}SearchDTO query){
        return RestResultDTO.newSuccess(service.list(userId,query,sort),"查询成功");
    }

    /**
     * 新增
     * @param userId
     * @param dto
     * @return
     */
    @PostMapping(value = "save")
    public RestResultDTO<Void> save(@RequestParam String userId, @RequestBody ${modelName}DTO dto){
        service.save(userId,dto);
        return RestResultDTO.newSuccess();
    }

    /**
     * 更新
     * @param userId
     * @param dto
     * @return
     */
    @PostMapping(value = "update")
    public RestResultDTO<Void> update(@RequestParam String userId, @RequestBody ${modelName}DTO dto){
        service.update(userId,dto);
        return RestResultDTO.newSuccess();
    }

    /**
     * 删除
     * @param userId
     * @param ids
     * @return
     */
    @RequestMapping(value = "delete")
    public RestResultDTO<Void> delete(@RequestParam String userId, @RequestParam Set<String> ids){
        service.delete(userId,ids);
        return RestResultDTO.newSuccess();
    }

}
