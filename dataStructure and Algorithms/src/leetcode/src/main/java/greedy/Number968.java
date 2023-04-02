package leetcode.src.main.java.greedy;

/**
 * https://leetcode.cn/problems/binary-tree-cameras/
 *
 * @author goodtime
 * @create 2023-04-02 00:19
 */
public class Number968 {
    public static void main(String[] args) {
        TreeNode968 root = new TreeNode968();
        TreeNode968 a = new TreeNode968();
        TreeNode968 b = new TreeNode968();
        TreeNode968 c = new TreeNode968();
        TreeNode968 d = new TreeNode968();
        TreeNode968 e = new TreeNode968();
        root.left = a;
        a.left = b;
        b.left = c;
        c.left = d;
        d.right = e;
        System.out.println(new Solution968().minCameraCover(root));
    }
}


class TreeNode968 {
    int val;
    TreeNode968 left;
    TreeNode968 right;

    TreeNode968() {
    }

    TreeNode968(int val) {
        this.val = val;
    }

    TreeNode968(int val, TreeNode968 left, TreeNode968 right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

/**
 * 整体规律是：叶子节点的父节点放一个摄像头，然后往上再隔两层放一个摄像头
 *
 * 一个节点有三种状态：无覆盖0、有覆盖1、有摄像头2
 *
 * null节点被视为有覆盖状态
 *
 * 需要后序遍历，根据左右孩子的节点状态来判断父节点的状态
 *
 * 如果左右孩子有一个无覆盖，那么父节点就得装摄像头
 *
 * 如果左右孩子有一个有摄像头，另一个至少也是有覆盖，那么父节点变成覆盖状态即可
 *
 * 如果左右孩子都是有覆盖，父节点状态变成无覆盖，等它的父亲装摄像头即可
 *
 * 有一种特殊情况：
 *
 * 如果父节点判定完是无覆盖情况，按理说是需要等上一层把它覆盖，但它没有上一层，所以这里要给它装一个摄像头
 *
 * 因为树的叶子节点最多，也考虑节省叶子节点
 */
class Solution968 {

    int count = 0;

    public int minCameraCover(TreeNode968 root) {
        if (root == null) {
            return 0;
        }

        if (root.left == null && root.right == null) {
            return 1;
        }

        int rootStatus = postTraversal(root);

        if (rootStatus == 0) {
            count++;
        }

        return count;

    }

    //后续遍历树
    private int postTraversal(TreeNode968 node) {

        //默认是有覆盖
        int leftNodeStatus = 1;
        int rightNodeStatus = 1;


        if (node.left != null) {
            leftNodeStatus = postTraversal(node.left);
        }

        if (node.right != null) {
            rightNodeStatus = postTraversal(node.right);
        }


        if (leftNodeStatus == 0 || rightNodeStatus == 0) {
            count++;
            return 2;
        }

        if (leftNodeStatus == 2 || rightNodeStatus == 2) {
            return 1;
        }

        return 0;

    }

}