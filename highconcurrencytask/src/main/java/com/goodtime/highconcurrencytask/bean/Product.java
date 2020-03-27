package com.goodtime.highconcurrencytask.bean;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

/**
 * product实体类
 * @author goodtime
 * @create 2020-03-27 5:10 下午
 */
@Data
@Component
public class Product {

    private Integer productId;
    private String productName;
    private Integer stock;
    private BigDecimal price;
    private Integer version;
    private String note;
}
