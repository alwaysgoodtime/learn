package config;

import com.mchange.v2.c3p0.DriverManagerDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

/**
 * 连接数据库相关的配置类
 * @author goodtime
 * @create 2020-03-21 11:09 上午
 */
public class jdbcConfig {


    @Value("${user}")
    String user;

    @Value("${password}")
    String password;

    @Value("${url}")
    String url;

    @Value("${driver}")
    String driver;


    /**
     * 创建queryrunner
     * @param dataSource
     * @return
     */

    @Bean("queryRunner")
    public QueryRunner createQueryRunner(DataSource dataSource){
        return  new QueryRunner(dataSource);
    }

    /**
     * 创建数据源对象
     * @return
     */
    @Bean("datasource")
    public DataSource createDataSource(){
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setJdbcUrl(url);
        ds.setUser(user);
        ds.setPassword(password);
        ds.setDriverClass(driver);
        return ds;
    }
}
