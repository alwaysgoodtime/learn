package leetcode.src.main.java.dp;


/**
 * https://leetcode.cn/problems/house-robber-iii/
 *
 * @author goodtime
 * @create 2023-04-04 17:59
 */
public class Number337 {
    public static void main(String[] args) {
        TreeNode337 a = new TreeNode337(3);
        TreeNode337 b = new TreeNode337(2);
        TreeNode337 c = new TreeNode337(3);
        TreeNode337 d = new TreeNode337(3);
        TreeNode337 e = new TreeNode337(1);
        a.left = b;
        a.right = c;
        b.right = d;
        c.right = e;
        System.out.println(new Solution337().rob(a));
    }

}

class TreeNode337 {
    int val;
    TreeNode337 left;
    TreeNode337 right;

    TreeNode337() {
    }

    TreeNode337(int val) {
        this.val = val;
    }

    TreeNode337(int val, TreeNode337 left, TreeNode337 right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

/**
 * 对于树的问题，无论是前中后序，还是层序遍历，肯定是要遍历，问题最后会变成对单节点是否要打劫的问题。
 *
 * 节点有两个状态：没被打劫，被打劫。null节点视为没被打劫
 *
 * 每个节点的val，表示以该节点为根节点的树所能打劫到的最大金额。
 *
 * 对于一个节点是否要打劫，取决于其子节点的打劫情况。
 * 1. 如果两个子节点都没有被打劫，那么就打劫这个节点。
 * 2. 如果至少有一个子节点被打劫，按如下步骤计算：
 * 	1. 左子树的打劫金额+右子树的打劫金额（视为打劫左树+打劫右树+不打劫当前节点）
 * 	2. 左子节点所有孩子的金额（即不偷左子节点）+ 右子节点所有孩子的最大金额（即不偷右子节点）+ 当前节点的金额。
 * 	3. 从1和2中选出最大值，来判定当前节点是否要打劫。
 */
class Solution337 {

    public int rob(TreeNode337 root) {
        postTraversal(root);
        return root.val;
    }

    private boolean postTraversal(TreeNode337 root) {

        if (root == null) {
            return false;
        }

        boolean leftStatus;//false代表未打劫,true代表打劫
        boolean rightStatus;

        leftStatus = postTraversal(root.left);
        rightStatus = postTraversal(root.right);

        if (root.left == null && root.right == null) {
            //左右节点都为null，子树最大值就是节点值本身，且打劫该节点
            return true;
        } else if (root.left == null) {
            //左子节点为空，右子节点如果未被打劫，就打劫该节点
            if (!rightStatus) {
                root.val = root.val + root.right.val;
                return true;
            } else {
                int noRob = root.right.val;
                int rob = root.val + getAllSon(root.right);
                root.val = Math.max(noRob, rob);
                return noRob < rob;
            }
        } else if (root.right == null) {
            //右子节点为空，左子节点如果未被打劫，就打劫该节点
            if (!leftStatus) {
                root.val = root.val + root.left.val;
                return true;
            } else {
                int noRob = root.left.val;
                int rob = root.val + getAllSon(root.left);
                root.val = Math.max(noRob, rob);
                return noRob < rob;
            }
        } else {

            //左右子节点都不为空
            int leftNodeRob = root.left.val;
            int leftNodeNoRob = getAllSon(root.left);
            int rightNodeRob = root.right.val;
            int rightNodeNoRob = getAllSon(root.right);

            int noRob = leftNodeRob + rightNodeRob;
            int rob = leftNodeNoRob + rightNodeNoRob + root.val;
            if (noRob >= rob) {
                root.val = noRob;
                return false;
            } else {
                root.val = rob;
                return true;
            }
        }
    }

    private int getAllSon(TreeNode337 node) {

        if (node == null) {
            return 0;
        }

        if (node.left != null && node.right != null) {
            return  node.left.val + node.right.val;
        } else if (node.left == null && node.right != null) {
            return node.right.val;
        } else if (node.left != null) {
            return node.left.val;
        } else {
            return 0;
        }
    }
}