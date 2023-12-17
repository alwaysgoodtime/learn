package leetcode.src.main.java.matrix;

import java.util.HashMap;

/**
 * https://leetcode.cn/problems/rotate-image/
 *
 * @author goodtime
 * @create 2023-12-15 12:33
 */
public class Number48 {
    int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
}

/**
 * 对n*n顺时针旋转90度的意思，就是第i行变n-1-i列（从0行0列开始算）
 * 也即，坐标为[i,j]的点，旋转后变成[j][n-1-i]
 *
 * 同时，一个坐标旋转4次就得到它所有需要挪动的地方
 *
 * 还有一种解法，是先水平翻转，再垂直翻转
 */
class Solution48 {

    public void rotate(int[][] matrix) {

        if (matrix == null) {
            return;
        }

        //这样就保证每个点只会旋转一次
        for (int i = 0; i < matrix.length / 2; i++) {
            for (int j = 0; j < (matrix.length + 1) / 2 ; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[matrix.length-1-j][i];
                matrix[matrix.length-1-j][i] = matrix[matrix.length-1-i][matrix.length-1-j];
                matrix[matrix.length-1-i][matrix.length-1-j] = matrix[j][matrix.length-1-i];
                matrix[j][matrix.length-1-i] = tmp;
            }
        }

    }


}
