import javax.swing.tree.TreeNode;
import java.util.ArrayList;

/**
 * @author goodtime
 * @create 2020-01-23 11:32 下午
 */
public class Number39 {
    public static void main(String[] args) {
        Solution39plus solution39 = new Solution39plus();
        TreeNode39 root = new TreeNode39(1);
        TreeNode39 d = new TreeNode39(2);
        TreeNode39 c = new TreeNode39(3);
        TreeNode39 b = new TreeNode39(4);
        TreeNode39 a = new TreeNode39(5);
        root.left = d;
        d.left = c;
        c.left = b;
        root.right = a;
        boolean rt = solution39.IsBalanced_Solution(root);
        System.out.println(rt);
    }
}

class TreeNode39 {
    int val = 0;
    TreeNode39 left = null;
    TreeNode39 right = null;

    public TreeNode39(int val) {
        this.val = val;
    }
}

//假定传入的是二叉树，最简单判定其是否为平衡二叉树的方式是：从根节点开始遍历树中的每个节点（这里用的是层次遍历/广度遍历），
//然后判断每个节点左右节点高度差的绝对值是否不超过一。这样就能保证二叉树满足平衡二叉树的定义
//也就是依次判断左右节点的高度差，正好可以用上38题关于高度的计算。
//假设树有n个节点，该方法的算法时间复杂度为O(n的平方)

//class Solution39 {
//    public boolean IsBalanced_Solution(TreeNode39 root) {
//        if(root == null){
//            return true; //空树也为平衡二叉树
//        }
//        ArrayList<TreeNode39> nodes = new ArrayList<TreeNode39>();
//        nodes.add(root);
//
//        for(int i = 0,leftDepth = 0,rightDepth = 0;nodes.size()> i;i++){
//            TreeNode39 node = nodes.get(i);
//
//            if (node == null) {
//            } else {
//                if (node.left != null) {
//                    nodes.add(node.left);
//                    leftDepth = TreeDepth(node.left);
//                }
//                if (node.right != null) {
//                    nodes.add(node.right);
//                    rightDepth = TreeDepth(node.right);
//                }
//            }//遍历整个二叉树，把他们加到动态数组中
//            if(Math.abs(leftDepth-rightDepth) > 1){
//                return false;
//            }
//
//            //格式化左右子树的深度（这里视作高度），方便下次使用
//            leftDepth = 0;
//            rightDepth = 0;
//
//        }
//        return true;
//
//    }
//
//    public int TreeDepth(TreeNode39 root) {
//        if (root == null) {
//            return 0;//程序健壮性
//        }
//        int current = 0;//当前层的最后一个节点下标
//        int count = 1;//深度
//        int next = 0;//节点总长度
//        int i = 0;//节点下标
//        ArrayList<TreeNode39> treeNode38s = new ArrayList<TreeNode39>();
//        treeNode38s.add(root);
//        TreeNode39 node = root;
//        while (true) {
//            if (node == null) {
//            } else {
//                if (node.left != null) {
//                    treeNode38s.add(node.left);
//                    next++;
//                }
//                if (node.right != null) {
//                    treeNode38s.add(node.right);
//                    next++;
//                }
//            }
//
//            if (current == i) {
//                if (current < next) {
//                    current = next;
//                    count++;
//                } else {
//                    break;
//                }
//            }
//            node = treeNode38s.get(++i);
//        }
//        return count;
//    }
//}

//优化上面的解法，上面是从树的根节点开始判断左右子树高度是否平衡，如果平衡在分别判断其左右子树的左右子树平衡，指导最后的叶节点
//对于二叉平衡树来说，如果左右子树不平衡，它的父节点和祖先节点也不平衡。所以，可以采用深度遍历中的后序遍历，来遍历树中的每个节点。
//牛客网测速，平均值确实快了些，不过每次跑程序都不一样，奇怪
class Solution39plus {
    public boolean IsBalanced_Solution(TreeNode39 root) {
        if(root == null){
            return true; //空树也为平衡二叉树
        }
        return postorderersal(root);//后序遍历
    }

    private boolean postorderersal(TreeNode39 root) {
        if(root == null){
            return true;
        }
        if(postorderersal(root.left) && postorderersal(root.right)){
           return( Math.abs(TreeDepth(root.left) - TreeDepth(root.right)) <= 1);
        }

        return false;
    }


    public int TreeDepth(TreeNode39 root) {
        if (root == null) {
            return 0;//程序健壮性
        }
        int current = 0;//当前层的最后一个节点下标
        int count = 1;//深度
        int next = 0;//节点总长度
        int i = 0;//节点下标
        ArrayList<TreeNode39> treeNode38s = new ArrayList<TreeNode39>();
        treeNode38s.add(root);
        TreeNode39 node = root;
        while (true) {
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
