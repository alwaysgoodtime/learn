package datastructure.graph;

/**
 * 线索化二叉树的节点
 * @author goodtime
 * @create 2020-02-08 7:38 下午
 */
public class ThreadedBinaryTreeNode {
    public ThreadedBinaryTreeNode left = null;//二叉树左节点
    public ThreadedBinaryTreeNode right = null;//右节点
    public int data;//每个二叉树节点中存的数据
    public int leftType;//leftType为0，指向左子树，为1，则表示指向前驱节点
    public int rightType;//rightType为0，指向右子树，为1，则表示指向后继节点


    public ThreadedBinaryTreeNode(int data) {
        this.data = data;
    }

    public ThreadedBinaryTreeNode() {
    }

    public ThreadedBinaryTreeNode getLeft() {
        return left;
    }

    public void setLeft(ThreadedBinaryTreeNode left) {
        this.left = left;
    }

    public ThreadedBinaryTreeNode getRight() {
        return right;
    }

    public void setRight(ThreadedBinaryTreeNode right) {
        this.right = right;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }
}


