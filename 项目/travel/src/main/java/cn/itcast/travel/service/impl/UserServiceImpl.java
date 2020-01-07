package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.util.UuidUtil;

import javax.sound.midi.SoundbankResource;
import java.io.UnsupportedEncodingException;
import java.security.cert.CertPathBuilderResult;

/**
 * @author goodtime
 * @create 2020-01-04 1:33 下午
 */
public class UserServiceImpl implements UserService{
    UserDao userDao = new UserDaoImpl();

    @Override
    public boolean findUserByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public void addUser(User user) {
        user.setStatus("N");
        user.setCode(UuidUtil.getUuid());
        try {
            System.out.println(user.getSex());
            //linux电脑上直接输出即可，已经解码好了
            //String result = java.net.URLDecoder.decode(user.getSex(),"UTF-8");//这一步linux上也不需要
            //我的电脑上需要这样设置
            //user.setSex(new String(user.getSex().getBytes("ISO8859-1"),"UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        userDao.addUser(user);
    }

    @Override
    public boolean findUserByUsernameAndPassword(String username,String password) {
        return userDao.findByUsernameAndPassword(username,password);

    }

    @Override
    public boolean findStatusByUsername(String username) {
        return userDao.findStatusByUsername(username);
    }

    @Override
    public boolean getUserByCode(String code) {
        String userByCode = userDao.getUserByCode(code);
        if(userByCode == null){
            return false;
        }else{
            userDao.updateStatusByUsername(userByCode);
            return true;
        }
    }
}
