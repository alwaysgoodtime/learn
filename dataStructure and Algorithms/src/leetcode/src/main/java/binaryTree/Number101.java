package leetcode.src.main.java.binaryTree;

import java.util.LinkedList;

/**
 * https://leetcode.cn/problems/symmetric-tree/
 *
 * @author goodtime
 * @create 2023-12-06 13:49
 */
public class Number101 {

    public static void main(String[] args) {
        System.out.println("test");
    }

}

/**
 * 轴对称，其实是让节点的左节点和右节点两相比较
 */
class Solution101 {
    public boolean isSymmetric(TreeNode root) {

        if (root == null) {
            return false;
        }

        return symmetric(root.left, root.right);

    }

    /**
     * 递归解法
     */
    private boolean symmetric(TreeNode left, TreeNode right) {

        if (left == null && right != null) {
            return false;
        }

        if (left != null && right == null) {
            return false;
        }

        if (left == null && right == null) {
            return true;
        }

        if (left.val != right.val) {
            return false;
        }

        return symmetric(left.left, right.right) && symmetric(left.right, right.left);

    }

    /**
     * 迭代 类似深度遍历
     */
    public boolean isSymmetricIteration(TreeNode root) {

        if (root == null) {
            return false;
        }

        LinkedList<TreeNode> queue = new LinkedList<>();

        if (root.left == null && root.right == null) {
            return true;
        }

        if (root.left == null && root.right != null) {
            return false;
        }

        if (root.left != null && root.right == null) {
            return false;
        }

        queue.add(root.left);
        queue.add(root.right);

        while (queue.size() != 0) {

            TreeNode first = queue.pollFirst();
            TreeNode last = queue.pollFirst();

            if (first.val != last.val) {
                return false;
            }

            if (first.left != null && last.right == null) {
                return false;
            } else if (first.right == null && last.left != null) {
                return false;
            } else if (first.left == null && last.right != null) {
                return false;
            } else if (first.right != null && last.left == null) {
                return false;
            }

            if (first.left != null && last.right != null) {
                queue.push(first.left);
                queue.push(last.right);
            }

            if (first.right != null && last.left != null) {
                queue.push(first.right);
                queue.push(last.left);
            }

        }

        return true;


    }


}
