package com.roadjava.psi.config;/*
 *ClassName:AuthorizationProperties
 *Description: 权限url配置
 *@Author:deanzhou
 *@Date:2024-01-15 21:05
 */

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "auth")
@Data
public class AuthorizationProperties {
    /**
     * 忽略权限拦截器的url
     */
    private List<String> ignoreHandlers;
}
