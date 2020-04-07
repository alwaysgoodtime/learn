package com.goodtime.springcloud.dao;

import com.goodtime.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author goodtime
 * @create 2020-04-07 11:10 下午
 */
//尽量用@Mapper注解（ibatis自带的），少用@Repository，可能会有问题
@Mapper
public interface PaymentDao {

    int create(Payment payment);//写操作

    Payment getPaymentById(@Param("id") Long id);//读操作

}
