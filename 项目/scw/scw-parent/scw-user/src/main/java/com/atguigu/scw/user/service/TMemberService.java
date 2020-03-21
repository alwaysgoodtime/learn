package com.atguigu.scw.user.service;

import com.atguigu.scw.user.vo.req.UserRegistVo;
import com.atguigu.scw.user.vo.resp.UserRespVo;

/**
 * @author goodtime
 * @create 2020-02-24 8:19 下午
 */

public interface TMemberService {
    int saveTmember(UserRegistVo vo);

    UserRespVo getUserByLogin(String loginacct, String userpswd);
}
