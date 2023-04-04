package leetcode.src.main.java.dp;

/**
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/
 *
 * @author goodtime
 * @create 2023-04-04 22:12
 */
public class Number122 {

    public static void main(String[] args) {
        System.out.println(new Solution122().maxProfit(new int[]{
                7, 1, 5, 3, 6, 4
        }));
    }

}

/**
 * 买卖股票2
 * 允许多次买卖，相当于如果卖的赚了钱，就一直留到下次持有时再扣
 */
class Solution122 {
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
            //昨天之前持有的现金与股票 与 昨天持有的现金卖完股票后今天再买股票剩下的现金 的最大值
            dp[i][0] = Math.max(dp[i-1][1] - prices[i], dp[i-1][0]);
            //昨天之前卖完股票后持有的现金 与 以今天股价卖完股票后持有的现金 的最大值
            dp[i][1] = Math.max(prices[i] + dp[i][0], dp[i-1][1]);
        }

        return dp[prices.length-1][1];

    }
}
