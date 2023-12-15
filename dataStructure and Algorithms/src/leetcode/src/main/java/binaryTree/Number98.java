package leetcode.src.main.java.binaryTree;

/**
 * https://leetcode.cn/problems/validate-binary-search-tree/description/
 *
 * @author goodtime
 * @create 2023-12-08 07:23
 */
public class Number98 {
    public static void main(String[] args) {
        System.out.println("test");
    }
}

/**
 * 验证二叉搜索树，就用中序遍历该树即可
 */
class Solution98 {

    Integer value = null;


    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }

        if (root.left == null && root.right == null) {
            return true;
        }

        return inOrder(root);

    }

    private boolean inOrder(TreeNode root) {

        boolean leftResult = true;
        if (root.left != null) {
            leftResult = inOrder(root.left);
        }

        if (value == null) {
            value = root.val;
        } else {
            if (root.val <= value) {
                return false;
            } else {
                value = root.val;
            }
        }

        boolean rightResult = true;

        if (root.right != null) {
            rightResult = inOrder(root.right);
        }
        return leftResult && rightResult;
    }
}
