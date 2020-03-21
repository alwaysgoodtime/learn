package com.good.service.impl;

import com.good.domain.Account;
import com.good.mapper.AccountMapper;
import com.good.mapper.impl.AccountMapperImpl;
import com.good.service.AccountService;
import com.good.utils.TransactionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author goodtime
 * @create 2020-03-13 1:02 下午
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    TransactionUtils transactionUtils;

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
        accountMapper.deleteById(id);
    }

    //先增后删除的操作，模拟事务
    //我们已经动态增强了，详见AOPtransaction
    public void addminusAccount(Account account){
//        try {
//            //开启事务
//            transactionUtils.beginTransaction();
//            System.out.println(transactionUtils.getConnectionUtils());
//            System.out.println(Thread.currentThread().getName());
//            //执行操作
            accountMapper.addAccount(account);
            int i = 1 / 0; //除0导致事务出错,新的账户创建了，却没有删除
            accountMapper.deleteById(account.getId());
            //提交事务
//            transactionUtils.commit();
//            //返回结果,有则写，没有则不写
//        }catch (Exception e){
//            //回滚
//            e.getCause();
//            System.out.println("回滚了");
//            transactionUtils.rollback();
//        }finally {
//            //释放资源
//            transactionUtils.release();
//        }


    }
}
