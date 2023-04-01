package 剑指0ffer.tree;

/**
 * JZ28 对称的二叉树
 * @author goodtime
 * @create 2020-01-28 9:23 下午
 */
public class Number58 {
    public static void main(String[] args) {
        Solution58s solution58 = new Solution58s();
        boolean symmetrical = solution58.isSymmetrical(null);
        System.out.println(symmetrical);
    }
}

class TreeNode58 {
    int val = 0;
    TreeNode58 left = null;
    TreeNode58 right = null;
    public TreeNode58(int val) {
        this.val = val;

    }

}

//思路：分别遍历左右两个子树，左子树正常后序遍历，右子树按照右、左、中的顺序遍历，比较两者得到的数组是否相同。
//这种方法有bug，{5,5,5,5,#,#,5,5,#,5}，这个是层次遍历，#代表空缺，本来这棵树有可能为false，但我这种方法
//一做就是true
//class Solution58 {
//    private ArrayList<TreeNode58> a = new ArrayList<TreeNode58>();
//    private ArrayList<TreeNode58> b = new ArrayList<TreeNode58>();
//    boolean isSymmetrical(TreeNode58 pRoot) {
//        if (pRoot == null) {
//            return true;
//        }
//        postorderTraversal(pRoot.left);//遍历左子树
//        postTraversal(pRoot.right);//遍历右子树
//
//        if(a.size() != b.size()){
//            return false;
//        }
//        for (int i = 0; i < a.size(); i++) {
//            if(a.get(i).val == b.get(i).val){
//                continue;
//            }else {
//                return false;
//            }
//        }
//        return true;
//    }
//    private void postorderTraversal(TreeNode58 root) {
//        if (root != null) {
//            postorderTraversal(root.left);
//            postorderTraversal(root.right);
//            a.add(root);
//        }
//    }
//    private void postTraversal(TreeNode58 root) {
//        if (root != null) {
//            postorderTraversal(root.right);
//            postorderTraversal(root.left);
//            b.add(root);
//        }
//    }
//}


//思路：分别遍历左右两个子树，左子树中左右遍历，右子树按照中右左的顺序遍历，比较两者遍历得到的每个值是否相同。
//递归
class Solution58s {
    boolean isSymmetrical(TreeNode58 pRoot) {
        if (pRoot == null) {
            return true;
        }
        return traversal(pRoot.left,pRoot.right);
    }

    private boolean traversal(TreeNode58 left, TreeNode58 right) {

        if (left == null && right == null) {
            return true;
        } else if (left == null || right == null) {
            return false;
        } else if (left.val != right.val) {
            return false;
        } else {
            return traversal(left.left,right.right) && traversal(left.right, right.left);
        }
    }

}
