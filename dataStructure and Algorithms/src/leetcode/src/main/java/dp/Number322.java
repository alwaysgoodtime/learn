package leetcode.src.main.java.dp;

/**
 * https://leetcode.cn/problems/coin-change/
 *
 * @author goodtime
 * @create 2023-04-04 14:10
 */
public class Number322 {

    public static void main(String[] args) {
        System.out.println(new Solution322().coinChange(new int[]{2, 5, 10, 1}, 27));
    }

}

/**
 * 完全背包
 * 二维dp[i][j]含义：使用[0,i]个硬币的情况下，获得j的面额，至少需要的硬币数
 * 初始化：第 1 列为 0，第 1 行，如果coin[0]为i，那么 j % i == 0 时， 值为 j / i ; 否则为 -1
 * 递推公式：如果都不为-1的情况下：dp[i][j] = Math.min(dp[i-1][j],dp[i][j-nums[i]] + 1);
 * 考虑dp[i-1][j]和dp[i][j-nums[i]]其中一种为-1的情况，一个为-1，则考察另一个的值。
 * 如果dp[i][j-nums[i]]也是-1，那么dp[i][j]也为-1。
 *
 * 遍历顺序：先从左到右，再从上到下，先遍历物品，后遍历背包
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

        int[][] dp = new int[coins.length][amount + 1];

        //初始化
        for (int i = 1; i <= amount; i++) {
            int mod = i % coins[0];
            if (mod == 0) {
                dp[0][i] = i / coins[0];
            } else {
                dp[0][i] = -1;
            }
        }

        for (int i = 1; i < coins.length; i++) {//物品
            for (int j = 1; j <= amount; j++) {//背包
                //递推公式
                if (j - coins[i] >= 0) {
                    if (dp[i - 1][j] != -1 && dp[i][j - coins[i]] != -1) {
                        dp[i][j] = Math.min(dp[i][j - coins[i]] + 1, dp[i - 1][j]);
                    } else if (dp[i][j - coins[i]] != -1) {
                        dp[i][j] = dp[i][j - coins[i]] + 1;
                    } else if (dp[i - 1][j] != -1) {
                        dp[i][j] = dp[i - 1][j];
                    } else {
                        dp[i][j] = -1;
                    }
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[coins.length - 1][amount];
    }

    /**
     * 一维dp[j]含义：获得j的面额，至少需要的硬币数，其中0代表不可获得这个面额
     * 公式：dp[j] =  min(dp[j], dp[j-coins[i]]+1)
     * 注意：如果j-coins[i]==0，直接把dp[j]赋值1即可，这是一个特例，即 dp[j-coins[i]]==0，但此时dp[j-coins[i]]+1有效。
     * 因为每个值只依赖上面与左边的值，故可压缩成滚动数组
     */
    public int coinChangeAdvanced(int[] coins, int amount) {
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