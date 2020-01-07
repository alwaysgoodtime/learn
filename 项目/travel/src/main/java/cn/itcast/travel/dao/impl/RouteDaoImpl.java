package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author goodtime
 * @create 2020-01-05 4:05 下午
 */
public class RouteDaoImpl implements RouteDao {

    JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List findRouteByCidAndRname(int cid, int currentPageReal, int pageCountReal, String rname) {
        StringBuilder stringBuilder = new StringBuilder();
        List arraylist = new ArrayList();
        String sql = "select * from tab_route where 1=1  ";
        stringBuilder.append(sql);
        if(cid != 0){
            stringBuilder.append(" and cid = ? ");
            arraylist.add(cid);
        }
        if(!rname.equals("ignoreme")){
            stringBuilder.append(" and rname like ? ");
            arraylist.add("%"+rname+"%");
        }
        arraylist.add((currentPageReal - 1) * pageCountReal);
        arraylist.add(pageCountReal);
        stringBuilder.append(" limit ?,? ");
        sql = stringBuilder.toString();
        List<Route> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Route>(Route.class),arraylist.toArray());
        return query;
    }

    @Override
    public int findAllRouteCountByCid(int cid) {
        String sql = "select count(*) from tab_route where cid = ?";
        Long aLong = jdbcTemplate.queryForObject(sql, Long.class, cid);
        return aLong.intValue();

    }

    @Override
    public int findAllRouteCountByRnameAndCid(String rname,int cid) {
        String sql = "select count(*) from tab_route where rname like ? and cid = ?";
        Long aLong = jdbcTemplate.queryForObject(sql, Long.class,"%"+rname+"%",cid);
        return  aLong.intValue();
    }

    @Override
    public Route findRouteByRid(int ridReal) {
        String sql = "select * from tab_route where rid = ?";
        Route route = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Route>(Route.class), ridReal);
        return route;
    }
}
