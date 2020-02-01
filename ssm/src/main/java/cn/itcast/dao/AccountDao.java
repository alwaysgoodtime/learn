package cn.itcast.dao;

import cn.itcast.domain.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author goodtime
 * @create 2020-02-01 2:27 下午
 */

@Repository
public interface AccountDao {

    @Select("select * from account")
    public List<Account>  findAll();

    @Insert("insert into account(name,money) values (#{name},#{money})")
    public void saveAccount(Account account);
}
