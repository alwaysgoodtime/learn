package leetcode.src.main.java.dp;

/**
 * https://leetcode.cn/problems/house-robber-ii/
 *
 * @author goodtime
 * @create 2023-04-04 17:05
 */
public class Number213 {
    public static void main(String[] args) {
        System.out.println(new Solution213().rob(new int[]{2, 3, 2}));
    }
}

/**
 * 打家劫舍2
 *
 * dp[i]含义:打劫[0,i]所房屋所能得到的最大金额
 * dpNoFirst[i]含义：打劫[1,i]所房屋所能得到的最大金额
 * 递推公式：dp[i] = max(dp[i-2] + value ,dp[i-1])
 * dpNoFirst[i] = max(dpNoFirst[1,i-2] + value ,dpNoFirst[i-1])
 * 初始化：dp[0]= nums[0] dp[1]= max(nums[0],nums[1])
 * dpNoFirst[0] = 0 dpNoFirst[1] = nums[1]
 * 遍历顺序：从前往后遍历
 *
 * 如果dp[length -1]和dpNoFirst[Length-1]值相同，说明即使不打劫第一家，也得到了打家劫舍的最大金额，直接返回即可
 * 如果dp[length -1]和dpNoFirst[Length-1]值不同，说明在dp[length -1]中，要么同时打劫了第一家和最后一家，要么是打劫了第一家和其他组合，总之最大值里包含里第一家的值，才导致
 * dp和dpNoFirst的值不同。 题目要求不能同时打劫第一家和最后一家，所以打家劫舍最大值要么是dp[length-2] (即打劫第一家，不准打劫最后一家)，要么是dpNoFirst（即可以打劫最后一家，
 * 不准打劫第一家）
 */
class Solution213 {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        if (nums.length == 1) {
            return nums[0];
        }

        int length = nums.length;


        int[] dp = new int[length];
        int[] dpNoFirst = new int[length];

        //初始化
        dp[0] = nums[0];

        if (nums[1] > nums[0]) {
            dp[1] = nums[1];
        } else {
            dp[1] = nums[0];
        }


        //初始化
        dpNoFirst[0] = 0;
        dpNoFirst[1] = nums[1];


        //递推公式
        for (int i = 2; i < length; i++) {

            //推dpNoFirst
            int robNoFirst = dpNoFirst[i - 2] + nums[i];
            int noRobNoFirst = dpNoFirst[i - 1];

            dpNoFirst[i] = Math.max(robNoFirst, noRobNoFirst);

            //推dp
            int rob = dp[i - 2] + nums[i];
            int noRob = dp[i - 1];

            dp[i] = Math.max(rob, noRob);

        }

        if (dp[length - 1] == dpNoFirst[length - 1]) {
            return dp[length - 1];
        }

        return Math.max(dp[length - 2], dpNoFirst[length - 1]);
    }
}