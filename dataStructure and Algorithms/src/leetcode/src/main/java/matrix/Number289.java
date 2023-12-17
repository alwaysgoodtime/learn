package leetcode.src.main.java.matrix;

/**
 * https://leetcode.cn/problems/game-of-life/
 *
 * @author goodtime
 * @create 2023-12-15 14:36
 */
public class Number289 {
    public static void main(String[] args) {
        int[][] board = {{0, 1, 0}, {0, 0, 1}, {1, 1, 1}, {0, 0, 0}};
        new Solution289().gameOfLifeAdvanced(board);
    }
}

/**
 * 最直观的方法是对每个细胞进行遍历，看其是生是死，用一个和原二维数组一样的数组，来记录细胞在下一个状态的生死
 *
 * 也可以增加两个状态，来表示状态的情况，3为活变死，2为死变活，1和0如果保持不变，就保持不变
 */
class Solution289 {

    public void gameOfLife(int[][] board) {

        int[][] result = new int[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                result[i][j] = calculate2(board, i, j);
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = result[i][j];
            }
        }

    }


    public void gameOfLifeAdvanced(int[][] board) {

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = calculate(board, i, j);
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] >= 2) {
                    board[i][j] = (board[i][j] + 1) % 2;
                }

            }
        }
    }


    private int calculate(int[][] board, int i, int j) {
        int maxRow = board.length;
        int maxColumn = board[0].length;

        int liveCell = 0;

        if (i >= 1) {
            liveCell += board[i - 1][j] % 2;
            if (j >= 1) {
                liveCell += board[i - 1][j - 1] % 2;
            }
            if (j <= maxColumn - 2) {
                liveCell += board[i - 1][j + 1] % 2;
            }
        }


        if (j >= 1) {
            liveCell += board[i][j - 1] % 2;
        }

        if (j <= maxColumn - 2) {
            liveCell += board[i][j + 1] % 2;
        }


        if (i <= maxRow - 2) {
            liveCell += board[i + 1][j] % 2;
            if (j >= 1) {
                liveCell += board[i + 1][j - 1] % 2;
            }
            if (j <= maxColumn - 2) {
                liveCell += board[i + 1][j + 1] % 2;
            }
        }

        //判断细胞死活
        if (board[i][j] == 0) {
            if (liveCell == 3) {
                return 2;
            } else {
                return 0;
            }
        } else {
            if (liveCell < 2 || liveCell > 3) {
                return 3;
            } else {
                return 1;
            }
        }
    }

    private int calculate2(int[][] board, int i, int j) {
        int maxRow = board.length;
        int maxColumn = board[0].length;

        int liveCell = 0;

        if (i >= 1) {
            liveCell += board[i - 1][j];
            if (j >= 1) {
                liveCell += board[i - 1][j - 1];
            }
            if (j <= maxColumn - 2) {
                liveCell += board[i - 1][j + 1];
            }
        }


        if (j >= 1) {
            liveCell += board[i][j - 1];
        }

        if (j <= maxColumn - 2) {
            liveCell += board[i][j + 1];
        }


        if (i <= maxRow - 2) {
            liveCell += board[i + 1][j];
            if (j >= 1) {
                liveCell += board[i + 1][j - 1];
            }
            if (j <= maxColumn - 2) {
                liveCell += board[i + 1][j + 1];
            }
        }

        //判断细胞死活
        if (board[i][j] == 0) {
            return liveCell == 3 ? 1 : 0;
        } else {
            if (liveCell < 2 || liveCell > 3) {
                return 0;
            } else {
                return 1;
            }
        }
    }
}
