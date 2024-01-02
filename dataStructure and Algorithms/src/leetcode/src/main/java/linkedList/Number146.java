package leetcode.src.main.java.linkedList;

import java.util.HashMap;

/**
 * @author goodtime
 * @create 2023-12-05 13:46
 */
public class Number146 {

    public static void main(String[] args) {
        System.out.println("test");
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

        DLinkedNode node = map.get(key);

        if (node == null) {
            return -1;
        } else {
            updateNode(node);
            return node.value;
        }
    }

    public void put(int key, int value) {


        //更新值
        if (map.get(key) != null) {

            DLinkedNode node = map.get(key);
            node.value = value;
            updateNode(node);
            return;
        }


        if (size == capacity) {

            //弹出最久未使用的key
            Integer uselessKey = head.key;
            map.remove(uselessKey);

            if (size == 1) {
                head = null;
                last = null;
            } else {
                head = head.next;
            }
            size--;
        }

        DLinkedNode newNode = new DLinkedNode(key, value);

        if (size == 0) {
            head = newNode;
            last = newNode;
        } else {
            newNode.prev = last;
            last.next = newNode;
            last = newNode;

        }

        //放入新key-value
        map.put(key, newNode);
        size++;

    }

    private void updateNode(DLinkedNode node) {

        if (node == last) {
            return;
        }

        //更新节点先后顺序
        if (node != head) {
            DLinkedNode before = node.prev;
            before.next = node.next;
            node.next.prev = before;
        } else {
            head = node.next;
        }

        last.next = node;
        node.prev = last;
        node.next = null;
        last = node;

    }
}
