package com.cy.framework.common.dto;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataStoreDTO<T> {

    /**
     * 记录总数
     */
    private Long total = 0L;
    /**
     * 当前页记录
     */
    private List<T> rows = Lists.newArrayList();
}
