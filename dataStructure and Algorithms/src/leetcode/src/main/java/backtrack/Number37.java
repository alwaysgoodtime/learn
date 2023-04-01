package leetcode.src.main.java.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.cn/problems/sudoku-solver/
 *
 * @author goodtime
 * @create 2023-03-31 17:16
 */
public class Number37 {
    public static void main(String[] args) {


        char[][] board = new char[][]{
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}};
        new Solution37().solveSudoku(board);
        System.out.println(board);

    }

}

class Solution37 {

    List<Character> list = Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9');

    public void solveSudoku(char[][] board) {

        if (board == null || board.length == 0) {
            return;
        }

        backtracking(board, board.length, 0, 0);

    }

    
    //另一个版本
    private boolean backtracking(char[][] board, int n, int row, int column) {


        if (column == n) {
            column = 0;
            row++;
        }

        if (row == n) {
            return true;
        }

        if (board[row][column] != '.') {
            return backtracking(board, n, row, column + 1);
        }

        ArrayList<Character> copy = new ArrayList<>(list);

        //排除该行已经有的数字，不再进行填写
        for (int i = 0; i < n; i++) {
            if (board[row][i] != '.') {
                copy.remove((Character) board[row][i]);
            }
        }

        //从左往右尝试用copy中的值去填写 board[row][column]

        boolean result = false;

        for (int i = 0; i < copy.size(); i++) {
            //处理
            Character dealChar = copy.get(i);
            board[row][column] = dealChar;
            if (isLegal(dealChar, board, row, column, n)) {
                //递归
                result = backtracking(board, n,  row, column + 1);
                if (result) {
                    return true;
                }
            }

            //回溯
            board[row][column] = '.';
        }

        return false;

    }
    
    //检查填入的数字是否合法
    private boolean isLegal(Character dealChar, char[][] board, int dealRow, int dealColumn, int n) {

        //1.填入的数字是否在该行出现，已经行去重过，所以可以忽略

        //2.填入的数字是否在该列出现
        for (int i = 0; i < n; i++) {
            if (i != dealRow && board[i][dealColumn] == dealChar) {
                return false;
            }
        }

        //3.填入的数字是否在3X3方格出现
        int compressRow = dealRow % 3;
        int compressColumn = dealColumn % 3;


        if (compressRow == 0 && compressColumn == 0) {
            //[0,0]位置，不用检查同一行与同一列
            return dealChar != board[dealRow + 1][dealColumn + 1]
                    && dealChar != board[dealRow + 1][dealColumn + 2]
                    && dealChar != board[dealRow + 2][dealColumn + 1]
                    && dealChar != board[dealRow + 2][dealColumn + 2];
        } else if (compressRow == 0 && compressColumn == 1) {
            //[0,1]位置，不用检查同一行与同一列
            return dealChar != board[dealRow + 1][dealColumn - 1]
                    && dealChar != board[dealRow + 1][dealColumn + 1]
                    && dealChar != board[dealRow + 2][dealColumn - 1]
                    && dealChar != board[dealRow + 2][dealColumn + 1];
        } else if (compressRow == 0 && compressColumn == 2) {
            //[0,2]位置，不用检查同一行与同一列
            return dealChar != board[dealRow + 1][dealColumn - 2]
                    && dealChar != board[dealRow + 1][dealColumn - 1]
                    && dealChar != board[dealRow + 2][dealColumn - 2]
                    && dealChar != board[dealRow + 2][dealColumn - 1];
        } else if (compressRow == 1 && compressColumn == 0) {
            //[1,0]位置，不用检查同一行与同一列
            return dealChar != board[dealRow - 1][dealColumn + 1]
                    && dealChar != board[dealRow - 1][dealColumn + 2]
                    && dealChar != board[dealRow + 1][dealColumn + 1]
                    && dealChar != board[dealRow + 1][dealColumn + 2];
        } else if (compressRow == 1 && compressColumn == 1) {
            //[1,1]位置，不用检查同一行与同一列
            return dealChar != board[dealRow - 1][dealColumn - 1]
                    && dealChar != board[dealRow - 1][dealColumn + 1]
                    && dealChar != board[dealRow + 1][dealColumn - 1]
                    && dealChar != board[dealRow + 1][dealColumn + 1];
        } else if (compressRow == 1 && compressColumn == 2) {
            //[1,2]位置，不用检查同一行与同一列
            return dealChar != board[dealRow - 1][dealColumn - 2]
                    && dealChar != board[dealRow - 1][dealColumn - 1]
                    && dealChar != board[dealRow + 1][dealColumn - 2]
                    && dealChar != board[dealRow + 1][dealColumn - 1];
        } else if (compressRow == 2 && compressColumn == 0) {
            //[2,0]位置，不用检查同一行与同一列
            return dealChar != board[dealRow - 2][dealColumn + 1]
                    && dealChar != board[dealRow - 2][dealColumn + 2]
                    && dealChar != board[dealRow - 1][dealColumn + 1]
                    && dealChar != board[dealRow - 1][dealColumn + 2];
        } else if (compressRow == 2 && compressColumn == 1) {
            //[2,1]位置，不用检查同一行与同一列
            return dealChar != board[dealRow - 2][dealColumn - 1]
                    && dealChar != board[dealRow - 2][dealColumn + 1]
                    && dealChar != board[dealRow - 1][dealColumn - 1]
                    && dealChar != board[dealRow - 1][dealColumn + 1];
        } else {
            //[2,2]位置，不用检查同一行与同一列
            return dealChar != board[dealRow - 2][dealColumn - 2]
                    && dealChar != board[dealRow - 2][dealColumn - 1]
                    && dealChar != board[dealRow - 1][dealColumn - 2]
                    && dealChar != board[dealRow - 1][dealColumn - 1];
        }

    }
}
