package leetcode.src.main.java.dp;

/**
 * https://leetcode.cn/problems/unique-paths-ii/
 *
 * @author goodtime
 * @create 2023-11-29 19:58
 */
public class Number63 {
    public static void main(String[] args) {

        int[][] grid = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        System.out.println(new Solution63().uniquePathsWithObstacles(grid));
    }
}

/**
 * dp[i][j] 下标含义：到坐标（i，j)的可能走法
 *
 * 初始化：dp[0][0]为1
 *
 * 递推公式：dp[i][j] = dp[i-1][j] + dp[i][j-1]
 *
 * 遍历顺序:从左到右，从上到下
 */
class Solution63 {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null) {
            return 0;
        }

        int row = obstacleGrid.length;
        int column = obstacleGrid[0].length;

        int[][] dp = new int[row][column];

        dp[0][0] = 1;

        for (int i = 0; i < obstacleGrid.length; i++) {
            for (int j = 0; j < obstacleGrid[0].length; j++) {

                if(obstacleGrid[i][j]  == 1){
                    dp[i][j] = 0;
                    continue;
                }


                if (i == 0 && j != 0) {
                    dp[i][j] = dp[i][j - 1];
                }

                if (j == 0 && i != 0) {
                    dp[i][j] = dp[i - 1][j];
                }

                if (j != 0 && i != 0) {
                    dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
                }
            }
        }

        return dp[row - 1][column - 1];
    }
}
