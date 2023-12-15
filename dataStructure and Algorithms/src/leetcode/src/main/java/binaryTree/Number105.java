package leetcode.src.main.java.binaryTree;

/**
 * https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 *
 * @author goodtime
 * @create 2023-12-06 17:13
 */
public class Number105 {

    public static void main(String[] args) {

    }
}

/**
 * 从前序和中序遍历中构造二叉树
 */
class Solution105 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {

        if (preorder == null) {
            return null;
        }

        if (preorder.length == 1) {
            return new TreeNode(preorder[0]);
        }

        TreeNode head = new TreeNode(preorder[0]);
        build(preorder, inorder, head);

        return head;
    }

    private TreeNode build(int[] preorder, int[] inorder, TreeNode head) {

        int headIndex = 0;

        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == preorder[0]) {
                headIndex = i;
            }
        }

        if (headIndex == 0) {
            head.left = null;
        } else {
            int[] leftPreOrder = new int[headIndex];
            int[] leftInorder = new int[headIndex];

            for (int i = 0; i < headIndex; i++) {
                leftPreOrder[i] = preorder[i + 1];
                leftInorder[i] = inorder[i];
            }

            head.left = new TreeNode(leftPreOrder[0]);
            build(leftPreOrder, leftInorder, head.left);
        }

        if (headIndex == inorder.length - 1) {
            head.right = null;
        } else {

            int[] rightPreOrder = new int[preorder.length - headIndex - 1];
            int[] rightInorder = new int[preorder.length - headIndex - 1];


            for (int i = 0; i < preorder.length - headIndex - 1; i++) {
                rightPreOrder[i] = preorder[headIndex + i + 1];
                rightInorder[i] = inorder[headIndex + i + 1];
            }

            head.right = new TreeNode(rightPreOrder[0]);
            build(rightPreOrder, rightInorder, head.right);

        }

        return head;
    }
}
