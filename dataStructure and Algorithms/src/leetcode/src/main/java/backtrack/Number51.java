package leetcode.src.main.java.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/n-queens/
 *
 * @author goodtime
 * @create 2023-03-31 16:38
 */
public class Number51 {
    public static void main(String[] args) {

        List<List<String>> lists = new Solution51().solveNQueens(4);
        System.out.println(lists);
    }
}

class Solution51 {

    ArrayList<List<String>> result = new ArrayList<>();

    ArrayList<String> solve = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {

        if (n == 0) {
            return result;
        }

        int[][] chessboard = new int[n][n];

        backtracking(n, 0, chessboard);
        return result;
    }

    private void backtracking(int n, int row, int[][] chessboard) {

        //结束条件
        if (row == n) {
            //收集结果
            result.add(new ArrayList<>(solve));
        }

        for (int column = 0; column < n; column++) {
            if (canSetQueen(chessboard, column, row, n)) {
                //处理
                chessboard[row][column] = 1;
                StringBuilder tmp = new StringBuilder();
                for (int i = 0; i < n; i++) {
                    if(i != column){
                        tmp.append(".");
                    }else {
                        tmp.append("Q");
                    }
                }
                solve.add(tmp.toString());
                //递归
                backtracking(n, row + 1, chessboard);
                //回溯
                chessboard[row][column] = 0;
                solve.remove(solve.size() - 1);
            }
        }

    }

    //检测在chessboard上，是否可以把queen放到[row,column]的位置上
    private boolean canSetQueen(int[][] chessboard, int column, int row, int n) {

        //1.检查该行上除皇后所在列，其他列是否有皇后，此检查可跳过，因为我们的递归顺序保证了这一点

        //2.检查该列上除皇后所在行，其他行是否有皇后
        for (int i = 0; i < n; i++) {
            if (i != row && chessboard[i][column] == 1) {
                return false;
            }
        }

        //3.检查该位置的对角线上是否有queen
        for (int i = row + 1, j = column + 1; i < n && j < n; i++, j++) {
            if(chessboard[i][j] == 1){
                return false;
            }
        }
        for (int i = row - 1, j = column - 1; i >= 0 && j >= 0; i--, j--) {
            if(chessboard[i][j] == 1){
                return false;
            }
        }
        for (int i = row + 1, j = column - 1; i < n && j >= 0; i++, j--) {
            if(chessboard[i][j] == 1){
                return false;
            }
        }
        for (int i = row - 1, j = column + 1; i >= 0 && j < n; i--, j++) {
            if(chessboard[i][j] == 1){
                return false;
            }
        }

        return true;

    }


}
