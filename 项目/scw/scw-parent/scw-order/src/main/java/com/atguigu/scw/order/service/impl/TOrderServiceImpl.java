package com.atguigu.scw.order.service.impl;

import com.atguigu.scw.enums.OrderStatusEnumes;
import com.atguigu.scw.order.bean.TOrder;
import com.atguigu.scw.order.bean.TReturn;
import com.atguigu.scw.order.mapper.TOrderMapper;
import com.atguigu.scw.order.service.TOrderService;
import com.atguigu.scw.order.service.TProjectServiceFeign;
import com.atguigu.scw.order.vo.OrderInfoSubmitVo;
import com.atguigu.scw.utils.AppDateUtils;
import com.atguigu.scw.vo.resp.AppResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * @author goodtime
 * @create 2020-02-28 1:15 下午
 */
@Service
@Transactional
public class TOrderServiceImpl implements TOrderService {

    @Autowired
    TOrderMapper tOrderMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    TProjectServiceFeign tProjectServiceFeign;

    @Override
    public TOrder saveOrder(OrderInfoSubmitVo order) {


        TOrder tOrder = new TOrder();

        try {
        BeanUtils.copyProperties(order,tOrder);

        String accessToken = order.getAccessToken();

        String memberId = stringRedisTemplate.opsForValue().get(accessToken);

        tOrder.setMemberid(Integer.parseInt(memberId));

        //Ordernum为订单自己的订单号，实际生产中可能保存时间、用户id、地域等信息。
        tOrder.setOrdernum(UUID.randomUUID().toString().replaceAll("-",""));

        //调用远程服务，总金额不能从前端redis的session中取，一方面是，后端不要动前端的redis，而且，数据库中订单的回报信息
        //可能在用户下单时候有变化，所以需要重新从数据库订单表中取
        AppResponse<TReturn> resp = tProjectServiceFeign.returnInfo(order.getReturnid());

        TReturn tReturn = resp.getData();

        Integer orderMoney = tReturn.getFreight() + order.getRtncount()*tReturn.getSupportmoney();

        tOrder.setMoney(orderMoney);//订单总金额


        tOrder.setCreatedate(AppDateUtils.getFormatTime());


        //设置订单状态为未支付
        tOrder.setStatus(OrderStatusEnumes.UNPAY.getCode()+"");

        tOrderMapper.insertSelective(tOrder);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("数据库保存订单信息失败");
        }

        return tOrder;
    }
}
