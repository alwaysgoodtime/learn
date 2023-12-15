package leetcode.src.main.java.dp;

/**
 * https://leetcode.cn/problems/maximal-square/
 *
 * @author goodtime
 * @create 2023-11-30 20:24
 */
public class Number221 {
    public static void main(String[] args) {
        char[][] matrix = {{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '0'}};
        System.out.println(new Solution221().maximalSquare(matrix));
    }
}

/**
 * 核心原理：如果 dp[i-1][j]、dp[i][j-1]、dp[i-1][j-1]值中都不为0，那么dp[i][j]为（其中最小值的平方根+1）后的平方数
 *
 * dp[i][j]含义：以[i][j]方块为正方形右下角一块的正方形最大面积
 *
 * 初始化：设一空行空列，其中值都为0，省去初始化
 *
 * 递推公式：如果[i][j]=0,则dp[i][j]=0;
 * 如果[i][j]!=0
 * 如果 dp[i-1][j]、dp[i][j-1]、dp[i-1][j-1]值中都不为0，那么dp[i][j]为（其中最小值的平方根+1）后的平方数
 * 如果 dp[i-1][j]、dp[i][j-1]、dp[i-1][j-1]值有1个为0，dp[i][j]=1
 *
 * 遍历顺序：从左到右，从上到下
 */
class Solution221 {
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }

        int row = matrix.length;
        int column = matrix[0].length;

        int[][] dp = new int[row + 1][column + 1];
        int maximalSquare = 0;

        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= column; j++) {
                if (matrix[i - 1][j - 1] == '0') {
                    dp[i][j] = 0;
                    continue;
                }

                int min = Math.min(dp[i - 1][j], dp[i][j - 1]);
                min = Math.min(min, dp[i - 1][j - 1]);

                if (min == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = (int)Math.pow((Math.sqrt(min) + 1), 2);
                }

                if (maximalSquare < dp[i][j]) {
                    maximalSquare = dp[i][j];
                }
            }
        }

        return maximalSquare;

    }
}
