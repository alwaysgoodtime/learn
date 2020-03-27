package cache;

import sun.util.resources.cldr.ewo.CalendarData_ewo_CM;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * LinkedHashMap的removeEldestEntry默认返回false，如果重写该方法，（以下是匿名实现类），那么就会根据我们传的accessOrder为
 * true还是false，来判断缓存的过期策略，默认为false，也就是FIFO，先插入的如果缓存放不下了，就最先赶出去，如果为true，就是lru算法
 * 最近最少使用，一旦get了一下，该数据就会放到链表表头，而让缓存过期，是从链表尾部开始的
 * @author goodtime
 * @create 2020-03-25 11:25 下午
 */
public class SimpleLRU{
    public static void main(String[] args) {

        //LRU简单实现版，并非线程安全
        Map<Object, Object> cache = new LinkedHashMap<Object, Object>(4,0.75f,true){
            @Override
            protected boolean removeEldestEntry(Map.Entry<Object, Object> eldest) {
                boolean flag = size() > 4;
                if(flag){
                    Object key = eldest.getKey();
                    System.out.println(key);
                }
                return flag;
            }
        };


        cache.put(null,null);
        cache.put(1,1);
        cache.put(2,2);
        cache.put(3,3);
        cache.put(4,4);
        cache.put(5,5);
        cache.put(6,6);
        cache.get(3);
        cache.put(7,7);


        //FIFO缓存过期策略实现版
        Map<Object, Object> cache2 = new LinkedHashMap<Object, Object>(4,0.75f,false){
            @Override
            protected boolean removeEldestEntry(Map.Entry<Object, Object> eldest) {
                boolean flag = size() > 4;
                if(flag){
                    Object key = eldest.getKey();
                    System.out.println(key);
                }
                return flag;
            }
        };

        cache2.put(1,1);
        cache2.put(2,2);
        cache2.put(3,3);
        cache2.put(4,4);
        cache2.put(5,5);
        cache2.put(6,6);
        cache2.get(3);
        cache2.put(7,7);

        HashMap<Object, Object> map = new HashMap<>();
        map.put(1,null);//无错
        map.put(null,1);//无错
        Object o = map.get(1);
        System.out.println(o);
        ConcurrentHashMap<Object, Object> map2 = new ConcurrentHashMap<>();
        //map2.put(1,null);//报错
        //map2.put(null,1);//报错
        Hashtable<Object,Object> hashTable = new Hashtable<>();
        //hashTable.put(1,null);//报错
        //hashTable.put(null,1);//报错

    }


}
