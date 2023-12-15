package leetcode.src.main.java.divideAndRule;

/**
 * https://leetcode.cn/problems/convert-sorted-array-to-binary-search-tree/
 *
 * @author goodtime
 * @create 2023-12-08 11:08
 */
public class Number108 {
    public static void main(String[] args) {
        int[] nums = {-10, -3, 0, 5, 9};
        System.out.println(new Solution108().sortedArrayToBST(nums));
    }
}


/**
 * 分治 二叉搜索树是中序遍历满足递增顺序的树，高度平衡则是每个节点的左右两个子树的高度差的绝对值不超过 1
 *
 * 而原数组是有序数组，不妨将其从中间断成两支，左边就是左子树，右边就是右子树，这样持续下去即可
 */
class Solution108 {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 注意对middle与边界值进行判等处理
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {

        if (nums == null) {
            return null;
        }

        int middle = nums.length / 2;

        TreeNode head = new TreeNode(nums[middle]);

        if (middle != 0) {
            divide(nums, 0, middle - 1, head, true);
        }

        if (middle != nums.length - 1 ) {
            divide(nums, middle + 1, nums.length - 1, head, false);
        }

        return head;

    }

    private void divide(int[] nums, int l, int r, TreeNode head, boolean isLeft) {

        if (l == r) {
            if (isLeft) {
                head.left = new TreeNode(nums[l]);
            } else {
                head.right = new TreeNode(nums[l]);
            }
            return;
        }

        int middle = (l + r) / 2;

        TreeNode treeNode = new TreeNode(nums[middle]);
        if (isLeft) {
            head.left = treeNode;
        } else {
            head.right = treeNode;
        }

        if (middle != l) {
            divide(nums, l, middle - 1, treeNode, true);
        }

        if (middle != r) {
            divide(nums, middle + 1, r, treeNode, false);
        }

    }
}
