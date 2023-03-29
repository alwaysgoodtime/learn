
/**
 * JZ84 二叉树中和为某一值的路径(三)
 * 给定一个二叉树root和一个整数值 sum ，求该树有多少路径的的节点值之和等于 sum 。
 * 1.该题路径定义不需要从根节点开始，也不需要在叶子节点结束，但是一定是从父亲节点往下到孩子节点
 * 2.总节点数目为n
 * 3.保证最后返回的路径个数在整形范围内
 *
 * @author goodtime
 * @create 2020-01-20 6:14 下午
 */
public class Number66 {
    public static void main(String[] args) {
        TreeNode66 a = new TreeNode66(1);
        TreeNode66 b = new TreeNode66(2);
        TreeNode66 c = new TreeNode66(1);
        TreeNode66 d = new TreeNode66(2);
        TreeNode66 e = new TreeNode66(1);
        TreeNode66 f = new TreeNode66(-1);
        a.left = b;
        a.right = c;
        b.left = d;
        b.right = e;
        d.left = f;
        Solution66s solution66 = new Solution66s();
        System.out.println(solution66.FindPath(a, 4));

    }
}


class TreeNode66 {
    int val = 0;
    TreeNode66 left = null;
    TreeNode66 right = null;

    public TreeNode66(int val) {
        this.val = val;

    }
}

//前序遍历（也就是二叉树的深度优先搜索）
class Solution66s {

    private int route;
    private int count;

    //遍历整棵树，让每个节点作为根节点，来找可能的情况
    public int FindPath(TreeNode66 root, int sum) {

        if (root == null) {
            return 0;
        }

        FindRootPath(root, sum);
        FindPath(root.left, sum);
        FindPath(root.right, sum);

        return route;
    }

    //从当前节点作为根节点时，所有总和可能为expectNumber的可能性
    public void FindRootPath(TreeNode66 root, int expectNumber) {

        if (root == null) {
            return;
        }

        count += root.val;

        //不需要到叶子节点才算总和
        if (count == expectNumber) {
            route++;
        }

        FindRootPath(root.left, expectNumber);
        FindRootPath(root.right, expectNumber);

        count -= root.val;
    }
}