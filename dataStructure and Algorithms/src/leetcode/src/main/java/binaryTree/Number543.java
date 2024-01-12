package leetcode.src.main.java.binaryTree;

/**
 * https://leetcode.cn/problems/diameter-of-binary-tree
 *
 * @author goodtime
 * @create 2024-01-12 18:25
 */
public class Number543 {
    public static void main(String[] args) {
        System.out.println("test");
    }
}

/**
 * 任意路径都有一个父节点，路径长度=父节点左子节点+父节点右子节点+父节点-1
 *
 * 题目可以转换为，给定任意节点，所能得到的最多的左子节点+最多的右子节点
 */
class Solution543 {

    int maxLength = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return maxLength;
    }

    /**
     * 后序遍历
     */
    private int dfs(TreeNode root) {

        if (root == null) {
            return 0;
        }

        int maxLeftLength = dfs(root.left);
        int maxRightLength = dfs(root.right);


        maxLength = Math.max(maxLeftLength + maxRightLength, maxLength);

        return Math.max(maxLeftLength + 1, maxRightLength + 1);

    }
}