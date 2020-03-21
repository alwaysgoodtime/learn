package com.atguigu.scw.user.service;


import com.atguigu.scw.user.bean.TMember;
import com.atguigu.scw.user.bean.TMemberAddress;
import com.atguigu.scw.user.vo.resp.UserRespVo;

import java.util.List;

public interface UserService {


	/**
	 * 获取会员地址
	 * @param userId
	 * @return
	 */
	List<TMemberAddress> getUserAddress(Integer userId);


	/**
	 * 根据用户id获取用户信息
	 * @param memberid
	 * @return
	 */
	UserRespVo getUserById(Integer memberid);

}
