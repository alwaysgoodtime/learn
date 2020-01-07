package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.dao.impl.CategoryDaoImpl;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author goodtime
 * @create 2020-01-05 10:18 上午
 */
public class CategoryServiceImpl implements CategoryService {

//    Jedis jedis = JedisUtil.getJedis();

    CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public List findCategory() {

//        Set<Tuple> categorys = jedis.zrangeWithScores("category", 0, -1);
//        ArrayList arrayList = new ArrayList();
//
//        if (categorys == null || categorys.size() == 0) {
            List<Category> li = categoryDao.findCategory();
//            for (Category a : li) {
//                jedis.zadd("category", a.getCid(), a.getCname());
//            }
            return li;
//        } else {
//            for (Tuple tuple:categorys){
//                String element = tuple.getElement();
//                int score = (int)tuple.getScore();
//                Category category1 = new Category();
//                category1.setCid(score);
//                category1.setCname(element);
//                arrayList.add(category1);
//            }
//            System.out.println("从redis查");
//            return arrayList;
//            }

        }
}

