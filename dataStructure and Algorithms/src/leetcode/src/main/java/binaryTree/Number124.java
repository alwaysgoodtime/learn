package leetcode.src.main.java.binaryTree;

/**
 * https://leetcode.cn/problems/binary-tree-maximum-path-sum/
 *
 * @author goodtime
 * @create 2023-12-06 22:12
 */
public class Number124 {
    public static void main(String[] args) {
        System.out.println("test");
    }
}

/**
 * 1.依次遍历每个节点，找到经过该节点的路径最大值
 * 2.经过该节点的路径最大值，等于以该节点为根节点，
 * 根节点（不包括根节点）到左叶子节点的最大值（正数，负数则忽略）与根节点（不包括根节点）到右叶子节点的最大值相加，再加上根节点的值
 */
class Solution124 {
    public int maxPathSum(TreeNode root) {

        if (root == null) {
            return 0;
        }

        return preOrder(root);

    }

    /**
     * 前序遍历树的每个节点
     */
    private int preOrder(TreeNode root) {

        int maxRootValue = getMax(root);
        int maxLeftValue = Integer.MIN_VALUE;
        int maxRightValue = Integer.MIN_VALUE;

        if (root.left != null) {
            maxLeftValue = preOrder(root.left);
        }

        if (root.right != null) {
            maxRightValue = preOrder(root.right);
        }

        maxRootValue = Math.max(maxLeftValue, maxRootValue);
        return Math.max(maxRootValue, maxRightValue);

    }

    /**
     * 找到经过该节点的最大路径和
     *
     * @param root
     * @return
     */
    private int getMax(TreeNode root) {

        int maxLeftValue = Integer.MIN_VALUE;
        int maxRightValue = Integer.MIN_VALUE;
        int max = root.val;

        if (root.left != null) {
            maxLeftValue = getSubMax(root.left, root.left.val);
        }

        if (root.right != null) {
            maxRightValue = getSubMax(root.right, root.right.val);
        }

        if (maxLeftValue > 0) {
            max += maxLeftValue;
        }

        if (maxRightValue > 0) {
            max += maxRightValue;
        }

        return max;

    }

    //获取以node为根节点，到叶子节点的最大路径和
    private int getSubMax(TreeNode node, int pathSum) {


        if (node.left == null && node.right == null) {
            return pathSum;
        }

        int maxLeftSum = Integer.MIN_VALUE;
        int maxRightSum = Integer.MIN_VALUE;


        if (node.left != null) {
            maxLeftSum = getSubMax(node.left, pathSum + node.left.val);
        }

        if (node.right != null) {
            maxRightSum = getSubMax(node.right, pathSum + node.right.val);
        }

        if (maxLeftSum <= pathSum && maxRightSum <= pathSum) {
            return pathSum;
        } else {
            return Math.max(maxLeftSum, maxRightSum);
        }

    }

    /**
     * 1.后序遍历每个节点，找到经过该节点的路径最大值（最大贡献值），以及该节点左半边最大值，与右半边最大值
     * 2.依次计算该节点左右两链路所能提供的最大单链路值，供其父节点调用
     * 3.最大贡献值中最大的那一个，就是结果
     */
    public int maxPathSumAdvanced(TreeNode root) {

        if (root == null) {
            return 0;
        }

        TreeNode treeNode = new TreeNode(Integer.MIN_VALUE);

        //使用treeNode，单纯只是因为int值没办法在递归中从头带到尾，并无其他特殊含义
        postOrderMax(root, treeNode);

        return treeNode.val;

    }


    private int postOrderMax(TreeNode root, TreeNode maxValue) {

        //左叶子链路最大路径和，<0则忽略
        int leftLineMaxValue = 0;
        //由叶子链路最大路径和，<0则忽略
        int rightLineMaxValue = 0;

        if (root.left != null) {
            leftLineMaxValue = Math.max(leftLineMaxValue, postOrderMax(root.left, maxValue));
        }

        if (root.right != null) {
            rightLineMaxValue = Math.max(rightLineMaxValue, postOrderMax(root.right, maxValue));
        }

        //maxGain的含义是：以当前节点为根节点，把其左右节点连同该节点加和，所能得到的最大值，该值与单链路所能提供的最大值不同
        //返回值是链路最大值，maxGain则存到了maxValue里
        int maxGain = root.val;
        maxGain += leftLineMaxValue;
        maxGain += rightLineMaxValue;

        if (maxGain > maxValue.val) {
            maxValue.val = maxGain;
        }

        int lineMax = Math.max(leftLineMaxValue, rightLineMaxValue);

        return Math.max(lineMax + root.val, 0);

    }

}
