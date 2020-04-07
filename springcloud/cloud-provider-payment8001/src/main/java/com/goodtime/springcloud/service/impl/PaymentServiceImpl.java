package com.goodtime.springcloud.service.impl;

import com.goodtime.springcloud.dao.PaymentDao;
import com.goodtime.springcloud.entities.Payment;
import com.goodtime.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author goodtime
 * @create 2020-04-07 11:29 下午
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    //这个是java自带的注入功能，和spring自带的autowired实现的功能是一样的
    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
