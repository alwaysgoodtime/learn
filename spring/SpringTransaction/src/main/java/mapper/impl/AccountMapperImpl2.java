package mapper.impl;

import bean.Account;
import mapper.AccountMapper;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 *和speing事务整合的时候，用jdbctemplate可以，但是用dbutils不可以，可能是需要额外的配置
 * @author goodtime
 * @create 2020-03-13 1:05 下午
 */
@Repository
public class AccountMapperImpl2 implements AccountMapper {

    @Autowired
    //Autowired,基于注解进行依赖注入时，并不需要对应的set方法也可以完成，用的是类字段（成员变量）的反射注入，但是基于xml时，要么是set注入，要么就得是构造方法注入
    //它是多例的
    private JdbcTemplate jdbcTemplate;


//    public void setQueryRunner(QueryRunner queryRunner) {
//        this.queryRunner = queryRunner;
//    }

    public List<Account> findAll() {
            //因为我们现在不准备给queryRunner传送数据源，让它直接获取连接，所以要传个连接进去，保证service事务中的所有方法都是一个连接
            return jdbcTemplate.query("select * from account",new BeanPropertyRowMapper<Account>(Account.class));
    }

    public Account findById(Integer id) {
        List<Account> query = jdbcTemplate.query("select * from account where id = ?", new BeanPropertyRowMapper<Account>(Account.class), id);
        if(query.size() == 0){
            return null;
        }
        if(query.size() > 1){
             return null;//结果集不唯一
        }
        return query.get(0);
    }

    public void addAccount(Account account) {
        jdbcTemplate.update("insert into account(id,name,money) values(?,?,?)", account.getId(), account.getName(), account.getMoney());

    }

    public void updateAccount(Account account) {
        int i = 1 / 0;
        jdbcTemplate.update("update account set name = ?,money = ? where id = ?", account.getName(), account.getMoney(), account.getId());
    }

    public void deleteById(Integer id) {
        jdbcTemplate.update("delete from Account where id = ?", id);
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
