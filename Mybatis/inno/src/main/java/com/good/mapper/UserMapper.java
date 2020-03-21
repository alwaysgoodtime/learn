package com.good.mapper;

import com.good.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @author goodtime
 * @create 2020-03-12 12:44 上午
 */
//开启二级缓存的最后一步，也就是整个UserMapper的方法都开启了二级缓存
//不过开启范围仅限此mapper，别的mapper默认还是false
@CacheNamespace(blocking = true)
public interface UserMapper {

    @Select("select * from user")
    @Results(id = "accountMap", value = {
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "uid",column= "uid"),
            @Result(property = "password",column = "password"),
//          user对account是一对一，所以是one，clomn是传的参数，传给accountmapper的findById方法，fetchType有三个选项，懒加载、迟加载、默认（两者选一个来用）。一对一一般都是Eager（相当于饿汉式，立即加载）
            @Result(property = "account",column = "uid",one =@One(select= "com.good.mapper.AccountMapper.findAccountByid",fetchType = FetchType.EAGER))
    })
    List<User> findAll();

    @Select("select * from user where uid = #{id}")
    List<User> findByUid(int id);

}
