package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Route;
import com.sun.org.apache.xpath.internal.operations.Lt;

import java.util.List;

/**
 * @author goodtime
 * @create 2020-01-05 4:05 下午
 */
public interface RouteDao {
    List findRouteByCidAndRname(int cid, int currentPageReal, int pageCountReal, String rname);

    int findAllRouteCountByCid(int cid);

    int findAllRouteCountByRnameAndCid(String rname,int cid);

    Route findRouteByRid(int ridReal);
}
