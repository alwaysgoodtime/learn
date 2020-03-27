package com.atguigu.scw.webui.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author goodtime
 * @create 2020-02-26 9:20 下午
 */
//表示该文件是配置类，和@Configuration一样，这是SpringMvc配置类
@SpringBootConfiguration
public class AppWebmvcConfig implements WebMvcConfigurer {

    //相当于以前在springmvc配置文件中配拦截器
    //以后拦截器主要就在这里配置
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//
//    }
    //不需要配置静态资源的原因是：放在static下的静态资源默认已经放行，约定大于配置

    //如果这个方法只负责映射页面跳转，不负责其他业务逻辑，以前可以直接在springmvc.xml文件中
    //做此配置，现在没有配置文件，可以在配置类中进行配置
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");

    }
}
