package leetcode.src.main.java.matrix;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * https://leetcode.cn/problems/valid-sudoku/
 *
 * @author goodtime
 * @create 2023-12-15 09:55
 */
public class Number36 {
    public static void main(String[] args) {
        char[][] board = {{'8', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        System.out.println(new Solution36().isValidSudoku(board));
    }
}

/**
 * 先横再纵最后3X3
 * 优化的解法是只遍历一次，将遍历的结果分别储藏
 */
class Solution36 {

    /**
     * key row_number
     */
    HashMap<String, Boolean> rowMap = new HashMap<>();
    /**
     * key column_number
     */
    HashMap<String, Boolean> columnMap = new HashMap<>();
    HashMap<Character, Boolean> matrixMap = new HashMap<>();


    public boolean isValidSudokuAdvanced(char[][] board) {
        if (board == null || board[0].length == 0) {
            return false;
        }


        //3X3
        for (int i = 0; i < board.length; i = i + 3) {
            for (int j = 0; j < board[0].length; j = j + 3) {

                if (board[i][j] != '.' && !check(board[i][j], i, j)) {
                    return false;
                }
                if (board[i][j + 1] != '.' && !check(board[i][j + 1], i, j + 1)) {
                    return false;
                }
                if (board[i][j + 2] != '.' && !check(board[i][j + 2], i, j + 2)) {
                    return false;
                }
                if (board[i + 1][j] != '.' && !check(board[i + 1][j], i + 1, j)) {
                    return false;
                }
                if (board[i + 1][j + 1] != '.' && !check(board[i + 1][j + 1], i + 1, j + 1)) {
                    return false;
                }
                if (board[i + 1][j + 2] != '.' && !check(board[i + 1][j + 2], i + 1, j + 2)) {
                    return false;
                }
                if (board[i + 2][j] != '.' && !check(board[i + 2][j], i + 2, j)) {
                    return false;
                }
                if (board[i + 2][j + 1] != '.' && !check(board[i + 2][j + 1], i + 2, j + 1)) {
                    return false;
                }
                if (board[i + 2][j + 2] != '.' && !check(board[i + 2][j + 2], i + 2, j + 2)) {
                    return false;
                }
                matrixMap.clear();

            }
        }

        return true;

    }

    private boolean check(char character, int row, int column) {

        if (matrixMap.containsKey(character)) {
            return false;
        } else {
            matrixMap.put(character, true);
        }
        String rowKey = row + "_" + character;
        String columnKey = column + "_" + character;
        if (rowMap.containsKey(rowKey)) {
            return false;
        } else {
            rowMap.put(rowKey, true);
        }
        if (columnMap.containsKey(columnKey)) {
            return false;
        } else {
            columnMap.put(columnKey, true);
        }

        return true;
    }

    public boolean isValidSudoku(char[][] board) {
        if (board == null || board[0].length == 0) {
            return false;
        }

        HashMap<Character, Boolean> map = new HashMap<>();

        //行
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                char c = board[i][j];
                if (c == '.') {
                    continue;
                }
                if (map.containsKey(c)) {
                    return false;
                } else {
                    map.put(c, true);
                }
            }
            map.clear();
        }

        //列
        for (int i = 0; i < board[0].length; i++) {
            for (int j = 0; j < board.length; j++) {
                char c = board[j][i];
                if (c == '.') {
                    continue;
                }
                if (map.containsKey(c)) {
                    return false;
                } else {
                    map.put(c, true);
                }
            }
            map.clear();
        }

        //3X3
        for (int i = 0; i < board.length; i = i + 3) {
            for (int j = 0; j < board[0].length; j = j + 3) {
                ArrayList<Character> list = new ArrayList<>();
                list.add(board[i][j]);
                list.add(board[i][j + 1]);
                list.add(board[i][j + 2]);
                list.add(board[i + 1][j]);
                list.add(board[i + 1][j + 1]);
                list.add(board[i + 1][j + 2]);
                list.add(board[i + 2][j]);
                list.add(board[i + 2][j + 1]);
                list.add(board[i + 2][j + 2]);

                for (int k = 0; k < list.size(); k++) {
                    Character character = list.get(k);
                    if (character.equals('.')) {
                        continue;
                    }
                    if (map.containsKey(character)) {
                        return false;
                    } else {
                        map.put(character, true);
                    }
                }
                map.clear();

            }
        }

        return true;

    }
}
