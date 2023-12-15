package leetcode.src.main.java.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/triangle/description/
 *
 * @author goodtime
 * @create 2023-11-29 18:37
 */
public class Number120 {
    public static void main(String[] args) {

        List<Integer> first = new ArrayList<>();
        List<Integer> second = new ArrayList<>();
        List<Integer> third = new ArrayList<>();
        List<Integer> forth = new ArrayList<>();
        first.add(2);
        second.add(3);
        second.add(4);
        third.add(6);
        third.add(5);
        third.add(7);
        forth.add(4);
        forth.add(1);
        forth.add(8);
        forth.add(3);

        List<List<Integer>> lists = new ArrayList<>();
        lists.add(first);
        lists.add(second);
        lists.add(third);
        lists.add(forth);

        System.out.println(new Solution120().minimumTotal(lists));
    }
}

/**
 * dp[i][j]下标含义 终点为第i-1行第j-1列的最短路径
 *
 * 初始条件 dp[0][0] 为三角形顶点的值
 *
 * 递推公式 dp[i][j] = Math.min(dp[i-1][j],dp[i][j]) + value; 注意每行第一列与每行最后一个值的情况，特殊处理
 *
 * 遍历顺序 从上到下，从左到右
 */
class Solution120 {
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }

        int row = triangle.size();

        int[][] dp = new int[row][row];

        dp[0][0] = triangle.get(0).get(0);

        for (int i = 1; i < triangle.size(); i++) {
            List<Integer> integers = triangle.get(i);
            for (int j = 0; j < i + 1; j++) {
                if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + integers.get(j);
                } else if (j < i) {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]) + integers.get(j);
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + integers.get(j);
                }
            }
        }

        int minPath = Integer.MAX_VALUE;

        for (int i = 0; i < triangle.size(); i++) {
            int lastRow = triangle.size() - 1;
            minPath = Math.min(dp[lastRow][i], minPath);
        }

        return minPath;
    }
}