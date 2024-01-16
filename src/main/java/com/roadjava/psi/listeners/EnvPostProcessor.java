package com.roadjava.psi.listeners;/*
 *ClassName:EnvPostProcessor
 *Description: 加载可能发送变化的自定义配置,通过在spring.factories中配置达到在spring启动之前
 就填充到environment中的目的,达到变量文件的抽离
 *@Author:deanzhou
 *@Date:2024-01-15 21:32
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class EnvPostProcessor implements EnvironmentPostProcessor {
    private final YamlPropertySourceLoader yamlPropertySourceLoader = new YamlPropertySourceLoader();
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        Resource resource = new ClassPathResource("config.yml");
        PropertySource<?> propertySource = loadProfiles(resource);
        environment.getPropertySources().addLast(propertySource);
        System.out.println(1);
    }
    //加载单个配置文件
    private PropertySource<?> loadProfiles(Resource resource) {
        if (!resource.exists()) {
            throw new IllegalArgumentException("资源" + resource + "不存在");
        }
        try {
            return yamlPropertySourceLoader.load("custom-config",resource).get(0);
        }catch (IOException ex) {
            throw new IllegalStateException("加载配置文件失败" + resource, ex);
        }
    }
}
