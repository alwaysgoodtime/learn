/**
 * JZ82 二叉树中和为某一值的路径(一)
 * 给定一个二叉树root和一个值 sum ，判断是否有从根节点到叶子节点的节点值之和等于 sum 的路径。
 * 1.该题路径定义为从树的根结点开始往下一直到叶子结点所经过的结点
 * 2.叶子节点是指没有子节点的节点
 * 3.路径只能从父节点到子节点，不能从子节点到父节点
 * 4.总节点数目为n
 *
 * @author goodtime
 * @create 2023-03-28 15:36
 */
public class Number63 {

    public static void main(String[] args) {

        TreeNode63 a = new TreeNode63(1);
        TreeNode63 b = new TreeNode63(2);
        TreeNode63 c = new TreeNode63(3);
        TreeNode63 d = new TreeNode63(4);
        TreeNode63 e = new TreeNode63(7);
        TreeNode63 f = new TreeNode63(5);
        a.left = b;
        a.right = c;
        b.left = d;
        d.right = e;
        c.left = f;
        Solution63 solution = new Solution63();
        System.out.println(solution.hasPathSum(a, 10));
    }


}


class TreeNode63 {
    int val;
    TreeNode63 left = null;
    TreeNode63 right = null;

    public TreeNode63(int val) {
        this.val = val;

    }
}


/**
 * 深度遍历
 */
class Solution63 {

    public boolean hasPathSum(TreeNode63 root, int sum) {

        if (root == null) {
            return false;
        }

        return depthTraversal(root, sum, 0);
    }

    private boolean depthTraversal(TreeNode63 node, int sum, int count) {
        if (node == null) {
            return false;
        }

        count += node.val;


        if (node.left == null && node.right == null) {
            return count == sum;
        } else {
            return depthTraversal(node.left, sum, count) || depthTraversal(node.right, sum, count);
        }
    }
}
