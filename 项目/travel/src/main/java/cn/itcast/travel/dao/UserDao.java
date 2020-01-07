package cn.itcast.travel.dao;

import cn.itcast.travel.domain.User;

/**
 * @author goodtime
 * @create 2020-01-04 1:42 下午
 */
public interface UserDao {

    void addUser(User user);
    boolean findByUsername(String username);

    boolean findByUsernameAndPassword(String username, String password);

    boolean findStatusByUsername(String username);

    String getUserByCode(String code);

    void updateStatusByUsername(String username);

    int findByUsernameReturnUid(String username);
}
