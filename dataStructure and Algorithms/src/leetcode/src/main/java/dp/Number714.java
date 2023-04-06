package leetcode.src.main.java.dp;

/**
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
 *
 * @author goodtime
 * @create 2023-04-05 17:25
 */
public class Number714 {
    public static void main(String[] args) {
        System.out.println(new Solution714().maxProfit(new int[]{
                1, 3, 2, 8, 4, 9
        }, 2));

    }
}

/**
 * 买卖股票
 * 多了小费fee，跟最佳时机2(即number122)是一致的，只是卖的时候要扣除手续费
 */
class Solution714 {
    public int maxProfit(int[] prices, int fee) {

        if (prices == null || prices.length <= 1) {
            return 0;
        }

        int[][] dp = new int[prices.length][2];

        //初始化
        dp[0][0] = -prices[0];
        dp[0][1] = 0;

        for (int i = 1; i < prices.length; i++) {

            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i] - fee);

        }

        return dp[prices.length - 1][1];


    }
}