package leetcode.src.main.java.dp;

/**
 * https://leetcode.cn/problems/coin-change/
 *
 * @author goodtime
 * @create 2023-04-04 14:10
 */
public class Number322 {

    public static void main(String[] args) {
        System.out.println(new Solution322().coinChange(new int[]{1, 2, 5}, 11));
    }

}

/**
 * 完全背包
 * dp[i][j]含义：使用[0,i]个硬币的情况下，获得j的面额，至少需要的硬币数
 * 一维dp[j]含义：获得j的面额，至少需要的硬币数
 * 公式：dp[j] =  min(dp[j], dp[j-coins[i]]+1)
 * 注意：如果j-coins[i]==0，直接把dp[j]赋值1即可，这是一个特例，即 dp[j-coins[i]]==0，但此时dp[j-coins[i]]+1有效。
 */
class Solution322 {
    public int coinChange(int[] coins, int amount) {
        if (coins == null) {
            return -1;
        }
        if (amount < 0) {
            return -1;
        }
        if (amount == 0) {
            return 0;
        }

        int[] dp = new int[amount + 1];
        for (int i = 0; i < coins.length; i++) {//物品
            for (int j = coins[i]; j <= amount; j++) {//背包
                if (j == coins[i]) {
                    dp[j] = 1;
                    continue;
                }

                if (dp[j] == 0 && dp[j - coins[i]] != 0) {
                    dp[j] = dp[j - coins[i]] + 1;
                } else if (dp[j] != 0 && dp[j - coins[i]] != 0) {
                    dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
                }
            }
        }

        return dp[amount] != 0 ? dp[amount] : -1;
    }
}