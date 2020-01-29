import javax.swing.tree.TreeNode;

/**
 * @author goodtime
 * @create 2020-01-20 12:44 上午
 */
public class Number18 {
    private TreeNode3 treeNode5;

    public static void main(String[] args) {
        TreeNode3 treeNode1 = new TreeNode3(1);
        TreeNode3 treeNode2 = new TreeNode3(2);
        TreeNode3 treeNode3 = new TreeNode3(3);
        TreeNode3 treeNode4 = new TreeNode3(4);
        TreeNode3 treeNode5 = new TreeNode3(5);
        TreeNode3 treeNode6 = new TreeNode3(6);
        treeNode1.left = treeNode2;
        treeNode2.left = treeNode3;
        treeNode3.right = treeNode4;
        treeNode2.right = treeNode5;
        treeNode1.right = treeNode6;
        Solution18 solution18 = new Solution18();
        solution18.Mirror(treeNode1);

    }
}

class TreeNode3 {
    int val = 0;
    TreeNode3 left = null;
    TreeNode3 right = null;

    public TreeNode3(int val) {
        this.val = val;
    }
}
//前序遍历时，把节点的左右子节点互换即可
class Solution18 {
    public void Mirror(TreeNode3 root) {
        if(root == null){
            return;
        }
        TreeNode3 mirror = root.left;
        root.left = root.right;
        root.right = mirror;
        Mirror(root.left);
        Mirror(root.right);
    }
}
