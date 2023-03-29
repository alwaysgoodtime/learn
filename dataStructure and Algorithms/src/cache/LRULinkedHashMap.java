package cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 1. 新数据插入到链表头部；
 * 2. 每当缓存命中（即缓存数据被访问），则将数据移到链表头部；
 * 3. 当链表满的时候，将链表尾部的数据丢弃。
 * 分析
 * 【命中率】
 * 当存在热点数据时，LRU的效率很好，但偶发性的、周期性的批量操作会导致LRU命中率急剧下降，缓存污染情况比较严重。
 * 【复杂度】
 * 实现简单。
 * 【代价】
 * 命中时需要遍历链表，找到命中的数据块索引，然后需要将数据移到头部。
 *
 * @author goodtime
 * @create 2020-03-25 11:16 下午
 */


/**
 * 类说明：利用LinkedHashMap实现简单的缓存， 必须实现removeEldestEntry方法，具体参见JDK文档
 * <p>
 * 这个是复杂版的缓存机制，好处是加了锁，因此是线程安全的
 *
 * @param <K>
 * @param <V>
 */
public class LRULinkedHashMap<K, V> extends LinkedHashMap<K, V> {

    private final int maxCapacity;

    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private final Lock lock = new ReentrantLock();//锁

    public LRULinkedHashMap(int maxCapacity) {
        super(maxCapacity, DEFAULT_LOAD_FACTOR, true);
        this.maxCapacity = maxCapacity;
    }

    //核心其实就这一句话，便实现了LRU
    @Override
    protected boolean removeEldestEntry(java.util.Map.Entry<K, V> eldest) {
        return size() > maxCapacity;
    }


    //查看是否有key，以下方法重写的原因，都是为了加锁，保证线程安全而已
    @Override
    public boolean containsKey(Object key) {
        try {
            lock.lock();
            return super.containsKey(key);
        } finally {
            lock.unlock();
        }
    }

    //get方法
    @Override
    public V get(Object key) {
        try {
            lock.lock();
            return super.get(key);
        } finally {
            lock.unlock();
        }
    }

    //put方法
    @Override
    public V put(K key, V value) {
        try {
            lock.lock();
            return super.put(key, value);
        } finally {
            lock.unlock();
        }
    }

    //查看缓存的大小
    public int size() {
        try {
            lock.lock();
            return super.size();
        } finally {
            lock.unlock();
        }
    }

    //让缓存所有数据都失效
    public void clear() {
        try {
            lock.lock();
            super.clear();
        } finally {
            lock.unlock();
        }
    }

    //得到其中存放的所有数据
    public Collection<Map.Entry<K, V>> getAll() {
        try {
            lock.lock();
            return new ArrayList<Map.Entry<K, V>>(super.entrySet());
        } finally {
            lock.unlock();
        }
    }

}