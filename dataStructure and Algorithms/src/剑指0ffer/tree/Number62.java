package 剑指0ffer.tree; /**
 * @author goodtime
 * @create 2023-03-28 01:09
 */

import java.util.ArrayList;

/**
 * JZ54 二叉搜索树的第k个节点
 * 给定一棵结点数为n 二叉搜索树，请找出其中的第 k 小的TreeNode结点值。
 * 1.返回第k小的节点值即可
 * 2.不能查找的情况，如二叉树为空，则返回-1，或者k大于n等等，也返回-1
 * 3.保证n个节点的值不一样
 *
 * @author goodtime
 * @create 2023-03-28 00:25
 */
public class Number62 {
    public static void main(String[] args) {
        TreeNode62 a = new TreeNode62(5);
        TreeNode62 b = new TreeNode62(3);
        TreeNode62 c = new TreeNode62(8);
        TreeNode62 d = new TreeNode62(1);
        TreeNode62 e = new TreeNode62(2);
        TreeNode62 f = new TreeNode62(7);
        a.left = b;
        a.right = c;
        b.left = d;
        d.right = e;
        c.left = f;
        Solution62 solution = new Solution62();
        System.out.println(solution.KthNode(a, 1));
    }


}


class TreeNode62 {
    int val;
    TreeNode62 left = null;
    TreeNode62 right = null;

    public TreeNode62(int val) {
        this.val = val;

    }
}

/**
 * 使用中序遍历，这里用了递归，二叉搜索树遍历出的集合就是有序的
 */
class Solution62 {

    public int KthNode(TreeNode62 proot, int k) {
        if (proot == null || k <= 0) {
            return -1;
        }

        //中序遍历得到列表
        ArrayList<Integer> result = middleOrderTraversal(proot);

        //返回第k个元素
        return k <= result.size() ? result.get(k - 1) : -1;
    }

    private ArrayList<Integer> middleOrderTraversal(TreeNode62 proot) {

        ArrayList<Integer> result = new ArrayList<>();

        if (proot.left != null) {
            result.addAll(middleOrderTraversal(proot.left));
        }

        result.add(proot.val);

        if (proot.right != null) {
            result.addAll(middleOrderTraversal(proot.right));
        }

        return result;

    }
}
