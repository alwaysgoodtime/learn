package leetcode.src.main.java.binaryTree;

/**
 * https://leetcode.cn/problems/minimum-absolute-difference-in-bst/
 * @author goodtime
 * @create 2023-12-08 06:30
 */
public class Number530 {
    public static void main(String[] args) {
        System.out.println("test");
    }
}

/**
 * 由于是二叉搜索树，其中序遍历就是从小到大排列，只要遍历一遍，找出差值最小的即可
 */
class Solution530 {
    public int getMinimumDifference(TreeNode root) {
        if (root == null) {
            return 0;
        }

        TreeNode minValue = new TreeNode(Integer.MAX_VALUE);
        TreeNode lastValue = new TreeNode(Integer.MAX_VALUE);

        inOrder(root, minValue, lastValue);

        return minValue.val;
    }

    /**
     * lastValue为当前节点所遍历的上一个数的值
     */
    private void inOrder(TreeNode treeNode, TreeNode minValue, TreeNode lastValue) {

        if (treeNode.left != null) {
            inOrder(treeNode.left, minValue, lastValue);
        }

        int diff = 0;

        if (lastValue.val != Integer.MAX_VALUE) {
            diff = treeNode.val - lastValue.val;
            if (diff < minValue.val) {
                minValue.val = diff;
            }
        }

        lastValue.val = treeNode.val;

        if (treeNode.right != null) {
            inOrder(treeNode.right, minValue, lastValue);
        }
    }

}
