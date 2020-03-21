package com.atguigu.scw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement //开启事务
@EnableDiscoveryClient  //开启服务注册发现功能
@MapperScan("com.atguigu.scw.user.mapper")//扫描的mapper类所在的包
@SpringBootApplication
public class ScwUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScwUserApplication.class, args);
    }

}
