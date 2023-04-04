package leetcode.src.main.java.dp;

/**
 * https://leetcode.cn/problems/perfect-squares/
 *
 * @author goodtime
 * @creat 2023-04-04 15:15
 */
public class Number279 {
    public static void main(String[] args) {
        System.out.println(new Solution279().numSquares(800));
    }

}

/**
 * 完全背包（和number322基本一致）
 * 一维dp[j]含义：获得j的和，至少需要的完全平方数个数
 */
class Solution279 {
    public int numSquares(int n) {

        if (n == 1) {
            return 1;
        }

        //max为所需要考虑的最大完全平方数的开方数（如果n为9，则max为3）
        int max = (int) Math.sqrt(n);

        int[] dp = new int[n + 1];

        //初始化
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
        }


        for (int i = 1; i <= max; i++) {//物品
            for (int j = i * i; j <= n; j++) {//背包

                dp[j] = Math.min(dp[j], dp[j - i * i] + 1);

            }
        }

        return dp[n];


    }
}