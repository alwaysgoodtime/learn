package leetcode.src.main.java.dp;

/**
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/
 *
 * @author goodtime
 * @create 2023-04-04 20:24
 */
public class Number121 {
    public static void main(String[] args) {
        System.out.println(new Solution121().maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
    }
}

/**
 * 买卖股票
 *
 * 暴力解法：两层for循环，穷举每一天买和后面每一天买的最大利润
 *
 * 动态规划：dp[i][0]: [0,i]天里，持有股票花费剩余的最多现金
 * dp[i][1]: [0,i]天里，不持有股票得到的最多现金
 * 递推公式：dp[i][0] =  max(dp[i-1][0], -price[i])
 * dp[i][1] = max(dp[i-1][1], price + dp[i-1][0])
 * 初始化：dp[0][0] = -price[0] dp[0][1] = 0
 * 遍历顺序：从前到后
 */
class Solution121 {
    public int maxProfit(int[] prices) {

        if (prices == null || prices.length <= 1) {
            return 0;
        }

        int[][] dp = new int[prices.length][2];

        //初始化
        dp[0][0] = -prices[0];
        dp[0][1] = 0;

        //递推
        for (int i = 1; i < prices.length; i++) {
            int yesterdayHold = dp[i - 1][0];
            dp[i][0] = Math.max(yesterdayHold, -prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], prices[i] + yesterdayHold);
        }

        return dp[prices.length - 1][1];


    }
}
