package com.roadjava.psi.config;/*
 *ClassName:MybatisConfiguration
 *Description: mybatis configuration
 *@Author:deanzhou
 *@Date:2024-01-15 21:07
 */


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.Properties;

@Configuration
public class MybatisConfiguration {     
    @Resource
    private List<SqlSessionFactory> sqlSessionFactoryList;

    /**
     * 配置单表分页: mybatis plus的拦截器,不然selectPage不会加limit
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

    /**
     * 配置pageHelper分页:除了单表,也适用于一对一的多表join
     */
    @PostConstruct
    public void init() {
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties properties = new Properties();
        properties.put("helperDialect","mysql");
        properties.put("reasonable","true");
        properties.put("pageSizeZero","true");
        properties.put("params","pageNum=pageNow;count=executeCount");
        pageInterceptor.setProperties(properties);
        for (SqlSessionFactory sqlSessionFactory : sqlSessionFactoryList) {
            sqlSessionFactory.getConfiguration().addInterceptor(pageInterceptor);
        }
    }

}
