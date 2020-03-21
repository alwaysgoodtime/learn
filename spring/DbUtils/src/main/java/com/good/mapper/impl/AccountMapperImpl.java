package com.good.mapper.impl;

import com.good.domain.Account;
import com.good.mapper.AccountMapper;
import com.good.utils.ConnectionUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @author goodtime
 * @create 2020-03-13 1:05 下午
 */
@Repository
public class AccountMapperImpl implements AccountMapper {

    @Autowired
    //Autowired,基于注解进行依赖注入时，并不需要对应的set方法也可以完成，用的是类字段（成员变量）的反射注入，但是基于xml时，要么是set注入，要么就得是构造方法注入
    //它是多例的
    private QueryRunner queryRunner;

    @Autowired
    private ConnectionUtils connectionUtils;

//    public void setQueryRunner(QueryRunner queryRunner) {
//        this.queryRunner = queryRunner;
//    }

    public List<Account> findAll() {
        try {
            //因为我们现在不准备给queryRunner传送数据源，让它直接获取连接，所以要传个连接进去，保证service事务中的所有方法都是一个连接
            return queryRunner.query(connectionUtils.getThreadConnection(),"select * from account",new BeanListHandler<Account>(Account.class));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Account findById(Integer id) {
        try {
            return queryRunner.query(connectionUtils.getThreadConnection(),"select * from account where id = ?",new BeanHandler<Account>(Account.class),id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void addAccount(Account account) {
        try {
            queryRunner.update(connectionUtils.getThreadConnection(),"insert into account(id,name,money) values(?,?,?)",account.getId(),account.getName(),account.getMoney());
            System.out.println(Thread.currentThread().getName()+connectionUtils.getThreadConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateAccount(Account account) {
        try {
            queryRunner.update(connectionUtils.getThreadConnection(),"update account set name = ?,money = ? where id = ?",account.getName(),account.getMoney(),account.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteById(Integer id) {
        try {
            queryRunner.update(connectionUtils.getThreadConnection(),"delete from Account where id = ?",id);
            System.out.println(Thread.currentThread().getName()+connectionUtils.getThreadConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void setQueryRunner(QueryRunner queryRunner) {
        this.queryRunner = queryRunner;
    }
}
