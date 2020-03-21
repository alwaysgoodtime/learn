package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * 和事务相关的配置类
 * @author goodtime
 * @create 2020-03-21 11:24 上午
 */
public class TransactionConfig {


    //与事务相关的包，需要导spring-tx和spring-jdbc
    //用于创建事务管理器对象
    @Bean
    public PlatformTransactionManager platformTransactionManager(DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }
}
