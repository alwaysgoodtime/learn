package leetcode.src.main.java.binarySearch;

/**
 * https://leetcode.cn/problems/search-a-2d-matrix/
 *
 * @author goodtime
 * @create 2023-12-07 20:47
 */
public class Number74 {
    public static void main(String[] args) {
        int[][] matrix = {{1, 3}, {5, 7}};

        System.out.println(new Solution74().searchMatrix(matrix, 5));
    }
}

/**
 * @see Number35
 * 加强版35题
 */
class Solution74 {
    public boolean searchMatrix(int[][] matrix, int target) {

        if (matrix == null || matrix.length == 0) {
            return false;
        }


        for (int i = 0; i < matrix.length; i++) {

            int l = -1, r = matrix[i].length;
            int[] row = matrix[i];

            while (l + 1 != r) {
                int m = (l + r) / 2;
                if (row[m] < target) {
                    l = m;
                }else if(row[m] > target){
                    r = m;
                }else {
                    return true;
                }
            }
        }

        return false;


    }
}
