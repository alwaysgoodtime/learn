package graph;

/**
 * 二叉平衡树的节点，BST为首字母简写
 * @author goodtime
 * @create 2020-02-09 10:17 下午
 */
public class BSTNode {
    public BSTNode left = null;//二叉树左节点
    public BSTNode right = null;//右节点
    public int data;//每个二叉树节点中存的数据
    public BSTNode(int data) {
        this.data = data;
    }

    public BSTNode() {
    }

    public void setLeft(graph.BSTNode left) {
        this.left = left;
    }

    public void setRight(graph.BSTNode right) {
        this.right = right;
    }
}
