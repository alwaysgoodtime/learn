package leetcode.src.main.java.matrix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author goodtime
 * @create 2023-12-15 10:45
 */
public class Number54 {
    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println(new Solution54().spiralOrderAdvanced(matrix));
    }
}

/**
 * 螺旋矩阵有上下左右四种走法，用一个数字记录走法即可
 */
class Solution54 {

    //优化边界值判断条件
    public List<Integer> spiralOrderAdvanced(int[][] matrix) {

        if (matrix == null || matrix[0] == null || matrix[0].length == 0) {
            return new ArrayList<>();
        }

        List<Integer> result = new ArrayList<>();

        int maxRow = matrix.length - 1;
        int minRow = 0;
        int maxColumn = matrix[0].length - 1;
        int minColumn = 0;

        //这里的核心，是判断结束的情况更加优雅
        while (true) {

            for (int i = minColumn; i <= maxColumn; i++) {
                result.add(matrix[minRow][i]);
            }
            minRow++;
            if (minRow > maxRow) {
                break;
            }
            for (int i = minRow; i <= maxRow; i++) {
                result.add((matrix[i][maxColumn]));
            }
            maxColumn--;
            if (minColumn > maxColumn) {
                break;
            }
            for (int i = maxColumn; i >= minColumn; i--) {
                result.add(matrix[maxRow][i]);
            }
            maxRow--;
            if (minRow > maxRow) {
                break;
            }
            for (int i = maxRow; i >= minRow; i--) {
                result.add(matrix[i][minColumn]);
            }
            minColumn++;
            if (minColumn > maxColumn) {
                break;
            }

        }

        return result;
    }


    public List<Integer> spiralOrder(int[][] matrix) {

        if (matrix == null || matrix[0] == null || matrix[0].length == 0) {
            return new ArrayList<>();
        }

        List<Integer> result = new ArrayList<>();

        int row = 0;
        int column = 0;
        int maxRow = matrix.length - 1;
        int minRow = 0;
        int maxColumn = matrix[0].length - 1;
        int minColumn = 0;
        HashMap<String, Boolean> map = new HashMap<>();

        while (true) {


            String key = row + "-" + column;

            if (row == column && map.containsKey(key)) {
                row++;
                column++;
                minColumn++;
                minRow++;
                maxColumn--;
                maxRow--;
                key = row + "-" + column;
                if (map.containsKey(key) || row >= matrix.length || column >= matrix[0].length) {
                    break;
                } else {
                    result.add(matrix[row][column]);
                    map.put(key, true);
                }
            } else if (map.containsKey(key)) {
                break;
            } else {
                result.add(matrix[row][column]);
                map.put(key, true);
            }


            //row与column和最大最小行列值的关系，决定该如何走
            if (row == minRow && column < maxColumn) {
                column++;
            } else if (row != maxRow && column == maxColumn) {
                row++;
            } else if (row == maxRow && column != minColumn) {
                column--;
            } else if (column == minColumn && row != minRow) {
                row--;
            } else {
                break;
            }


        }

        return result;
    }
}
