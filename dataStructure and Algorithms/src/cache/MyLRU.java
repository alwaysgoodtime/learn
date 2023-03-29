package cache;

import java.util.HashMap;

/**
 * 用hashMap+双向链表模拟缓存
 * 双向链表的节点为内部类CacheNode
 * hashMap为HashMap<K, CacheNode>
 *
 * @author goodtime
 * @create 2020-03-26 12:08 上午
 */
public class MyLRU<K, V> {
    private HashMap<K, CacheNode> caches;

    private int cacheSize;

    private CacheNode first;

    private CacheNode last;

    private int currentCacheSize;//初始值为0


    public MyLRU(int cacheSize) {
        this.cacheSize = cacheSize;
        caches = new HashMap<>(cacheSize);
    }

    public MyLRU() {
        this(10);//调用有参构造器，用this关键字
    }//空参构造器,默认缓存大小为10

    public void put(K key, V value) {
        CacheNode newNode = new CacheNode(key, value);
        if (caches.containsKey(key)) {//说明这个key曾经存过，现在是重新放其他数据
            caches.put(key, newNode);
        } else {
            if (currentCacheSize == cacheSize) {//说明缓存已满，先清缓存
                caches.remove(last.key);
                removeLast();
                currentCacheSize--;
            }
            caches.put(key, newNode);
            currentCacheSize++;
        }
        moveToFirst(newNode);//每次放的时候，都默认放在头结点，也就将CacheNode的pre和next属性激活了
    }

    private void removeLast() {
        System.out.println("删除" + last.key + " v: " + last.value);
        if (last != null) {//这个方法，在hashmap没有节点时也能调用，所以要注意取值
            last = last.pre;
            if (last == null) {
                first = null;//last.pre = null,说明此时hashMap中就这个一个节点，first和last指向同一个节点，因此也设为null即可
            } else {
                last.next = null;
            }
        }
    }

    //调用clear方法，重新初始化缓存中的各个值
    public void clear() {
        first = null;
        currentCacheSize = 0;
        caches.clear();
        last = null;

    }

    //remove方法，删除当前key
    public void remove(K key) {
        if (caches.containsKey(key)) {
            CacheNode remove = caches.remove(key);
            if (remove == null && remove.next == null) {//即是头结点，又是尾节点
                clear();//直接调用clear方法即可
            } else {
                currentCacheSize--;
                if (remove == null) {
                    first = first.next;
                    first.pre = null; //定义新的头节点
                } else if (remove.next == null) {
                    last = remove.pre;
                    last.next = null;//定义新的尾节点
                }else {
                    remove.pre.next = remove.next;
                    remove.next.pre = remove.pre;
                }
            }
        } else {
            return;
        }

    }


    public V get(K k) {//这个数组看允不允许插入null为value的值，这里默认允许
        if (caches.containsKey(k)) {
            CacheNode cacheNode = caches.get(k);
            caches.remove(k);//先在hashMap删除该节点
            if (cacheNode.pre == null) {//说明它本来就是头节点,什么事也不用做
            } else if (cacheNode.next == null) {//说明它原来是尾节点
                last = cacheNode.pre;
                last.next = null;//重置尾节点即可
            } else {
                cacheNode.pre.next = cacheNode.next;
                cacheNode.next.pre = cacheNode.pre;//在形成的双向链表中，把此节点删掉
                moveToFirst(cacheNode);
            }
            return cacheNode.value;
        }
        return null;
    }

    //内部类，定义每个节点，是hashmap中存的真正的value
    private class CacheNode {
        private CacheNode pre;
        private CacheNode next;
        private K key;
        private V value;

        public CacheNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }

    //将元素放到链表开始
    private void moveToFirst(CacheNode newNode) {
        if (first == null) {
            first = newNode;
            last = newNode;//如果first为null,初始化时，把last和first设为同一个节点
        } else {
            first.pre = newNode;
            newNode.next = first;
            first = newNode;
        }
    }
}
