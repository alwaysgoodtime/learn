package leetcode.src.main.java.dp;

/**
 * @author goodtime
 * @create 2023-11-29 19:21
 */
public class Number64 {

    public static void main(String[] args) {

        int[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        System.out.println(new Solution64().minPathSum(grid));
    }
}


/**
 * dp[i][j] 下标含义：到坐标（i，j)的最短路径
 *
 * 初始化：dp[0][0]为该点的值
 *
 * 递推公式：dp[i][j] = Math.min(dp[i-1][j],dp[i][j-1])+ num[i][j]
 *
 * 遍历顺序:从左到右，从上到下
 */
class Solution64 {
    public int minPathSum(int[][] grid) {

        if (grid == null) {
            return 0;
        }

        int row = grid.length;
        int column = grid[0].length;

        int[][] dp = new int[row][column];

        dp[0][0] = grid[0][0];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (i == 0 && j != 0) {
                    dp[i][j] = dp[i][j - 1] + grid[i][j];
                }

                if (j == 0 && i != 0) {
                    dp[i][j] = dp[i - 1][j] + grid[i][j];
                }

                if (j != 0 && i != 0) {
                    dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j]) + grid[i][j];
                }
            }
        }

        return dp[row - 1][column - 1];


    }
}