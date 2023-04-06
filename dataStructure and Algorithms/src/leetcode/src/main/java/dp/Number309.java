package leetcode.src.main.java.dp;

/**
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-cooldown/
 *
 * @author goodtime
 * @create 2023-04-05 15:16
 */
public class Number309 {
    public static void main(String[] args) {
        System.out.println(new Solution309().maxProfit(new int[]{
                1,2,3,0,4
        }));

    }
}

/**
 * 买卖股票
 *
 * dp[i - 1][2]的含义为：在第i天处于冷冻期时，不持有的最大值
 *
 * dp[i][0]含义：持有
 * dp[i][1]含义：不持有
 * dp[i][2]含义：在第i天处于冷冻期时，不持有的最大值(相当于上一天的不持有，即今天不做任何操作)
 *
 */
class Solution309 {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }

        int[][] dp = new int[prices.length][3];

        //初始化
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        dp[0][2] = 0;

        for (int i = 1; i < prices.length; i++) {
            //第i天买入，是i-1天买入，或者i-1天是冷冻期，i天买入的最大值（i-1天卖出的话，i天是无法买入的）
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2] - prices[i]);
            //第i天卖出，是i-1天卖出，或者i-1天买入，i天卖出的最大值
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
            //第i天处于冷冻期，无法买卖，不持有的最大值就是i-1天不持有的金额。
            dp[i][2] = dp[i-1][1];

        }

        return dp[prices.length - 1][1];


    }
}
