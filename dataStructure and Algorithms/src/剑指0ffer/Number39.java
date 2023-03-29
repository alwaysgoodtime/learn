import java.util.ArrayList;

/**
 * JZ79 判断是不是平衡二叉树
 * 输入一棵节点数为 n 二叉树，判断该二叉树是否是平衡二叉树。
 * 在这里，我们只需要考虑其平衡性，不需要考虑其是不是排序二叉树
 * 平衡二叉树（Balanced Binary Tree），具有以下性质：它是一棵空树或它的左右两个子树的高度差的绝对值不超过1，并且左右两个子树都是一棵平衡二叉树。
 *
 * @author goodtime
 * @create 2020-01-23 11:32 下午
 */
public class Number39 {
    public static void main(String[] args) {
        Solution39s solution39 = new Solution39s();
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


//优化上面的解法，上面是从树的根节点开始判断左右子树高度是否平衡，如果平衡在分别判断其左右子树的左右子树平衡，指导最后的叶节点
//对于二叉平衡树来说，如果左右子树不平衡，它的父节点和祖先节点也不平衡。所以，可以采用深度遍历中的后序遍历，来遍历树中的每个节点。
//牛客网测速，平均值确实快了些，不过每次跑程序都不一样，奇怪
class Solution39plus {
    public boolean IsBalanced_Solution(TreeNode39 root) {
        if (root == null) {
            return true; //空树也为平衡二叉树
        }
        return postorderersal(root);//后序遍历
    }

    private boolean postorderersal(TreeNode39 root) {
        if (root == null) {
            return true;
        }
        if (postorderersal(root.left) && postorderersal(root.right)) {
            return (Math.abs(TreeDepth(root.left) - TreeDepth(root.right)) <= 1);
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


//平衡二叉树的子树也必须是平衡二叉树，所以需要判断完大树，再分别判断其左右子树是否为平衡二叉树，时间复杂度高
class Solution39s {

    public boolean IsBalanced_Solution(TreeNode39 root) {
        if (root == null) {
            return true; //空树也为平衡二叉树
        }

        int left = getDepth(root.left, 0);

        int right = getDepth(root.right, 0);

        if (Math.abs(left - right) > 1) {
            return false;
        }

        return IsBalanced_Solution(root.left) && IsBalanced_Solution(root.right);

    }

    //深度遍历
    private int getDepth(TreeNode39 treeNode, int depth) {

        if (treeNode == null) {
            return depth;
        }

        depth++;

        if (treeNode.left != null && treeNode.right == null) {
            return getDepth(treeNode.left, depth);
        } else if (treeNode.left == null && treeNode.right != null) {
            return getDepth(treeNode.right, depth);
        } else if (treeNode.left != null && treeNode.right != null) {

            int leftDepth = getDepth(treeNode.left, depth);
            int rightDepth = getDepth(treeNode.right, depth);
            return leftDepth >= rightDepth ? leftDepth : rightDepth;
        } else {
            return depth;
        }
    }
}

//后序遍历二叉树，遍历时顺手判断每个节点左右子树的高度相差是否为1，复杂度为n
class Solution39ss {

    public boolean IsBalanced_Solution(TreeNode39 root) {
        return postTraversal(root, 0) != -1;
    }

    private int postTraversal(TreeNode39 root, int depth) {


        if (root == null) {
            return depth;
        }

        depth++;

        //左子树的深度
        int leftDepth = postTraversal(root.left, depth);

        if (leftDepth == -1) {
            return -1;
        }

        //右子树的深度
        int rightDepth = postTraversal(root.right, depth);

        if (rightDepth == -1) {
            return -1;
        }

        if (Math.abs(leftDepth - rightDepth) > 1) {
            return -1;
        }

        return Math.max(leftDepth, rightDepth);
    }
}
