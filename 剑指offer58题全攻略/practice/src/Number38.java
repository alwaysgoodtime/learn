import java.util.ArrayList;

/**
 * @author goodtime
 * @create 2020-01-23 6:07 下午
 */
public class Number38 {
    public static void main(String[] args) {
        Solution38 solution38 = new Solution38();
        TreeNode38 root = new TreeNode38(1);
        TreeNode38 d = new TreeNode38(2);
        TreeNode38 c = new TreeNode38(3);
        TreeNode38 b = new TreeNode38(4);
        TreeNode38 a = new TreeNode38(5);
        root.left = d;
        d.left = c;
        c.left = b;
        root.right = a;
        int i = solution38.TreeDepth(root);
        System.out.println(i);

    }
}

class TreeNode38 {
    int val = 0;
    TreeNode38 left = null;
    TreeNode38 right = null;

    public TreeNode38(int val) {
        this.val = val;
    }
}

//解题思路，关键是更高效地遍历整个二叉树，这里用层次遍历（广度遍历)，在动态数组中，多存一个层次的变量，从而得到层数。

class Solution38 {
    public int TreeDepth(TreeNode38 root) {
        if (root == null) {
            return 0;//程序健壮性
        }
        int current = 0;//当前层的最后一个节点下标
        int count = 1;//深度
        int next = 0;//节点总长度
        int i = 0;//节点下标
        ArrayList<TreeNode38> treeNode38s = new ArrayList<TreeNode38>();
        treeNode38s.add(root);
        TreeNode38 node = root;
        while (true) {//其实是三个指针，然后三个指针来回进行切换
            if (node == null) {
            } else {
                if (node.left != null) {
                    treeNode38s.add(node.left);
                    next++;
                }
                if (node.right != null) {
                    treeNode38s.add(node.right);
                    next++;
                }
            }

            if (current == i) {
                if (current < next) {
                    current = next;
                    count++;
                } else {
                    break;
                }
            }
            node = treeNode38s.get(++i);
        }
        return count;
    }
}




