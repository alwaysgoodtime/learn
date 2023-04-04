package leetcode.src.main.java.dp;

/**
 * @author goodtime
 * @create 2023-04-03 16:42
 */
public class Number494 {
    public static void main(String[] args) {
        System.out.println(new Solution494().findTargetSumWays(new int[]{1}, 1));
    }
}

/**
 * 暴力解法是回溯
 * 用背包解题的关键：把所有数分成两堆，并把两堆数的差值视为target，这样，第一堆减第二堆的差值就是target
 * 假设总和小的为第一堆
 * 每个数的价值和重量都视为它本身
 * dp[i][j]的含义变成了，任取前i个物品，装满容量为j的容器，有多少种方法
 * 递推公式：dp[i][j] = dp[i-1][j] + dp[i-1][j-num[i]]
 * dp[i-1][j]表示，如果不放第i个物品，有几种方法能把dp[j]容器装满
 * dp[i-1][j-num[i]]表示，假设第i个物品重量小于j，那么在放第i个物品的情况下，之前有几种情况能放满容量为j的容器
 *
 * 压缩成一维数组：dp[j] = dp[j] + dp[j-num[i]]
 */
class Solution494 {
    public int findTargetSumWays(int[] nums, int target) {
        if (nums == null) {
            return 0;
        }
        if (nums.length == 1) {
            if (nums[0] == target || nums[0] == -target) {
                return 1;
            } else {
                return 0;
            }
        }

        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }

        if (sum < Math.abs(target)) {
            return 0;
        }

        if ((sum - target) % 2 != 0) {
            return 0;
        }

        //找和小的堆，dp的长度会短一点，节省空间
        int heapSum = (sum - Math.abs(target)) / 2;

        int[] dp = new int[heapSum + 1];

        //初始化 放满容量0的容器，只要不放第一个物品就行，所以将其初始化为1
        dp[0] = 1;


        for (int i = 0; i < nums.length; i++) {//物品
            for (int j = heapSum; j >= nums[i]; j--) {//重量，每个物品的重量为它自身
                dp[j] = dp[j] + dp[j - nums[i]];
            }
        }

        return dp[heapSum];

    }
}