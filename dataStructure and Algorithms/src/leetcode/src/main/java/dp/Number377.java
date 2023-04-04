package leetcode.src.main.java.dp;

/**
 * https://leetcode.cn/problems/combination-sum-iv/
 *
 * @author goodtime
 * @create 2023-04-04 01:07
 */
public class Number377 {
    public static void main(String[] args) {
        System.out.println(new Solution377().combinationSum4(new int[]{1, 2, 3}, 4));
    }
}

/**
 * 排列问题的完全背包，注意遍历顺序必须是先背包后物品
 */
class Solution377 {
    public int combinationSum4(int[] nums, int target) {
        if (target < 0) {
            return 0;
        }

        if (target == 0) {
            return 1;
        }

        if (nums == null) {
            return 0;
        }


        int[] dp = new int[target + 1];

        //初始化
        dp[0] = 1;

        //递推
        for (int i = 0; i <= target; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] <= i) {
                    dp[i] += dp[i - nums[j]];
                }
            }
        }

        return dp[target];

    }
}