package com.goodtime.springcloud.controller;

import com.goodtime.springcloud.entities.CommonResult;
import com.goodtime.springcloud.entities.Payment;
import com.goodtime.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author goodtime
 * @create 2020-04-07 11:33 下午
 */
@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @PostMapping("/payment/create")
    //和requestMapping一样，但是因为是rest风格，这是写操作，所以用postmapping
    public CommonResult create(Payment payment){
        int result = paymentService.create(payment);
        log.info("插入结果为：{}",result);
        int i = 1;
        if(result > 0){
            return new CommonResult(200,"插入数据库成功",result);
        }else {
            return new CommonResult(444,"插入数据库失败",null);
        }
    }

    @GetMapping("/payment/get/{id}")
    //和requestMapping一样，但是因为是rest风格，这是写操作，所以用postmapping
    public CommonResult getPaymentByID(@PathVariable("id") Long id){
        Payment result = paymentService.getPaymentById(id);
        log.info("查询结果为：{}",result);
        if(result != null){
            return new CommonResult(200,"查询成功",result);
        }else {
            return new CommonResult(444,"没有对应记录",result);
        }
    }

}
