package com.goodtime.service.impl;

import com.goodtime.mapper.UserMapper;
import com.goodtime.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * 用于创建对象的，和xml的bean标签一样
 * @Component value，用于指定bean的id，默认为当前类名的首字母小写
 * @Service 用在业务层 四个注解效果是一样的
 * @Repository 用在持久层 mapper层
 * @Controller 用在表现层
 *
 * 用于注入数据的，和bean标签中property是一样的
 *
 * @Autowired
 *如果没有对应的类（继承的类），就无法注入成功，如果有两个实现类，要看变量名称，变量名称如果和id
 * 匹配，会默认注入与id匹配的类，如果id与变量名称都不符合，就会报错，此时就可以用Qualifier。
 * 它可以在类匹配的基础上注入id
 * @Qualifer value 用于指定注入bean的id 与Autowired配合使用，不能独立使用
 *eg. Account account1 = null; account1就是变量名称
 *
 * @Resourece
 * 可独立使用,其中有name属性，指定bean的id，这是javax中的注解
 *
 *
 * @Value
 *     作用：用于注入基本的数据类型和String类型
 *     属性：value，用于指定数据的值，它可以使用spring中的el（也就是spel表达式）
 *     spel的写法：${表达式}
 *     mybatis有自己的el表达式，还有ognl表达式
 *     jsp有自己的el表达式
 *     spring有自己的el表达式，形式都是${},关键是看它们写在哪里，
 *     写在mybatis就是mybatis的表达式
 *     写在表现层、服务层、持久层就是spring的表达式

 *
 * 用于改变作用范围的，和bean标签中的scope是一样的
 *
 * 生命周期相关的，和bean标签中使用init-method是一样的
 *      PreDestory
 *          作用：用于指定销毁方法
 *      PostConsstruct
 *          作用：用于指定初始化方法
 *
 * @author goodtime
 * @create 2020-03-12 6:26 下午
 */

@Service(value = "aaa")
public class UserServiceImplAnno implements UserService {


    @Resource(name = "userMapperImpl")
    public UserMapper userMapper;


    @PostConstruct
    public void init(){
        System.out.println("初始化方法执行了");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("销毁方法执行了");
    }



    public UserServiceImplAnno() {

        System.out.println("我来啦");
    }

    public void findAll() {
        userMapper.findAll();
        System.out.println("666");
    }
}
