package cn.itcast.travel.service;

import cn.itcast.travel.domain.User;

/**
 * @author goodtime
 * @create 2020-01-04 1:32 下午
 */
public interface UserService {
    boolean findUserByUsername(String username);

    void addUser(User user);

    boolean findUserByUsernameAndPassword(String username,String password);

    boolean findStatusByUsername(String username);

    boolean getUserByCode(String code);
}
