package com.goodtime.mapper;

import com.goodtime.domain.Account;
import com.goodtime.domain.AccountUser;
import com.goodtime.domain.QueryVo;
import com.goodtime.domain.User;
import org.apache.ibatis.annotations.Select;

import javax.management.Query;
import java.util.List;

/**
 * @author goodtime
 * @create 2020-03-11 2:34 下午
 */
public interface AccountMapper {

//    @Select("select * from account")
    List<Account> findAll();

    int saveAccount(Account account);

    void updateAccount(Account account);

    void deleteAccount(int a);

    //模糊查询
    List<Account> findByName(String name);

    int findTotal();

    List<Account> findAccountByVo(QueryVo queryVo);

    List<Account> findAccountByCondition(Account account);

    List<Account> findAccountByIds(QueryVo queryVo);

    List<User> findAccountAndUser();

    List<Account> findAllAcconut();

    List<Account>  findAccountById(int uid);

    List<User> findUserById(int id);

}
