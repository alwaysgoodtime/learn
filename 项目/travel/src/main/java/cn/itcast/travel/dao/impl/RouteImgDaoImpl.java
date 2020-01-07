package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteImgDao;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

/**
 * @author goodtime
 * @create 2020-01-06 3:56 下午
 */
public class RouteImgDaoImpl implements RouteImgDao{

    JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List findImgByRid(int ridReal) {
        String sql = "select * from tab_route_img where rid = ?";
        List<RouteImg> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<RouteImg>(RouteImg.class), ridReal);
        return query;
    }
}
