package leetcode.src.main.java.binaryTree;

/**
 * https://leetcode.cn/problems/same-tree/
 * @author goodtime
 * @create 2023-12-05 16:34
 */
public class Number100 {

    public static void main(String[] args) {
        System.out.println("test");
    }

}

/**
 * 用相同的方式遍历两个树即可，注意不要忘了比较两个树每个节点的value
 */
class Solution100 {

    public boolean isSameTree(TreeNode p, TreeNode q) {

        if (p == null && q == null) {
            return true;
        }

        if (p == null || q == null) {
            return false;
        }

        return preOrder(p, q);

    }

    private boolean preOrder(TreeNode p, TreeNode q) {

        if (p.val != q.val) {
            return false;
        }

        if (p.left == null && p.right == null) {
            return q.left == null && q.right == null;
        }

        if (p.left == null && q.left != null) {
            return false;
        }

        if (p.right == null && q.right != null) {
            return false;
        }

        boolean leftSame = true;
        boolean rightSame = true;

        if (p.left != null) {

            if (q.left == null) {
                return false;
            }

            leftSame = preOrder(p.left, q.left);
        }


        if (p.right != null) {

            if (q.right == null) {
                return false;
            }

            rightSame = preOrder(p.right, q.right);
        }


        return leftSame && rightSame;


    }


}
