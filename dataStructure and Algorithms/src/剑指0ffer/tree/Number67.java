package 剑指0ffer.tree;

/**
 * JZ68 二叉搜索树的最近公共祖先
 * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 * 1.对于该题的最近的公共祖先定义:对于有根树T的两个节点p、q，
 * 最近公共祖先LCA(T,p,q)表示一个节点x，满足x是p和q的祖先且x的深度尽可能大。在这里，一个节点也可以是它自己的祖先.
 * 2.二叉搜索树是若它的左子树不空，则左子树上所有节点的值均小于它的根节点的值；
 * 若它的右子树不空，则右子树上所有节点的值均大于它的根节点的值
 * 3.所有节点的值都是唯一的。
 * 4.p、q 为不同节点且均存在于给定的二叉搜索树中。
 *
 * @author goodtime
 * @create 2020-01-20 6:14 下午
 */
public class Number67 {
    public static void main(String[] args) {
        TreeNode67 a = new TreeNode67(1);
        TreeNode67 b = new TreeNode67(2);
        TreeNode67 c = new TreeNode67(1);
        TreeNode67 d = new TreeNode67(2);
        TreeNode67 e = new TreeNode67(1);
        TreeNode67 f = new TreeNode67(-1);
        a.left = b;
        a.right = c;
        b.left = d;
        b.right = e;
        d.left = f;
        Solution67s solution67 = new Solution67s();
        System.out.println(solution67.lowestCommonAncestor(a, 1, 2));

    }
}


class TreeNode67 {
    int val = 0;
    TreeNode67 left = null;
    TreeNode67 right = null;

    public TreeNode67(int val) {
        this.val = val;

    }
}

class Solution67s {


    //利用二叉搜索树的特性做递归，一个节点一个节点从上往下找
    //如果是最小公共节点，要么大节点在小节点上方，因而公共节点是大节点，要么大小节点位于公共节点两边
    public int lowestCommonAncestor(TreeNode67 root, int p, int q) {

        if (root == null) {
            return 0;
        }

        int tmp;
        if (p > q) {
            tmp = p;
            p = q;
            q = tmp;
        }

        int middleValue = root.val;

        //如果两个数，一个比中点小，一个比中点大或相等，那么这个点就是他们的最小公共节点
        if (p < middleValue && q >= middleValue) {
            return root.val;
        }

        //从根节点的左节点继续找最小公共节点
        if (q < middleValue) {
            return lowestCommonAncestor(root.left, p, q);
        }

        //从根节点的右节点继续找最小公共节点
        return lowestCommonAncestor(root.right, p, q);

    }

}