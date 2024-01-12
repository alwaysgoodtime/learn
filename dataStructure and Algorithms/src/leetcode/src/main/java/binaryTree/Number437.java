package leetcode.src.main.java.binaryTree;

import java.util.HashMap;
import java.util.Map;

/**
 * @author goodtime
 * @create 2024-01-12 09:38
 */
public class Number437 {
    public static void main(String[] args) {
        TreeNode a = new TreeNode(1);
        TreeNode b = new TreeNode(2);
        TreeNode c = new TreeNode(3);
        TreeNode d = new TreeNode(4);
        TreeNode e = new TreeNode(5);
        a.right = b;
        b.right = c;
        c.right = d;
        d.right = e;
        System.out.println(new Solution437().pathSum(a, 3));

    }
}


class Solution437 {

    long targetValue;

    int count = 0;

    long sum = 0;

    //key为从根节点到当前节点父节点的前缀和，value为该前缀和出现的次数
    Map<Long, Integer> map = new HashMap<>();

    /**
     * 使用前缀和思想，参考
     * https://leetcode.cn/problems/path-sum-iii/solutions/1021490/gong-shui-san-xie-yi-ti-shuang-jie-dfs-q-usa7/?envType=study-plan-v2&envId=top-100-liked
     */
    public int pathSumPrefixSum(TreeNode root, int targetSum) {

        if (root == null) {
            return 0;
        }

        map.put(0L, 1);

        targetValue = targetSum;
        dfsPrefix(root, (long) root.val);
        return count;
    }

    private void dfsPrefix(TreeNode root, Long val) {

        if (map.containsKey(val - targetValue)) {
            count += map.get(val - targetValue);
        }

        map.merge(val, 1, Integer::sum);

        if (root.left != null) {
            dfsPrefix(root.left, val + root.left.val);
        }

        if (root.right != null) {
            dfsPrefix(root.right, val + root.right.val);
        }

        map.merge(val, -1, Integer::sum);
    }


    /**
     * 对每个节点进行深度遍历
     */
    public int pathSum(TreeNode root, int targetSum) {

        targetValue = targetSum;
        dfsAsRoot(root);
        return count;
    }

    /**
     * 前序遍历所有节点，并作为根节点
     */
    private void dfsAsRoot(TreeNode root) {
        if (root == null) {
            return;
        }

        dfs(root, true);
        dfsAsRoot(root.left);
        dfsAsRoot(root.right);
    }

    /**
     * 以当前节点为根节点，查看所有以此节点做开头的所有合法路径
     */
    private void dfs(TreeNode root, Boolean isRoot) {

        if (root == null) {
            return;
        }

        if (isRoot) {
            sum = root.val;
        } else {
            sum += root.val;
        }

        if (sum == targetValue) {
            count++;
        }

        dfs(root.left, false);
        dfs(root.right, false);
        sum -= root.val;
    }


}