package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author goodtime
 * @create 2020-03-21 11:07 上午
 */
@Configuration
@ComponentScan({"mapper.impl","service.impl"})
//注意导入其他配置类的写法
@Import({jdbcConfig.class,TransactionConfig.class})
@PropertySource("jdbc.properties")
//开启对事务注解的支持
@EnableTransactionManagement
public class SpringConfiguration {
}
