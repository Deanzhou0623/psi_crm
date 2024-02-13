package com.roadjava.psi.config;/*
 *ClassName:WebMvcConfig
 *Description: web mvc config
 *@Author:deanzhou
 *@Date:2024-01-15 21:11
 */


import com.roadjava.psi.interceptor.AuthorizationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Resource
    private AuthorizationProperties authorizationProperties;

    @Bean
    public AuthorizationInterceptor authorizationInterceptor() {
        return new AuthorizationInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 配置拦截器
        registry
                .addInterceptor(authorizationInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(authorizationProperties.getIgnoreHandlers());
    }

    /**
     * 跨域配置
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //设置允许跨域的路径
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedHeaders("*")
                .allowCredentials(true)
                // 请求方式
                .allowedMethods("*")
                .maxAge(3600);
    }
}
