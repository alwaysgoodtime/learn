package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.util.JDBCUtils;
import jdk.nashorn.internal.scripts.JD;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @author goodtime
 * @create 2020-01-05 10:17 上午
 */
public class CategoryDaoImpl implements CategoryDao {

    JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List findCategory() {
        String sql = "select * from tab_category";
        List<Category> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Category>(Category.class));
        return query;

    }
}
