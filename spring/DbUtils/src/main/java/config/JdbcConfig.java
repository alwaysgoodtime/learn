package config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * @author goodtime
 * @create 2020-03-13 2:45 下午
 */

//副配置类，用于注册数据源对象的配置类,此时configuration注解就不能省略了，因为主配置要扫描
//这个包，不写这个注解的话，就无法识别它是配置类了，
// 1.当然，可以在AnnotationConfigApplicationContext中扫描时,扫描整个config包，或者把它的配置类和主配置类的字节码类一起写进去
// 2.可以在主配置中写import，把这个配置类导入进去，这样也可以不写这个注解
@Configuration
public class JdbcConfig {


    //用spel表达式，从jdbc.properties文件中取到对应的值，然后注入进来
    //当然，也先将jdbc的配置文件导入进来，这里是在主配置文件里用PropertiesSource导入
    @Value("${user}")
    String user;
    @Value("${password}")
    String password;
    @Value("${url}")
    String url;
    @Value("${driver}")
    String driver;


    /**
     * 创建数据源对象
     * @return
     */
//    @Bean(name="datasource")
//    public DataSource createDataSource() {
//
//        ComboPooledDataSource source = null;
//        try {
//            source = new ComboPooledDataSource();
//            source.setUser("goodtime");
//            source.setPassword("haojianyu");
//            source.setJdbcUrl("jdbc:mysql://localhost:3306/ssm");
//            source.setDriverClass("com.mysql.jdbc.Driver");
//        } catch (PropertyVetoException e) {
//            e.printStackTrace();
//        }
//        return source;
//    }
//
    //配置数据源，但是抽取出配置文件，后续方便管理
    @Bean(name="datasource")
    public DataSource createDataSource() {

        ComboPooledDataSource source = null;
        try {
            source = new ComboPooledDataSource();
            source.setUser(user);
            source.setPassword(password);
            source.setJdbcUrl(url);
            source.setDriverClass(driver);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return source;
    }



}
