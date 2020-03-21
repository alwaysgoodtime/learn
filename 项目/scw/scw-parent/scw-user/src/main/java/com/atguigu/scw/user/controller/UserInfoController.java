package com.atguigu.scw.user.controller;

import java.util.List;

import com.atguigu.scw.user.bean.TMemberAddress;
import com.atguigu.scw.user.service.UserService;
import com.atguigu.scw.user.vo.resp.UserRespVo;
import com.atguigu.scw.vo.resp.AppResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "用户信息模块(个人中心维护信息)") // 描述当前类是做什么的
@RequestMapping("/user/info")
@RestController
@Slf4j
public class UserInfoController {

	@Autowired
	StringRedisTemplate redisTemplate;

	@Autowired
	UserService userService;

	@ApiOperation("[+]获取用户地址")
	@GetMapping("/address")
	public AppResponse<List<TMemberAddress>> addresses(String accessToken) {

		//从redis缓存中查到accessToken对应的用户id
		String string = redisTemplate.opsForValue().get(accessToken);
		Integer memberId = Integer.parseInt(string);

		List<TMemberAddress> address = userService.getUserAddress(memberId);
		return AppResponse.ok(address);
	}

	@ApiOperation("[+]获取用户信息")
	@GetMapping("/{id}") 
	public AppResponse<UserRespVo> getMemberInfo(@PathVariable("id") Integer memberid) {
		UserRespVo userById = userService.getUserById(memberid);
		return AppResponse.ok(userById);
	};

}
