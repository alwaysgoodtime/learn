package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Seller;

/**
 * @author goodtime
 * @create 2020-01-06 3:54 下午
 */
public interface SellerDao {
    Seller findBySid(int sid);
}
