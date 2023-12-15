package leetcode.src.main.java.binaryTree;

/**
 * https://leetcode.cn/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
 *
 * @author goodtime
 * @create 2023-12-06 18:04
 */
public class Number106 {

    public static void main(String[] args) {
        System.out.println("test");
    }
}

/**
 * @see Number105
 * 从中序和后序中还原二叉树
 */
class Solution106 {
    public TreeNode buildTree(int[] inorder, int[] postorder) {

        if (inorder == null) {
            return null;
        }

        if (inorder.length == 1) {
            return new TreeNode(inorder[0]);
        }

        return build(inorder, postorder, new TreeNode(postorder[postorder.length - 1]), 0, inorder.length - 1, 0, postorder.length - 1);

    }

    private TreeNode build(int[] inorder, int[] postorder, TreeNode head, int firstI, int lastI, int firstP, int lastP) {

        int headIndex = 0;

        for (int i = firstI; i <= lastI; i++) {
            if (inorder[i] == head.val) {
                headIndex = i;
            }
        }

        //如果headIndex = firstI, 说明剩下节点都为右节点
        if (headIndex == firstI) {
            head.left = null;
        } else {
            //注意这里左边的中节点逻辑，如果当前节点有左节点，那么它的左节点是，先得到该节点左边节点的数量，再从后序遍历的数组中，找到对应数量的节点，
            //最后1个就是该节点的左子节点
            int leftCount = headIndex - firstI;
            head.left = new TreeNode(postorder[firstP + leftCount - 1]);
            build(inorder, postorder, head.left, firstI, headIndex - 1, firstP, firstP + leftCount - 1);
        }

        //如果headIndex = lastI,说明剩下节点都为左节点
        if (headIndex == lastI) {
            head.right = null;
        } else {
            head.right = new TreeNode(postorder[lastP - 1]);
            build(inorder, postorder, head.right, headIndex + 1, lastI, lastP - lastI + headIndex, lastP - 1);
        }

        return head;


    }

}
