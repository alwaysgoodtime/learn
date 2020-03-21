package com.goodtime.domain;

import lombok.Data;

import java.util.List;

/**
 * 传递pojo包装对象
 * @author goodtime
 * @create 2020-03-11 4:20 下午
 */
@Data
public class QueryVo {

    private Account account;

    private List<Integer> ids;

    public QueryVo() {
    }

    public QueryVo(Account account) {
        this.account = account;
    }

    public QueryVo(Account account, List<Integer> ids) {
        this.account = account;
        this.ids = ids;
    }
}

