package leetcode.src.main.java.graph;

/**
 * https://leetcode.cn/problems/surrounded-regions/
 *
 * @author goodtime
 * @create 2023-12-08 22:57
 */
public class Number130 {
    public static void main(String[] args) {
        System.out.println("test");
    }
}

/**
 * @see Number200
 * 还是格子中进行深度遍历,被围绕的区间不会存在于边界上，换句话说，
 * 任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。
 *
 * 于是，我们可以换个思路，找到边界的'O'与边界上的'O'相连的'O',将其标记为'P'，接着遍历一遍数组，将所有非'P'的'O'染成'X',将所有为'P'的'O'染成'0'
 */
class Solution130 {
    public void solve(char[][] board) {

        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }

        /**
         * 将所有边界O与和边界O相连的O染成P
         */
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (isBound(i, j, board) && board[i][j] == 'O') {
                    dps(board, i, j);
                }
            }
        }

        /**
         * 将所有O染成X,将所有P染成O
         */
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == 'P') {
                    board[i][j] = 'O';
                } else {
                }
            }
        }
    }

    private void dps(char[][] board, int i, int j) {

        if (i < 0 || j < 0) {
            return;
        }

        if (i >= board.length || j >= board[0].length) {
            return;
        }

        if (board[i][j] != 'O') {
            return;
        }

        board[i][j] = 'P';

        dps(board, i + 1, j);
        dps(board, i, j + 1);
        dps(board, i - 1, j);
        dps(board, i, j - 1);

    }

    private boolean isBound(int i, int j, char[][] board) {
        if (i == 0 || j == 0) {
            return true;
        } else if (i == board.length - 1 || j == board[0].length - 1) {
            return true;
        } else {
            return false;
        }
    }
}

