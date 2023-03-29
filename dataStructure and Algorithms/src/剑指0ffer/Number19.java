import java.util.ArrayList;

/**
 * @author goodtime
 * @create 2020-01-20 1:05 上午
 */
public class Number19 {
    public static void main(String[] args) {
        Solution19 solution19 = new Solution19();
        int[][] a = {{1,2,3,4},{4,5,6,7},{7,8,9,10},{12,13,14,15}};
        ArrayList<Integer> integers = solution19.printMatrix(a);
        System.out.println(integers);
    }

}
//贪吃蛇吃矩阵，找规律，外面一层吃完吃里面一层，吃的数字量和矩阵总量相同就跳出循环
class Solution19 {
    public ArrayList<Integer> printMatrix(int [][] matrix) {
        ArrayList<Integer> integers = new ArrayList<>();
        int all = matrix[0].length * matrix.length;
        for (int h1 = 0,j1 = 0,circle =1,count = 0;count < all;circle++) {
            int h2 = matrix.length-circle;
            int j2 = matrix[0].length-circle;
            while (j1 <= j2 && count < all) {
                integers.add(matrix[h1][j1]);
                j1++;
                count++;
            }
            j1--;
            while (h1 < h2 && count < all) {
                h1++;
                integers.add(matrix[h1][j1]);
                count++;
            }
            while (j1 >= circle && count < all) {
                j1--;
                integers.add(matrix[h1][j1]);
                count++;
            }
            while (h1 > circle && count < all) {
                h1--;
                integers.add(matrix[h1][j1]);
                count++;
            }
            j1++;
        }
        return integers;
    }
}