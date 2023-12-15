package leetcode.src.main.java.binaryTree;

/**
 * https://leetcode.cn/problems/count-complete-tree-nodes/
 *
 * @author goodtime
 * @create 2023-12-07 00:12
 */
public class Number222 {

    public static void main(String[] args) {
        System.out.println("test");
    }

}

/**
 * 遍历整个数当然可以算出树的节点个数，即使不是完全二叉树也行，时间复杂度为O(n)
 * 参考[Java/Python3/C++]二分查找 + 位运算：统计完全二叉树的节点个数【图解】 画图小匠
 */
class Solution222 {
    public int countNodes(TreeNode root) {

        if (root == null) {
            return 0;
        }

        return countNodes(root.left) + countNodes(root.right) + 1;
    }

    public int countNodesAdvanced(TreeNode root) {
        // 统计二叉树层数
        TreeNode node = root;
        int level = 0;
        while (node != null) {
            level++;
            node = node.left;
        }

        if (level <= 1){
            return level; // 层数小于等于1，直接返回节点个数
        }

        // 二分查找节点个数，左闭右开[left, right)
        int left = 1 << (level - 1);    // 左边界为最少个数
        int right = 1 << level;         // 右边界为最多个数+1
        int ans = left;     // 最终结果
        int mid;
        while (left < right) {
            mid = left + ((right - left) >> 1);
            if (check(root, mid, level)) {
                // 第mid个节点存在，说明最终结果一定大于等于mid，暂存mid并更新左边界以寻找编号更大的节点是否存在
                ans = mid;
                left = mid + 1;
            } else {
                // 第mid个节点不存在，说明结果小于mid，更新右边界查找编号更小的节点是否存在
                right = mid;
            }
        }
        return ans;
    }

    private boolean check(TreeNode root, int id, int level) {
        int bit = 1 << (level - 2);     // 初始化开始匹配的位
        while (bit > 0) {
            // 将节点编号的二进制看成从根节点到节点的路径编码，根据节点编号寻找节点
            if ((bit & id) == 0) {
                root = root.left;
            } else {
                root = root.right;
            }
            bit >>= 1;
        }
        return root != null;    // 节点不为空，说明编号为id的节点存在
    }
}