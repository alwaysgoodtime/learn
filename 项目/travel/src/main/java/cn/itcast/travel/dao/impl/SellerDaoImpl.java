package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.SellerDao;
import cn.itcast.travel.domain.Seller;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author goodtime
 * @create 2020-01-06 3:56 下午
 */
public class SellerDaoImpl implements SellerDao{
    JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public Seller findBySid(int sid) {
        String sql = "select * from tab_seller where sid = ?";
        Seller seller = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Seller>(Seller.class), sid);
        return seller;
    }
}
