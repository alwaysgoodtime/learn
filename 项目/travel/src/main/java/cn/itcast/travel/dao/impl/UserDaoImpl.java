package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author goodtime
 * @create 2020-01-04 1:42 下午
 */
public class UserDaoImpl implements UserDao {

    JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public void addUser(User user) {
        String sql = "insert into tab_user(username,password,name,birthday,sex,telephone,email,status,code) values (?,?,?,?,?,?,?,?,?) ";
        jdbcTemplate.update(sql,user.getUsername(),user.getPassword(),user.getName(),user.getBirthday(),user.getSex(),user.getTelephone(),user.getEmail(),user.getStatus(),user.getCode());
    }

    @Override
    public boolean findByUsername(String username) {
        try {
            String sql = "select * from tab_user where username = ?";
            User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
        } catch (DataAccessException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean findByUsernameAndPassword(String username, String password) {
        String sql = "select * from tab_user where 1 = 1 ";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(sql);
        List list = new ArrayList();
        if(username != null && username.length() != 0){
            stringBuilder.append(" and username = ? ");
            list.add(username);
        }
        if(password != null && password.length() != 0){
            stringBuilder.append(" and password = ? ");
            list.add(password);
        }
        sql = stringBuilder.toString();

        try {
            User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), list.toArray());
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
        return  true;
    }

    @Override
    public boolean findStatusByUsername(String username) {
        String sql = "select status from tab_user where username = ?";
        String status = jdbcTemplate.queryForObject(sql,String.class, username);
        if("Y".equals(status)){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public String getUserByCode(String code) {
        String sql = "select username from tab_user where code = ?";
        String username = null;
        try {
            username = jdbcTemplate.queryForObject(sql, String.class, code);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return username;
    }

    public void updateStatusByUsername(String username){
        String sql = "update tab_user set status = 'Y' where username = ? ";
        jdbcTemplate.update(sql,username);
    }

    @Override
    public int findByUsernameReturnUid(String username) {
        String sql = "select uid from tab_user where username = ?";
        int aLong = jdbcTemplate.queryForObject(sql, Integer.class, username);
        return aLong;
    }


}
