package leetcode.src.main.java.binaryTree;

/**
 * https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/description/
 *
 * @author goodtime
 * @create 2023-12-07 00:39
 */
public class Number236 {

    public static void main(String[] args) {
        System.out.println("test");
    }
}

/**
 * 其实是贪心算法+后序遍历，如果有个根节点，它的左边或右边有p或者q，就立即返回这个节点，直到有左右两边都不为null的，
 * 那说明这个节点就是最小公共父节点，如果到了根节点，还是只有1边有值，那说明左边这个节点后面既有p又有q，只能没有把两者都"挖掘"出来而已
 */
class Solution236 {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if (root == null) {
            return null;
        }

        if (root == p || root == q) {
            return root;
        }


        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) {
            return root;
        } else if (left == null) {
            return right;
        } else {
            return left;
        }

    }


}