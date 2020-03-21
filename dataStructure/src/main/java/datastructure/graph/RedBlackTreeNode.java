package datastructure.graph;

/**
 * 红黑树的节点，比一般的树多了一个
 * @author goodtime
 * @create 2020-02-10 2:32 下午
 */

public class RedBlackTreeNode {

    int data;//红黑树存的数据
    boolean color = true;//红黑树默认插入都是红色，我们约定，ture代表红色，false代表黑色
    RedBlackTreeNode left;
    RedBlackTreeNode right;
    RedBlackTreeNode parent;//指向节点的根节点，如果根节点为null，则此节点为整个红黑树的根节点

    public RedBlackTreeNode(int data) {
        this.data = data;
    }

    public RedBlackTreeNode(int data, boolean color) {
        this.data = data;
        this.color = color;
    }

    public RedBlackTreeNode(int data, boolean color, RedBlackTreeNode parent) {
        this.data = data;
        this.color = color;
        this.parent = parent;
    }
}
