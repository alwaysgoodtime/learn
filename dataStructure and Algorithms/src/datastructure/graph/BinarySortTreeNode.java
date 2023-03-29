package datastructure.graph;

/**
 * 二叉排序树节点
 * @author goodtime
 * @create 2020-02-09 6:19 下午
 */
public class BinarySortTreeNode {
    public BinarySortTreeNode left = null;//二叉树左节点
    public BinarySortTreeNode right = null;//右节点
    public int data;//每个二叉树节点中存的数据

    public BinarySortTreeNode(int data) {
        this.data = data;
    }

    public BinarySortTreeNode() {
    }

    public void setLeft(datastructure.graph.BinarySortTreeNode left) {
        this.left = left;
    }

    public void setRight(datastructure.graph.BinarySortTreeNode right) {
        this.right = right;
    }
}
