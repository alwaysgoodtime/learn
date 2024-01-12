package leetcode.src.main.java.dp;

/**
 * https://leetcode.cn/problems/unique-paths/description/
 *
 * @author goodtime
 * @create 2024-01-11 21:36
 */
public class Number62 {
    public static void main(String[] args) {
        System.out.println("test");
    }
}

/**
 * dp[i][j]含义 机器人走到[i][j]处有多少种走法
 *
 * 初始化：dp[0][j] = 1 dp[j][0] = 1
 *
 * 递推公式：dp[i][j] = dp[i-1][j] + dp[i][j-1]
 *
 * 遍历顺序：从上到下，从左到右
 */
class Solution62 {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }

        for (int i = 1; i < m; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[m - 1][n - 1];
    }


}
