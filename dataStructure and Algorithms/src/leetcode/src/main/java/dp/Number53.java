package leetcode.src.main.java.dp;

/**
 * https://leetcode.cn/problems/maximum-subarray/
 *
 * @author goodtime
 * @create 2023-04-06 04:41
 */
public class Number53 {
    public static void main(String[] args) {
        System.out.println(new Solution53().maxSubArray(new int[]{
                -2, 1, -3, 4, -1, 2, 1, -5, 4
        }));
    }
}

/**
 * 动规版本，本题也有贪心版本
 *
 * dp[i]含义：以nums[i]结尾的最大子树组和
 *
 * 递推公式：dp[i] = max(nums[i], nums[i]+dp[i-1])}
 *
 * 初始化: dp[0] = nums[0]
 *
 * 遍历顺序: 从前到后
 */
class Solution53 {
    public int maxSubArray(int[] nums) {

        if (nums == null || nums.length == 0) {
            return 0;
        }

        int[] dp = new int[nums.length];

        //初始化
        dp[0] = nums[0];

        int result = nums[0];

        //递推
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            result = Math.max(dp[i], result);
        }

        return result;
    }
}