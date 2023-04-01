package graph;

/**普通二叉树的节点类
 * @author goodtime
 * @create 2020-02-08 6:24 下午
 */
public class BinaryTreeNode {
    public BinaryTreeNode left = null;//二叉树左节点
    public BinaryTreeNode right = null;//右节点
    public int data;//每个二叉树节点中存的数据

    public BinaryTreeNode(int data) {
        this.data = data;
    }

    public BinaryTreeNode() {
    }

    public void setLeft(graph.BinaryTreeNode left) {
        this.left = left;
    }

    public void setRight(graph.BinaryTreeNode right) {
        this.right = right;
    }
}
