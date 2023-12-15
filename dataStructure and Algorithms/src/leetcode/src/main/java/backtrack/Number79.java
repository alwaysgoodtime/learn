package leetcode.src.main.java.backtrack;

import java.util.HashMap;

/**
 * @author goodtime
 * @create 2023-12-08 09:47
 */
public class Number79 {
    public static void main(String[] args) {
        char[][] board = {{'a', 'a'}};
        System.out.println(new Solution79().exist(board, "aaa"));
    }

}

class Solution79 {

    boolean result = false;
    StringBuilder stringBuilder = new StringBuilder();

    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return false;
        }

        HashMap<String, Boolean> map = new HashMap<>();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == word.charAt(0)) {

                    //长度为1的情况就不需回溯了
                    if (word.length() == 1) {
                        return true;
                    }

                    String curKey = (i) + "_" + (j);
                    map.put(curKey, true);
                    stringBuilder.append(word.charAt(0));

                    backtrack(board, word, 1, map, i, j + 1);
                    backtrack(board, word, 1, map, i + 1, j);
                    backtrack(board, word, 1, map, i - 1, j);
                    backtrack(board, word, 1, map, i, j - 1);

                    map.put(curKey, null);
                    stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                }
                if (result) {
                    return true;
                }
            }
        }

        return false;
    }

    private void backtrack(char[][] board, String word, int index, HashMap<String, Boolean> map, int row, int column) {

        if(result == true){
            return;
        }

        if (row < 0 || row > board.length - 1) {
            return;
        }

        if (column < 0 || column > board[0].length - 1) {
            return;
        }

        if (index >= word.length() || board[row][column] != word.charAt(index)) {
            return;
        }

        String curKey = (row) + "_" + (column);
        if (map.get(curKey) != null) {
            return;
        }

        map.put(curKey, true);
        stringBuilder.append(word.charAt(index));

        if (stringBuilder.length() == word.length()) {
            result = true;
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            return;
        }

        backtrack(board, word, index + 1, map, row, column + 1);
        backtrack(board, word, index + 1, map, row + 1, column);
        backtrack(board, word, index + 1, map, row - 1, column);
        backtrack(board, word, index + 1, map, row, column - 1);

        map.put(curKey, null);
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
    }
}