package leetcode.src.main.java.dp;

/**
 * https://leetcode.cn/problems/longest-increasing-subsequence/
 *
 * @author goodtime
 * @create 2023-04-05 18:29
 */
public class Number300 {

    public static void main(String[] args) {
        System.out.println(new Solution300().lengthOfLIS(new int[]{
                10, 9, 2, 5, 3, 7, 101, 18
        }));
    }

}

/**
 *
 * 子序列问题
 *
 * dp[i]含义：以nums[i]结尾的最长递增子序列长度
 *
 * 递推公式：dp[i] = max(dp[j] + 1, dp[i]) (0<=j<i)
 *
 */
class Solution300 {
    public int lengthOfLIS(int[] nums) {

        if (nums == null || nums.length == 0) {
            return 0;
        }

        if (nums.length == 1) {
            return 1;
        }

        int[] dp = new int[nums.length];

        dp[0] = 1;


        //初始化
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
        }


        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        //最大值不是dp[nums.length-1]，因为最长递增子序列并不一定要包含最后一个元素，注意dp[i]的定义

        int max = 0;

        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, dp[i]);
        }

        return max;

    }
}