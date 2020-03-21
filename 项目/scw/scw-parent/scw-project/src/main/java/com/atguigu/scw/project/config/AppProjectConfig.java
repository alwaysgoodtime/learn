package com.atguigu.scw.project.config;

import com.atguigu.scw.project.component.OssTemplate;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author goodtime
 * @create 2020-02-25 3:32 上午
 */
@SpringBootConfiguration
public class AppProjectConfig {


    //通过配置文件的方式，注入application.properties中前缀为oss的值，并通过一个方法，把OssTemplate注册到了容器中
    //我们也可以在OssTemplate模板类上写@Component注解，然后每个属性头上写@Value+spEL表达式的方法来从application.properties
    //中取值
    @ConfigurationProperties(prefix = "oss")
    @Bean
    public OssTemplate ossTemplate() {
        return new OssTemplate();
    }
}
