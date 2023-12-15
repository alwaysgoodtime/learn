package leetcode.src.main.java.binaryTree;


/**
 * https://leetcode.cn/problems/maximum-depth-of-binary-tree/
 *
 * @author goodtime
 * @create 2023-12-05 16:13
 */
public class Number104 {
    public static void main(String[] args) {
        System.out.println("test");
    }

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

/**
 * 按照前序遍历二叉树，到达叶子节点时，查看每个叶子节点的深度，找到最大值即可
 */
class Solution104 {
    public int maxDepth(TreeNode root) {

        if (root == null) {
            return 0;
        }

        return backtrack(root, 1);

    }

    private int backtrack(TreeNode root, int depth) {

        if (root.left == null && root.right == null) {
            return depth;
        }

        int leftMaxDepth = 0;
        int rightMaxDepth = 0;

        if (root.left != null) {
            leftMaxDepth = backtrack(root.left, depth + 1);
        }

        if (root.right != null) {
            rightMaxDepth = backtrack(root.right, depth + 1);
        }

        return Math.max(leftMaxDepth, rightMaxDepth);
    }


}
