package com.cy.framework.data.mybatis.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Iterator;
import java.util.Objects;
import java.util.regex.Pattern;

public class PageUtils {
    private static ApplicationContext applicationContext;
    private static final Pattern ORDER_PROPERTY_REGEX = Pattern.compile("^[a-zA-Z0-9._,]+$");

    public PageUtils() {
    }

    public static void setApplicationContext(ApplicationContext applicationContext) {
        PageUtils.applicationContext = applicationContext;
    }

    public static <T> Page<T> transferPage(Pageable pageable) {
        if (Objects.isNull(pageable)) {
            return new Page(1L, 10L);
        } else {
            int pageNumber = pageable.getPageNumber();
            int pageSize = pageable.getPageSize();
            Page page = new Page((long)(pageNumber + 1), (long)pageSize);
            Sort sort = pageable.getSort();
            if (sort.isUnsorted()) {
                return page;
            } else {
                SqlSessionFactory sqlSessionFactory = (SqlSessionFactory)applicationContext.getBean(SqlSessionFactory.class);
                Configuration configuration = sqlSessionFactory.getConfiguration();
                boolean underCamel = configuration.isMapUnderscoreToCamelCase();
                Iterator var8 = pageable.getSort().iterator();

                while(var8.hasNext()) {
                    Sort.Order order = (Sort.Order)var8.next();
                    String property = order.getProperty();
                    boolean ascending = order.isAscending();
                    Assert.isTrue(ORDER_PROPERTY_REGEX.matcher(property).matches(), "非法的排序字段", new Object[0]);
                    page.addOrder(new OrderItem[]{new OrderItem(underCamel ? StringUtils.camelToUnderline(property) : property, ascending)});
                }

                return page;
            }
        }
    }

    public static <T> void transferSort(QueryWrapper queryWrapper, Sort sort) {
        if (!Objects.isNull(queryWrapper) && !Objects.isNull(sort) && !sort.isUnsorted()) {
            SqlSessionFactory sqlSessionFactory = (SqlSessionFactory)applicationContext.getBean(SqlSessionFactory.class);
            Configuration configuration = sqlSessionFactory.getConfiguration();
            boolean underCamel = configuration.isMapUnderscoreToCamelCase();
            Iterator var5 = sort.iterator();

            while(var5.hasNext()) {
                Sort.Order order = (Sort.Order)var5.next();
                String property = order.getProperty();
                boolean ascending = order.isAscending();
                Assert.isTrue(ORDER_PROPERTY_REGEX.matcher(property).matches(), "非法的排序字段", new Object[0]);
                if (ascending) {
                    queryWrapper.orderByAsc(underCamel ? StringUtils.camelToUnderline(property) : property);
                } else {
                    queryWrapper.orderByDesc(underCamel ? StringUtils.camelToUnderline(property) : property);
                }
            }

        }
    }

    public static <T> Page<T> transferSort(Sort sort) {
        Page page = new Page(1L, -1L);
        if (!Objects.isNull(sort) && !sort.isUnsorted()) {
            SqlSessionFactory sqlSessionFactory = (SqlSessionFactory)applicationContext.getBean(SqlSessionFactory.class);
            Configuration configuration = sqlSessionFactory.getConfiguration();
            boolean underCamel = configuration.isMapUnderscoreToCamelCase();
            Iterator var5 = sort.iterator();

            while(var5.hasNext()) {
                Sort.Order order = (Sort.Order)var5.next();
                String property = order.getProperty();
                boolean ascending = order.isAscending();
                Assert.isTrue(ORDER_PROPERTY_REGEX.matcher(property).matches(), "非法的排序字段", new Object[0]);
                page.addOrder(new OrderItem[]{new OrderItem(underCamel ? StringUtils.camelToUnderline(property) : property, ascending)});
            }

            return page;
        } else {
            return page;
        }
    }
}
