package com.atguigu.scw.user.service.impl;

import com.atguigu.scw.enums.UserExceptionEnum;
import com.atguigu.scw.user.bean.TMember;
import com.atguigu.scw.user.bean.TMemberExample;
import com.atguigu.scw.user.exp.UserException;
import com.atguigu.scw.user.mapper.TMemberMapper;
import com.atguigu.scw.user.service.TMemberService;
import com.atguigu.scw.user.vo.req.UserRegistVo;
import com.atguigu.scw.user.vo.resp.UserRespVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * @author goodtime
 * @create 2020-02-24 8:19 下午
 */

//加上事务，不标注解的默认为查询事务
//    小辣椒默认可以开日志
@Slf4j
@Transactional(readOnly = true)
@Service
public class TMermberServiceImpl implements TMemberService {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    TMemberMapper tMemberMapper;

    @Autowired
    TMember tMember;


    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public int saveTmember(UserRegistVo vo) {

        try {
            //将vo对拷到tMember里
            BeanUtils.copyProperties(vo, tMember);

            //用户注册用户时，没有提交username的值，我们要么给bean中的username加一个初始值，或者在这里把他们的loginacct设置为username
            tMember.setUsername(vo.getLoginacct());

            //借助SpringSecurity的BCryptPasswordEncoder，完成密码加密存储
            String string = vo.getUserpswd();
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

            tMember.setUserpswd(bCryptPasswordEncoder.encode(string));

            //有选择性的保存
            int i = tMemberMapper.insertSelective(tMember);
            log.debug("注册会员成功-{}", tMember.toString());

            return i;
        } //注意，这里要改成Exception异常，默认是BeansException异常，是抓不到SQl语句的异常，就不会进入我们的catch中
        catch (Exception e) {
            e.printStackTrace();
            log.error("注册会员失败-{}", e.getMessage());
            //这里如果不抛异常，还是会要求有返回值
            //throw new RuntimeException("保存会员业务逻辑失败");
            //我们自定义异常，这样可以找到是哪个模块出了异常，此时为user模块
            throw new UserException(UserExceptionEnum.USER_SAVE_ERROR);
        }
    }

    @Override
    public UserRespVo getUserByLogin(String loginacct, String userpswd) {

        UserRespVo vo = new UserRespVo();

        TMemberExample example = new TMemberExample();

        example.createCriteria().andLoginacctEqualTo(loginacct);

        List<TMember> list = tMemberMapper.selectByExample(example);

        //判断用户名是否存在
        if (list == null || list.size() == 0) {
            throw new UserException(UserExceptionEnum.USER_NOT_EXIST);
        } else {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            TMember member = list.get(0);
            //每次原始密码就算一样，加密后也不一样，不能加密后直接equals比较
            if (!bCryptPasswordEncoder.matches(userpswd, member.getUserpswd())) {
                throw new UserException(UserExceptionEnum.USER_PASSWORD_ERROR);
            }
            BeanUtils.copyProperties(member, vo);

            //UUID中有-，把他们全部替换走
            String accessToken = UUID.randomUUID().toString().replaceAll("-", "");
            //把令牌存到缓存区里
            //存的值是用户ID，其实只要非空，说明此用户就已经登录了，以后用户访问，就需要来此校验
            stringRedisTemplate.opsForValue().set(accessToken, member.getId().toString());
            //给vo中也存入accessToken
            vo.setAccessToken(accessToken);
            return vo;
        }

    }
}
