package leetcode.src.main.java.dp;

/**
 * https://leetcode.cn/problems/coin-change-ii/
 *
 * @author goodtime
 * @create 2023-04-03 23:04
 */
public class Number519 {
    public static void main(String[] args) {
        System.out.println(new Solution519().changes(5, new int[]{1, 2, 5}));
    }
}

/**
 * 完全背包
 * 二维数组解法，注意第0列的初始值为1
 * dp[i][j]的含义是：使用[0,i]的硬币，得到j的总和，可以有dp[i][j]种方法
 */
class Solution519 {
    public int change(int amount, int[] coins) {
        if (coins == null) {
            return 0;
        }
        if (amount < 0) {
            return 0;
        }
        if (amount == 0){
            return 1;
        }

        int[][] dp = new int[coins.length][amount+1];

        //第一列初始化amount为0时dp值为1
        for (int i = 0; i < coins.length; i++) {
            dp[i][0] = 1;
        }
        //第一行初始化
        for (int i = 1; i <= amount; i++) {
            if(i >= coins[0]){
                dp[0][i] = dp[0][i-coins[0]];
            }else {
                dp[0][i] = 0;
            }
        }

        //递推
        for (int i = 1; i < coins.length; i++) {//物品
            for (int j = 0; j <= amount; j++) {//背包

                int up = dp[i-1][j];

                int before = 0;
                if(j >= coins[i]){
                    before = dp[i][j-coins[i]];
                }

                dp[i][j] = up + before;
            }
        }

        return dp[coins.length -1][amount];

    }

    /**
     * 一维数组解法
     */
    public int changes(int amount, int[] coins) {
        if (coins == null) {
            return 0;
        }
        if (amount < 0) {
            return 0;
        }
        if (amount == 0){
            return 1;
        }

        int[] dp = new int[amount+1];

        //第一列初始化amount为0时dp值为1
        dp[0] = 1;

        //递推
        for (int i = 0; i < coins.length; i++) {//物品
            for (int j = 0; j <= amount; j++) {//背包

                int before = 0;
                if(j >= coins[i]){
                    before = dp[j-coins[i]];
                }

                dp[j] += before;
            }
        }

        return dp[amount];

    }
}

