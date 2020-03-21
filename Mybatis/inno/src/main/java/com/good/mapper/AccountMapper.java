package com.good.mapper;

import com.good.domain.Account;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import javax.xml.bind.ValidationEvent;
import java.util.List;

/**
 * 在mybatis中，针对CRUD有四个注解
 * @Insert/@Update/@Delete/@Select
 * @author goodtime
 * @create 2020-03-12 12:44 上午
 */
public interface AccountMapper {


    @Select("select * from account")
    //如果实体类和数据库字段无法对应,（如果假设是主键无法对应，为accountid）
    //下面示范的是一对多的延迟加载
    @Results(id = "accountMap" ,value ={
//            @Result(id = true,column = "id" ,property = "accountid"),
            @Result(id = true,column = "id" ,property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "money",property = "money"),
            @Result(property = "user",column = "id",many=@Many(select = "com.good.mapper.UserMapper.findByUid",fetchType = FetchType.LAZY))
    }
    )
    List<Account> findAll();

    @Insert("insert into account(id,name,money) values (#{id},#{name},#{money})")
    void saveAccount(Account account);

    @Update("update account set name = #{name}, money = #{money} where id = #{id}")
    void updateAccount(Account account);

    @Delete("delete from account where id = #{id}")
    void deleteAccountById(Integer id);

    @Select("select * from account where id = #{id}")
    //多次配返回值的话，可以用resuletMap引用id即可
    @ResultMap(value = "accountMap")
    Account findAccountByid(Integer id);

    @Select("select * from account where name like #{name}")
//    @ResultMap(value = "accountMap") 如果别的返回值想返回accountMap的话，可以这么写
        // @Select("select * from account where name like '%${name}%'")
    List<Account> findAccountByName(String username);

    @Select("select count(*) from account")
    Integer CountAccount();
}
