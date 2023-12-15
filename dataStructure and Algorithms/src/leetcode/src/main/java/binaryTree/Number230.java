package leetcode.src.main.java.binaryTree;

/**
 * https://leetcode.cn/problems/kth-smallest-element-in-a-bst/
 *
 * @author goodtime
 * @create 2023-12-08 07:09
 */
public class Number230 {
    public static void main(String[] args) {
        System.out.println("test");
    }
}

/**
 * 找到第k小的元素，也就是找到二叉搜索树中序遍历的第k个节点
 */
class Solution230 {

    int n;
    int result = 0;

    public int kthSmallest(TreeNode root, int k) {

        if (root == null) {
            return 0;
        }

        n = k;

        inOrder(root);

        return result;
    }

    private void inOrder(TreeNode root) {

        if (root.left != null) {
            inOrder(root.left);
        }

        n--;
        if (n < 0) {
            return;
        } else if (n == 0) {
            result = root.val;
            return;
        } else {
            if (root.right != null) {
                inOrder(root.right);
            }
        }
    }
}
