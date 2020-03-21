package service.impl;


import bean.Account;
import mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.AccountService;

import java.util.List;

/**
 * @author goodtime
 * @create 2020-03-13 1:02 下午
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountMapper accountMapper;

    //如果是基于xml配置，要注入的成员变量必须加入set方法
    public void setAccountMapper(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    public List<Account> findAll() {
       return accountMapper.findAll();
    }

    public Account findById(Integer id) {
        return accountMapper.findById(id);
    }

    public void addAccount(Account account) {
        accountMapper.addAccount(account);
    }

    public void updateAccount(Account account) {
        accountMapper.updateAccount(account);
    }

    public void deleteById(Integer id) {
        System.out.println("接到任务了");
        int i = 1/0;
        accountMapper.deleteById(id);
        System.out.println("完成任务了");
    }

    //先增后删除的操作，模拟事务
    //我们已经动态增强了，详见AOPtransaction
    public void addminusAccount(Account account){
            accountMapper.addAccount(account);
            int i = 1 / 0; //除0导致事务出错,新的账户创建了，却没有删除
            accountMapper.deleteById(account.getId());
    }
}
