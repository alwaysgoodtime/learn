/**
 * JZ86 在二叉树中找到两个节点的最近公共祖先
 * 给定一棵二叉树(保证非空)以及这棵树上的两个节点对应的val值 o1 和 o2，请找到 o1 和 o2 的最近公共祖先节点。
 * 注：本题保证二叉树中每个节点的val值均不相同。
 *
 * @author goodtime
 * @create 2020-01-20 6:14 下午
 */
public class Number68 {
    public static void main(String[] args) {
        TreeNode68 a = new TreeNode68(5);
        TreeNode68 b = new TreeNode68(14);
        TreeNode68 c = new TreeNode68(3);
        TreeNode68 d = new TreeNode68(15);
        TreeNode68 e = new TreeNode68(9);
        TreeNode68 f = new TreeNode68(7);
        a.left = b;
        b.left = c;
        c.left = d;
        d.left = e;
        d.right = f;
        Solution68 solution68 = new Solution68();
        System.out.println(solution68.lowestCommonAncestor(a, 9, 7));

    }
}


class TreeNode68 {
    int val = 0;
    TreeNode68 left = null;
    TreeNode68 right = null;

    public TreeNode68(int val) {
        this.val = val;

    }
}

class Solution68 {

    int result = -1;

    /**
     * 后序遍历 左 -> 右 -> 根  如果两个节点分属树的左右两边，那么最后会到一个节点，使得left和right的值都为正数，也即两者最小公共节点
     * 如果两个节点在同一条边上，那么最后会使得left或right其中一个值为1，且middleValue的值与o1或o2相等，也是两者最小公共节点
     */
    public int lowestCommonAncestor(TreeNode68 root, int o1, int o2) {

        //如果直接输出result，很难控制返回值，不如分拆方法，再添加一个全局变量存储找到的最小公共节点
        lowestCommonAncestorSub(root, o1, o2);

        return result;

    }

    public int lowestCommonAncestorSub(TreeNode68 root, int o1, int o2) {

        if (root == null) {
            return -1;
        }

        int left = lowestCommonAncestorSub(root.left, o1, o2);

        int right = lowestCommonAncestorSub(root.right, o1, o2);

        int middleValue = root.val;

        if (middleValue == o1 || middleValue == o2) {
            //如果两个节点在同一条边上，那么最后会使得left或right其中一个值为1，
            //且middleValue的值与o1或o2相等，这也是两者最小公共节点
            if (left >= 0 || right >= 0) {
                result = middleValue;
            }
            return middleValue;
        }

        if (left >= 0 && right >= 0) {
            result = middleValue;
        }

        //在找到公共节点后，这个返回值已经没有意义
        return left >= 0 ? left : right;

    }
}