package leetcode.src.main.java.graph;

/**
 * https://leetcode.cn/problems/island-perimeter/
 * @author goodtime
 * @create 2024-01-01 13:44
 */
public class Number463 {
    public static void main(String[] args) {
        int[][] grid = {{0, 1, 0, 0}, {1, 1, 1, 0}, {0, 1, 0, 0}, {1, 1, 0, 0}};
        System.out.println(new Solution463().islandPerimeter(grid));
    }
}

/**
 * 岛周长问题，dfs
 * 核心是要想明白如何把周长计算简化为dfs遍历
 */
class Solution463 {
    public int islandPerimeter(int[][] grid) {
        if (grid == null) {
            return 0;
        }

        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    return dfs(grid, i, j);
                }
            }
        }

        return 0;
    }

    private int dfs(int[][] grid, int i, int j) {

        if (!inArea(grid, i, j)) {
            return 1;
        }

        if (grid[i][j] == 0) {
            return 1;
        } else if (grid[i][j] == 2) {
            return 0;
        } else {
            //染色
            grid[i][j] = 2;
            return dfs(grid, i + 1, j) + dfs(grid, i, j + 1) + dfs(grid, i - 1, j) + dfs(grid, i, j - 1);
        }
    }

    private boolean inArea(int[][] grid, int i, int j) {

        if (i < 0 || i >= grid.length) {
            return false;
        }

        if (j < 0 || j >= grid[0].length) {
            return false;
        }

        return true;
    }


}