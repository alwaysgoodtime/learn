package com.goodtime.springcloud.service;

import com.goodtime.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author goodtime
 * @create 2020-04-07 11:29 下午
 */
public interface PaymentService {

    int create(Payment payment);//写操作

    Payment getPaymentById(@Param("id") Long id);//读操作
}
