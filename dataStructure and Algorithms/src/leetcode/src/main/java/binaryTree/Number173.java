package leetcode.src.main.java.binaryTree;

import java.util.ArrayList;

/**
 * https://leetcode.cn/problems/binary-search-tree-iterator/
 *
 * @author goodtime
 * @create 2023-12-06 23:47
 */
public class Number173 {
    public static void main(String[] args) {
        System.out.println("test");
    }
}

class BSTIterator {

    int pointer = 0;
    ArrayList<TreeNode> list = new ArrayList();

    public BSTIterator(TreeNode root) {
        onOrder(root);
    }

    public int next() {
        return list.get(pointer++).val;
    }

    public boolean hasNext() {
        return pointer < list.size();
    }

    private void onOrder(TreeNode treeNode) {

        if (treeNode.left != null) {
            onOrder(treeNode.left);
        }

        list.add(treeNode);

        if(treeNode.right != null){
            onOrder(treeNode.right);
        }

    }
}