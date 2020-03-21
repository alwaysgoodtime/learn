package com.atguigu.scw.webui.vo.resp;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 接收用户下单提交的数据
 * @author goodtime
 * @create 2020-02-28 1:41 上午
 */

@Data
@ToString
public class OrderFormInfoSubmitVo implements Serializable {

    private String address;//收货地址id
    private String invoice;//0代表不要  1-代表要
    private String invoictitle;//发票抬头
    private String remark;//订单的备注

}
