package com.goodtime.highconcurrencytask.service;

/**
 * @author goodtime
 * @create 2020-03-27 5:38 下午
 */
public interface PurchaseService {
        /**
         * 抢购产品
         * @param userId 用户编码
         * @param productId 产品编码
         * @param quantity  抢购数量
         * @return 是否抢购成功
         */
        Boolean purchaseProduct(Integer userId, Integer productId, Integer quantity);

        Boolean purchaseProductRedis(Integer userId, Integer productId, Integer quantity);

        Boolean purchaseProductRedisPlus(Integer userId, Integer productId, Integer quantity);

        Boolean purchaseProductRedisPlus2(Integer userId, Integer productId, Integer quantity);
}
