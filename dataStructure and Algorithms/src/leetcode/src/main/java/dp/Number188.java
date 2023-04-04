package leetcode.src.main.java.dp;

/**
 * @author goodtime
 * @create 2023-04-05 00:21
 */
public class Number188 {

    public static void main(String[] args) {
        System.out.println(new Solution188().maxProfit(2, new int[]{
                2, 4, 1
        }));
    }

}

/**
 * 本题是123的加强版，只是把允许两次买卖，变成了允许多次买卖
 */
class Solution188 {
    public int maxProfit(int k, int[] prices) {

        if (prices == null || prices.length <= 1) {
            return 0;
        }

        if (k <= 0) {
            return 0;
        }

        int[][] dp = new int[prices.length][2 * k + 1];

        //初始化
        for (int i = 0; i < 2 * k + 1; i++) {
            if (i % 2 == 0) {
                dp[0][i] = 0;
            } else {
                dp[0][i] = -prices[0];
            }
        }

        //递推
        for (int i = 1; i < prices.length; i++) {

            for (int j = 1; j < 2 * k + 1; j++) {

                if (j % 2 != 0) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1] - prices[i]);
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1] + prices[i]);
                }
            }


        }

        return dp[prices.length - 1][2 * k];


    }
}