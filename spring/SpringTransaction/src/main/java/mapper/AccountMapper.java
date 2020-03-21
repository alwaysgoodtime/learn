package mapper;


import bean.Account;

import java.util.List;

/**
 * @author goodtime
 * @create 2020-03-13 12:58 下午
 */
public interface AccountMapper {
    List<Account> findAll();
     Account  findById(Integer id);
     void addAccount(Account account);
     void updateAccount(Account account);
     void deleteById(Integer id);
}
