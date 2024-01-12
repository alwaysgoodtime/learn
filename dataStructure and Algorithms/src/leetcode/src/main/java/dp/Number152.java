package leetcode.src.main.java.dp;

/**
 * @author goodtime
 * @create 2024-01-11 23:31
 */
public class Number152 {
    public static void main(String[] args) {
        System.out.println("test");
    }
}

/**
 * 注意考虑[-2,3,-4]的情况,所以要维护一个dpMin
 *
 * dpMax[i]含义：以[i]为结尾的连续子数组最大乘积
 * dpMin[i]含义：以[i]为结尾的连续子数组最小乘积
 *
 * 初始化:dpMax[0] = i dpMin[0]=i
 *
 *
 * 递推公式：dpMax[j] = Math.max(dpMax[j-1] * j , Math.max(j,dpMin[j-1] * j);
 * dpMin[j] = Math.min(dpMin[j-1] * j, Math.min(j, dpMax[j-1] * j));
 *
 *
 * 递推顺序：从左到右
 */
class Solution152 {
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int[] dpMax = new int[nums.length];
        int[] dpMin = new int[nums.length];

        dpMax[0] = nums[0];
        dpMin[0] = nums[0];

        int maxProduct = dpMax[0];

        for (int i = 1; i < nums.length; i++) {
            dpMax[i] = Math.max(dpMax[i - 1] * nums[i], Math.max(dpMin[i - 1] * nums[i], nums[i]));
            dpMin[i] = Math.min(dpMin[i - 1] * nums[i], Math.min(nums[i], dpMax[i - 1] * nums[i]));
            maxProduct = Math.max(maxProduct, dpMax[i]);
        }

        return maxProduct;


    }
}