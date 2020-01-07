package cn.itcast.travel.service;

import cn.itcast.travel.domain.PageBean;

import java.util.List;

/**
 * @author goodtime
 * @create 2020-01-05 4:04 下午
 */
public interface RouteService {
    PageBean queryForCate(String cid, String currentPage, String pageCount, String rname);

    List queryForRoute(String rid, String username,String coll);
}
