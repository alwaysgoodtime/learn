package leetcode.src.main.java.dp;

/**
 * https://leetcode.cn/problems/longest-continuous-increasing-subsequence/
 *
 * @author goodtime
 * @create 2023-04-05 19:42
 */
public class Number674 {

    public static void main(String[] args) {
        System.out.println(new Solution674().findLengthOfLCIS(new int[]{1, 3, 5, 4, 7}));
    }

}

/**
 * 子序列问题
 * 可以和number300对照
 *
 * 第一种：
 *
 * dp[i]含义: [0,i]的字符串中最长连续递增序列
 * 递推公式：dp[i] = max(lastlength,dp[i-1])
 * 定义一个lastlength，如果当前字符串>前一个字符串，这个值+1，否则重置为1
 * 初始化：dp[0]=1
 *
 * 第二种：
 *
 * dp[i]含义:  以nums[i]为结尾的字符串最长连续递增序列
 * 递推公式：dp[i] =  dp[i-1] + 1 (如果 num[i] 大于 nums[i-1])
 * 初始化：dp[i]=1
 */
class Solution674 {
    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        if (nums.length == 1) {
            return 1;
        }

        int[] dp = new int[nums.length];

        //初始化
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
        }


        for (int i = 1; i < nums.length; i++) {

            if (nums[i] > nums[i - 1]) {
                dp[i] = dp[i - 1] + 1;
            }

        }

        int max = 0;

        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, dp[i]);
        }

        return max;

    }
}