package config;

import com.good.utils.ConnectionUtils;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * 用配置类，取到bean.xml文件
 * 该类是配置类，用bean.xml作用一样
 * Configuration   作用：指定当前类是一个配置类
 * ComponentScan 作用：用于通过注解，指定创建bean工厂时要扫描的包  其中的属性value和basepackage，是一样的
 * ComponentScans 里面可以写多个ComponentScan
 *
 * import,用于导入其他的配置类
 *      属性：value 用于指定其他配置的字节码
 *      有此注解的为主配置类，其他注解为副配置类
 *
 * propertySource 导入properties的配置文件，地址默认是类路径，也可以直接写出classpath（其他注解在用类路径时也可以这样）
 * 表示为类路径下的地址
 *
 * @author goodtime
 * @create 2020-03-13 2:21 下午
 */

//如果是主配置，也就是AnnotationConfigApplicationContext加载的类，可以不用此注解。
//但如果是被加载进主配置的其他配置，就得写
@Configuration
@PropertySource("jdbc.properties")
@Import(JdbcConfig.class)
//注解要扫描的包
@ComponentScan(value = {"com.good.mapper.impl","com.good.factory.beanfactory","com.good.service.impl","com.good.utils","config"})
public class SpringConfiguration {

    /**
     * @Bean注解作用，用于把当前注解的返回值作为bean对象，存入Spring容器中
     *      属性：name 用于指定bean的id 默认值为当前方法的名称
     *
     * @return
     *
     * 细节：让我们使用注解配置类时，如果方法有参数(比如这里的dataSource)，spring会去容器中查找有无可用的参数
     * 有的话，就会自动注入，查找的方式和Autowired注解是一样的
     */

//    @Bean(name="runner")
//    @Scope(value = "prototype")
//    //Scope注解，配置需要为多例的类，默认为单例
//    public QueryRunner createQueryRunner(DataSource dataSource) {
//        return new QueryRunner(dataSource);
//    }


    @Bean(name="runner")
    @Scope(value = "prototype")
    //Scope注解，配置需要为多例的类，默认为单例，此时没有注入数据源，是为了事务控制，让ConnectionUtils接管连接管理
    public QueryRunner createQueryRunner() {
        return new QueryRunner();
    }

}
