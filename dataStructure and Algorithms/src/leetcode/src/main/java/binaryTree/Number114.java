package leetcode.src.main.java.binaryTree;

import java.util.ArrayList;

/**
 * https://leetcode.cn/problems/flatten-binary-tree-to-linked-list/
 *
 * @author goodtime
 * @create 2023-12-06 20:41
 */
public class Number114 {

    public static void main(String[] args) {
        System.out.println("test");
    }

}

/**
 * 先序遍历树,把节点装到list中，然后挨个处理
 */
class Solution114 {

    public void flatten(TreeNode root) {

        if (root == null) {
            return;
        }

        ArrayList<TreeNode> list = new ArrayList<>();

        preOrder(root, list);

        for (int i = 0; i < list.size(); i++) {
            TreeNode treeNode = list.get(i);
            treeNode.left = null;
            if (i < list.size() - 1) {
                treeNode.right = list.get(i + 1);
            } else {
                treeNode.right = null;
            }
        }
    }

    private void preOrder(TreeNode root, ArrayList<TreeNode> list) {

        if (root == null) {
            return;
        }

        list.add(root);

        preOrder(root.left, list);
        preOrder(root.right, list);

    }

    /**
     * 原地更改版,不停让左叶子节点的最右节点连接根节点的右节点，同时让根节点的左节点变为右节点
     */
    private void preOrderAdvanced(TreeNode root, ArrayList<TreeNode> list) {

        while (root != null) {

            if (root.left != null) {
                TreeNode left = root.left;

                while (left.right != null) {
                    left = left.right;
                }

                left.right = root.right;
                root.right = root.left;
                TreeNode tmp = root.left;
                root.left = null;
                root = tmp;
            } else if (root.right != null) {
                root = root.right;
            } else {
                break;
            }
        }


    }

}