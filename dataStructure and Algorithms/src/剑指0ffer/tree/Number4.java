package 剑指0ffer.tree;

/**
 * JZ7 重建二叉树
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
 * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
 *
 * @author goodtime
 * @create 2020-01-18 12:16 上午
 */
public class Number4 {
    public static void main(String[] args) {
        Solution4 solution = new Solution4();
        int a[] = {1, 2, 4, 7, 3, 5, 6, 8};
        int b[] = {4, 7, 2, 1, 5, 3, 8, 6};

        int c[] = {1, 2, 3, 4};
        int d[] = {1, 2, 3, 4};

        TreeNode4 treeNod1 = solution.reConstructBinaryTree(c, d);
        System.out.println(treeNod1);
    }
}


class TreeNode4 {
    int val;
    TreeNode4 left;
    TreeNode4 right;

    TreeNode4(int x) {
        val = x;
    }
}


// 前序遍历的每个子集合都是前序遍历，中序遍历的每个子集合也都是中序遍历，所以可以用递归
class Solution4 {
    public TreeNode4 reConstructBinaryTree(int[] pre, int[] vin) {

        if (pre.length == 0) {
            return null;
        }

        return reConstructBinaryTree(pre, vin, new TreeNode4(pre[0]));

    }

    TreeNode4 reConstructBinaryTree(int[] pre, int[] vin, TreeNode4 middleNode) {

        if (pre.length == 1) {
            return middleNode;
        }

        int middle = pre[0];

        //中间节点有左节点
        if (vin[0] != middle) {

            //重建左节点
            //左节点，就是前序遍历的后一个节点
            middleNode.left = new TreeNode4(pre[1]);
            int[] leftVin = getLeftByMiddleNode(vin, pre[0]);
            reConstructBinaryTree(getLeftByLength(pre, leftVin.length), leftVin, middleNode.left);

        }

        //中间节点有右节点
        if (vin[vin.length - 1] != middle) {

            //重建右节点
            int[] rightVin = getRightByMiddleNode(vin, pre[0]);
            //右节点，就是前序遍历除去左节点和中间节点的第一个节点
            middleNode.right = new TreeNode4(pre[pre.length - rightVin.length]);
            reConstructBinaryTree(getRightByLength(pre, rightVin.length), rightVin, middleNode.right);

        }


        return middleNode;

    }


    private int[] getLeftByMiddleNode(int[] vin, int middle) {

        int index = 0;

        for (int j = 0; j < vin.length; j++) {
            if (vin[j] == middle) {
                index = j;
                break;
            }
        }


        int[] leftVin = new int[index];
        System.arraycopy(vin, 0, leftVin, 0, index);

        return leftVin;

    }

    private int[] getLeftByLength(int[] pre, int length) {

        int[] leftPre = new int[length];
        //跳过第一个中间节点来复制
        System.arraycopy(pre, 1, leftPre, 0, length);

        return leftPre;

    }

    private int[] getRightByLength(int[] pre, int length) {

        int[] rightPre = new int[length];

        System.arraycopy(pre, pre.length - length, rightPre, 0, pre.length - (pre.length - length));

        return rightPre;
    }

    private int[] getRightByMiddleNode(int[] vin, int middle) {

        int index = 0;


        for (int j = 0; j < vin.length; j++) {
            if (vin[j] == middle) {
                index = j;
                break;
            }
        }


        int[] right = new int[vin.length - index - 1];

        for (int j = index + 1; j < vin.length; j++) {
            right[j - index - 1] = vin[j];
        }

        return right;
    }
}

