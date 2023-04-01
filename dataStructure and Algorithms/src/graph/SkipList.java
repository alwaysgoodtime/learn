package graph;

import java.util.Random;

/**
 * @author goodtime
 * @create 2020-03-04 4:30 下午
 */
public class SkipList {
    private static final int MAX_LEVEL = 16;//默认最高为16层
    private int levelCount = 1;//标识当前链表的最高层
    private Node head = new Node();//标识链表头
    private Random random = new Random();

    //查找
    public Node find(int value) {
        Node p = head;
        for (int i = levelCount - 1; i >= 0; i--) {
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                p = p.forwards[i];
            }//从上往下进行层次的遍历
        }
        if (p.forwards[0] != null && p.forwards[0].data == value) return p.forwards[0];//如果是找到了，就返回，找不到就返回null
        return null;
    }

    //增
    public void insert(int value) {
        Node p = head;
        int level = randomLevel();
        Node node = new Node();
        node.data = value;
        node.maxLevel = level;//指定node最大层级
        Node update[] = new Node[level];//指定node的层级
        for (int i = level; i >= 0; i--) {
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                p = p.forwards[i];
            }
            update[i] = p;
        }
        for (int i = 0; i < level; i++) {
            node.forwards[i] = update[i].forwards[i];
            update[i].forwards[i] = node;
        }
        if (levelCount < level) levelCount = level;//更新当前链表的最高层
    }

    //删
    public void delete(int value) {
        Node[] deleteNode = new Node[MAX_LEVEL];
        Node p = head;
        for (int i = levelCount - 1; i >= 0; i--) {
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                p = p.forwards[i];
            }
            deleteNode[i] = p;//先找到要删的节点前面所有连着它的节点
        }
        //如果退出循环，要么是找到p，p的后面节点就是要删的节点，要么没找到，p的下一个节点为null
        if (p.forwards[0] != null && p.forwards[0].data == value) {
            for (int i = levelCount - 1; i >= 0; i--) {
                if (deleteNode[i] != null && deleteNode[i].forwards[i].data == value) {
                    deleteNode[i].forwards[i] = deleteNode[i].forwards[i].forwards[i];//相当于跳过这个节点
                }
            }
        }
    }

    //遍历
    public void printAll() {
        Node p = head;
        while (p.forwards[0] != null) {
            System.out.print(p.forwards[0] + " ");
            p = p.forwards[0];//遍历头节点的第1层即可
        }
        System.out.println();
    }

    private int randomLevel() {//生成一个0-16的随机数，表示层级
        int level = 0;
        for (int i = 0; i < MAX_LEVEL; i++) {
            if (random.nextInt() % 2 == 1) {
                level++;
            }
        }
        return level;
    }

    //跳表中的每个节点
    class Node {
        private int data;
        private Node[] forwards = new Node[MAX_LEVEL];//跳表所在的层级，实例化时会随机一个层级
        private int maxLevel;

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("{data: ");
            sb.append(data);
            sb.append("; level: ");
            sb.append(maxLevel);
            sb.append(" }");
            return sb.toString();
        }
    }
}
