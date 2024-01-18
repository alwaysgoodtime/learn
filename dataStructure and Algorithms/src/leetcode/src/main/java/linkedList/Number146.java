package leetcode.src.main.java.linkedList;

import java.util.*;

/**
 * @author goodtime
 * @create 2023-12-05 13:46
 */
public class Number146 {

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(1);
        lruCache.put(2,1);
        lruCache.get(2);
        lruCache.put(3,2);
        lruCache.get(2);
        lruCache.get(3);
    }
}

/**
 * @see cache.LRULinkedHashMap
 * 哈希表 + 双向链表实现
 */
class LRUCache {

    class DLinkedNode {
        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;

        public DLinkedNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    DLinkedNode head;
    DLinkedNode last;

    /**
     * 存储key与value,value为双向链表节点
     */
    HashMap<Integer, DLinkedNode> map = new HashMap();

    int size = 0;

    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {

        DLinkedNode dLinkedNode = map.get(key);

        if (dLinkedNode == null) {
            return -1;
        }

        //查到dLinkedNode后，将其放到head
        putToHead(dLinkedNode);

        return dLinkedNode.value;

    }

    private void putToHead(DLinkedNode dLinkedNode) {

        if (head == null) {
            head = dLinkedNode;
            last = dLinkedNode;
            return;
        }

        if (head == dLinkedNode) {
            return;
        }

        if (last == dLinkedNode) {
            //前一节点变为last
            dLinkedNode.prev.next = null;
            last = dLinkedNode.prev;
        } else if (dLinkedNode.prev != null && dLinkedNode.next != null) {
            //表示dLinkedNode原先就在链中，先从链中摘除
            dLinkedNode.prev.next = dLinkedNode.next;
            dLinkedNode.next.prev = dLinkedNode.prev;
        } else {
            //dLinkedNode为新节点，等待后续处理
        }

        //当前节点变为head,原head变为head.next
        dLinkedNode.prev = null;
        dLinkedNode.next = head;
        head.prev = dLinkedNode;
        head = dLinkedNode;

    }

    public void put(int key, int value) {

        DLinkedNode dLinkedNode = map.get(key);

        if (dLinkedNode != null) {
            dLinkedNode.value = value;
            putToHead(dLinkedNode);
            return;
        }

        DLinkedNode node = new DLinkedNode(key, value);
        map.put(key, node);

        //需要放入新节点
        if (size < capacity) {
            putToHead(node);
            size++;
        } else {
            //将最后一个节点去掉
            map.remove(last.key);
            if (last.prev == null) {
                head = null;
                last = null;
            } else {
                last = last.prev;
                last.next = null;
            }

            //将新节点放到第一个节点
            putToHead(node);
        }
    }
}
