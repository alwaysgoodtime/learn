package leetcode.src.main.java.graph;

/**
 * https://leetcode.cn/problems/number-of-islands/
 *
 * @author goodtime
 * @create 2023-12-08 22:05
 */
public class Number200 {
    public static void main(String[] args) {
        System.out.println("test");
    }
}

/**
 * 参考一篇高质量题解：https://leetcode.cn/problems/number-of-islands/solutions/211211/dao-yu-lei-wen-ti-de-tong-yong-jie-fa-dfs-bian-li-/
 * DFS
 */
class Solution200 {
    public int numIslands(char[][] grid) {


        if (grid == null) {
            return 0;
        }

        if (grid.length == 0) {
            return 0;
        }

        int result = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    result++;
                    dfs(grid, i, j);
                }
            }
        }

        return result;
    }

    /**
     * 遍历相连的陆地1，将其染成2
     */
    private void dfs(char[][] grid, int i, int j) {

        //超出范围
        if (i < 0 || i >= grid.length) {
            return;
        }
        if (j < 0 || j >= grid[0].length) {
            return;
        }

        if (grid[i][j] != '1') {
            return;
        }

        //染色
        grid[i][j] = '2';


        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);
        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);

    }


}