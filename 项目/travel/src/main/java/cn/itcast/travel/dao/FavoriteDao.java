package cn.itcast.travel.dao;

/**
 * @author goodtime
 * @create 2020-01-06 3:54 下午
 */
public interface FavoriteDao {
    boolean findByRidAndUid(int ridReal, int uid);

    int findCountByRid(int rid);

    void addByRidAndUid(int ridReal, int uid);
}
