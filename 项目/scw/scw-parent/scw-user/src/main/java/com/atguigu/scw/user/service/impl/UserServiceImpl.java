package com.atguigu.scw.user.service.impl;

import com.atguigu.scw.user.bean.TMember;
import com.atguigu.scw.user.bean.TMemberAddress;
import com.atguigu.scw.user.bean.TMemberAddressExample;
import com.atguigu.scw.user.mapper.TMemberAddressMapper;
import com.atguigu.scw.user.mapper.TMemberMapper;
import com.atguigu.scw.user.service.UserService;
import com.atguigu.scw.user.vo.resp.UserRespVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    TMemberMapper memberMapper;

    @Autowired
    TMemberAddressMapper memberAddressMapper;


    @Override
    public List<TMemberAddress> getUserAddress(Integer userId) {
        TMemberAddressExample example = new TMemberAddressExample();
        example.createCriteria().andMemberidEqualTo(userId);
        List<TMemberAddress> list = memberAddressMapper.selectByExample(example);
        return list;
    }

    @Override
    public UserRespVo getUserById(Integer memberid)
	{
		UserRespVo userRespVo = new UserRespVo();
		TMember tMember = memberMapper.selectByPrimaryKey(memberid);
		BeanUtils.copyProperties(tMember, userRespVo);
		return userRespVo;

	}

}
