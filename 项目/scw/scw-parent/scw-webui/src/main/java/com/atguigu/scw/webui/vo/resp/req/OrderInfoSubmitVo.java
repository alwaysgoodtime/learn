package com.atguigu.scw.webui.vo.resp.req;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 用于保存用户下单提交的数据，发送到远程订单服务那里
 * @author goodtime
 * @create 2020-02-28 1:35 上午
 */
@Data
@ToString
public class OrderInfoSubmitVo implements Serializable {

    private String accessToken;//会员的访问token值
    private Integer projectid;//项目id
    private Integer returnid;//第几种回报
    private Integer rtncount;//此单要回报给用户的商品数

    private String address;//下单会员的地址
    private String invoice;//是否开发票，0不开，1开
    private String invoictitle;//发票抬头
    private String remark;//备注

}
