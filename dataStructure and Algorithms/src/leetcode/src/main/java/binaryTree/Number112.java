package leetcode.src.main.java.binaryTree;

/**
 * https://leetcode.cn/problems/path-sum/
 *
 * @author goodtime
 * @create 2023-12-06 21:49
 */
public class Number112 {

    public static void main(String[] args) {
        System.out.println("test");
    }

}

/**
 * 先序遍历root后，到每个叶子节点看和是否满足
 */
class Solution112 {
    public boolean hasPathSum(TreeNode root, int targetSum) {

        if (root == null) {
            return false;
        }


        return preOrder(root, targetSum);

    }

    private boolean preOrder(TreeNode root, int targetSum) {

        if (root.left == null && root.right == null) {
            return targetSum == root.val;
        }

        boolean leftRoutine = false;
        boolean rightRoutine = false;


        if (root.left != null) {
            leftRoutine = preOrder(root.left, targetSum - root.val);
        }

        if (leftRoutine) {
            return true;
        }

        if (root.right != null) {
            rightRoutine = preOrder(root.right, targetSum - root.val);
        }

        return rightRoutine;

    }
}
