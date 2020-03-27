package com.goodtime.highconcurrencytask.mapper;

import com.goodtime.highconcurrencytask.bean.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author goodtime
 * @create 2020-03-27 5:07 下午
 */
public interface ProductMapper {

    /**
     * 获取产品
     *
     * @param productId 编号
     * @return 产品对象
     */
    Product getProduct(Integer productId);

    /**
     * 减少产品库存
     *
     * @param productId 产品编号
     * @param quantity  产品购买数量
     * @return 更新的行数
     */
    Integer decreaseProduct(@Param("productId") Integer productId, @Param("quantity") Integer
            quantity);

    /**
     * 获取产品，查询的时候，加行锁，改为当前读
     *
     * @param productId 编号
     * @return 产品对象
     */
    Product getProductPlus(Integer productId);

    Integer decreaseProductPlus(@Param("productId") Integer productId,@Param("quantity")Integer quantity, @Param("version")Integer version);
}

