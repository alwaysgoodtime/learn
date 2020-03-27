package com.goodtime.highconcurrencytask.controller;

import com.goodtime.highconcurrencytask.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author goodtime
 * @create 2020-03-27 5:43 下午
 */
@RestController
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseProductService;

    @GetMapping("/")
    public ModelAndView indexPage() {
        ModelAndView index = new ModelAndView("index");
        return index;
    }

    //简单用mysql数据库的客观锁和悲观锁来解决库存超卖问题
    @PostMapping("/purchase")
    public void purchaseProduct(Integer userId, Integer productId, Integer quantity) {
        Boolean success = purchaseProductService.purchaseProduct(userId, productId, quantity);
        String messsge = success ? "抢购成功" : "抢购失败";
        System.out.println("产品抢购结果：" + messsge);
    }

    //演示用redis解决库存超卖问题，实际应用会更多，因为redis读取速度快
    @PostMapping("/purchaseRedis")
    public void purchaseProductRedis(Integer userId, Integer productId, Integer quantity) {
        Boolean success = purchaseProductService.purchaseProductRedisPlus2(userId, productId, quantity);
        String messsge = success ? "抢购成功" : "抢购失败";
        System.out.println("产品抢购结果：" + messsge);
    }



}

