package leetcode.src.main.java.dp;

/**
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iii/
 *
 * @author goodtime
 * @create 2023-04-04 22:33
 */
public class Number123 {
    public static void main(String[] args) {
        System.out.println(new Solution123().maxProfit(new int[]{1,2,3,4,5}));
    }
}

/**
 * 买卖股票3
 * dp[0][0]含义：不操作
 * dp[0][1]含义：第一次持有
 * dp[0][2]含义：第一次不持有
 * dp[0][3]含义：第二次持有
 * dp[0][4]含义：第二次不持有
 * 对于一个元素，总共有五种状态，也需要这五种状态来完成整个状态转移
 * 递推公式：
 * dp[i][0] = dp[i-1][0];
 * dp[i][1] = max(dp[i-1][1], dp[i-1][0]-prices[i])
 * dp[i][2] = max(dp[i-1][2], dp[i-1][1]+prices[i])
 * dp[i][3] = max(dp[i-1][3], dp[i-1][2]-prices[i])
 * dp[i][4] = max(dp[i-1][4], dp[i-1][3]+prices[i])
 * 初始化：
 * dp[0][0] = 0;
 * dp[0][1] = -prices[0]
 * dp[0][2] = 0;
 * dp[0][3] = -prices[0]
 * dp[0][4] = 0;
 */
class Solution123 {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }

        int[][] dp = new int[prices.length][5];

        //初始化
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[0][2] = 0;
        dp[0][3] = -prices[0];
        dp[0][4] = 0;

        //递推
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = dp[i-1][0];
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0]-prices[i]);
            dp[i][2] = Math.max(dp[i-1][2], dp[i-1][1]+prices[i]);
            dp[i][3] = Math.max(dp[i-1][3], dp[i-1][2]-prices[i]);
            dp[i][4] = Math.max(dp[i-1][4], dp[i-1][3]+prices[i]);
        }

        return dp[prices.length-1][4];
    }
}