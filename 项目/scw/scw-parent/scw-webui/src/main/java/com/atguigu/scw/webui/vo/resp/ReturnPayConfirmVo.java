package com.atguigu.scw.webui.vo.resp;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author goodtime
 * @create 2020-02-27 5:21 下午
 */

/** 回报支付确认的vo，用户点击支持，客户端传数据，服务端把对应的回报内容进行返回 */
@Data
@ToString
public class ReturnPayConfirmVo implements Serializable {

    // 项目信息
    private Integer projectId;
    private String projectName;
    private String projectRemark;

    // 发起人信息
    private Integer memberId;
    private String memberName;

    // 回报的信息
    private Integer returnId;
    private String returnContent;
    private Integer num; // 支持数量，默认数量1，不能大于signalpurchase单笔限购数量
    private Integer price;// 支持单价
    private Integer freight; //运费
    private Integer signalpurchase;// 单笔限购数量
    private Integer purchase; //总限购数量

    // 所有的double和float的运算在任何情况下都会导致丢失精度，使用BigDecimal进行计算
    private BigDecimal totalPrice;// 总价 totalPrice =price* num+ freight
}

