package leetcode.src.main.java.binaryTree;

/**
 * https://leetcode.cn/problems/invert-binary-tree/
 *
 * @author goodtime
 * @create 2023-12-05 16:51
 */
public class Number224 {
    public static void main(String[] args) {
        System.out.println("test");
    }
}

/**
 * 翻转二叉树，其实就是递归交互每个节点的左右子节点，用后序遍历最方便，先让左子节点互换完，再让右子节点互换完，最后调换当前节点的左右节点
 */
class Solution224 {
    public TreeNode invertTree(TreeNode root) {

        exchange(root);

        return root;
    }

    private void exchange(TreeNode root) {

        //终止条件
        if (root == null) {
            return;
        }

        //递归调用
        exchange(root.left);
        exchange(root.right);

        //处理节点
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        return;

    }
}
