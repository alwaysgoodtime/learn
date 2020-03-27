package com.goodtime.highconcurrencytask.bean;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author goodtime
 * @create 2020-03-27 5:26 下午
 */
@Data
@Component
public class PurchaseRecord {

    private Integer userId;
    private Integer productId;
    private BigDecimal price;
    private Integer quantity;
    private BigDecimal totalPrice;
    private Date purchaseDate;
    private String note;
}
