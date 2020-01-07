package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author goodtime
 * @create 2020-01-06 3:55 下午
 */
public class FavoriteDaoImpl implements FavoriteDao {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public boolean findByRidAndUid(int ridReal, int uid) {
        try {
            String sql = "select * from tab_favorite where rid = ? and uid = ?";
            Favorite favorite = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Favorite>(Favorite.class), ridReal, uid);
        } catch (DataAccessException e) {
            return false;
        }
        return true;
    }

    @Override
    public int findCountByRid(int rid) {
        String sql = "select count(*) from tab_favorite where rid = ?";
        Integer integer = jdbcTemplate.queryForObject(sql, Integer.class, rid);
        return integer;

    }

    @Override
    public void addByRidAndUid(int ridReal, int uid) {
        String sql = "insert into tab_favorite values( ? , ? , ?)";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(new Date());
        jdbcTemplate.update(sql,ridReal,format,uid);
    }
}
