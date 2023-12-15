package leetcode.src.main.java.binaryTree;

/**
 * https://leetcode.cn/problems/sum-root-to-leaf-numbers/
 *
 * @author goodtime
 * @create 2023-12-06 22:04
 */
public class Number129 {

    public static void main(String[] args) {
        System.out.println("test");
    }
}

/**
 * 前序遍历
 */
class Solution129 {
    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return preOrder(root, 0);

    }

    private int preOrder(TreeNode root, int pathSum) {

        int sum = pathSum * 10 + root.val;

        if (root.left == null && root.right == null) {
            return sum;
        }

        int result = 0;

        if (root.left != null) {
            result += preOrder(root.left, sum);
        }

        if (root.right != null) {
            result += preOrder(root.right, sum);
        }

        return result;

    }
}
