package com.goodtime.highconcurrencytask.mapper;

import com.goodtime.highconcurrencytask.bean.PurchaseRecord;

/**
 * @author goodtime
 * @create 2020-03-27 5:25 下午
 */
public interface PurchaseRecordMapper {

    /**
     * 插入购买记录
     *
     * @param purchaseRecord 产品对象
     * @return 插入的行数
     */
    Integer insertPurchaseRecord(PurchaseRecord purchaseRecord);

}
