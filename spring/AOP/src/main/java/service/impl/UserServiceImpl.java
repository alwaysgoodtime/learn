package service.impl;

import org.springframework.stereotype.Service;
import service.UserService;

/**
 * @author goodtime
 * @create 2020-03-14 12:45 上午
 */
@Service("service")
public class UserServiceImpl implements UserService {
    public int findAll() {
        System.out.println("查找所有用户");
        return 1;
    }

    public void update() {
        System.out.println("更新用户");
    }

    public void delete(int id) {
        System.out.println("删除用户");
    }
}
