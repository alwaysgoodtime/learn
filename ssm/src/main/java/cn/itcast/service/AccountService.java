package cn.itcast.service;

import cn.itcast.domain.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author goodtime
 * @create 2020-02-01 2:30 下午
 */

public interface AccountService {

    public List<Account> findAll();

    public void saveAccount(Account account);

}
