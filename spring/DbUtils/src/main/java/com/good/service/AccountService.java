package com.good.service;

import com.good.domain.Account;

import java.util.List;

/**
 * @author goodtime
 * @create 2020-03-13 1:01 下午
 */
public interface AccountService {
    List<Account> findAll();
    Account  findById(Integer id);
    void addAccount(Account account);
    void updateAccount(Account account);
    void deleteById(Integer id);
    void addminusAccount(Account account);
}
