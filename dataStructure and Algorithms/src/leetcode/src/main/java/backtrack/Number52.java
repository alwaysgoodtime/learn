package leetcode.src.main.java.backtrack;

import java.util.HashMap;

/**
 * https://leetcode.cn/problems/n-queens-ii/
 *
 * @author goodtime
 * @create 2023-12-08 08:48
 */
public class Number52 {
    public static void main(String[] args) {
        System.out.println(new Solution52().totalNQueens(4));
    }
}

/**
 * @see Number51
 */
class Solution52 {

    int result = 0;
    int[][] chessboard;

    public int totalNQueens(int n) {

        if (n <= 0) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }

        chessboard = new int[n][n];

        backtrack(n, 0, new HashMap<Integer,Boolean>());

        return result;
    }

    private void backtrack(int n, int index, HashMap<Integer,Boolean> hashMap) {

        if (index == n) {
            if (isValid(chessboard)) {
                result++;
            }
            return;
        }

        for (int i = 0; i < n; i++) {
            if(hashMap.get(i) == null){
                chessboard[i][index] = 1;
                hashMap.put(i,true);
                backtrack(n, index + 1,hashMap);
                chessboard[i][index] = 0;
                hashMap.put(i,null);
            }else {
                continue;
            }


        }

    }

    private boolean isValid(int[][] chessboard) {

        for (int i = 0; i < chessboard.length; i++) {
            for (int j = 0; j < chessboard.length; j++) {

                //下棋时已保证每一列每一行只有一个皇后，检查斜向即可
                int queen = chessboard[i][j];

                if (queen == 0) {
                    continue;
                } else {

                    //检查斜向
                    int tmpI = i;
                    int tmpJ = j;

                    //先向左斜上
                    while (tmpI != 0 && tmpJ != 0) {
                        tmpI--;
                        tmpJ--;
                        if (chessboard[tmpI][tmpJ] == 1) {
                            return false;
                        }
                    }

                    tmpI = i;
                    tmpJ = j;

                    //再向左斜下
                    while (tmpI != chessboard.length - 1 && tmpJ != chessboard.length - 1) {
                        tmpI++;
                        tmpJ++;
                        if (chessboard[tmpI][tmpJ] == 1) {
                            return false;
                        }
                    }

                    tmpI = i;
                    tmpJ = j;

                    //再向右斜上
                    while (tmpI != 0 && tmpJ != chessboard.length - 1) {
                        tmpI--;
                        tmpJ++;
                        if (chessboard[tmpI][tmpJ] == 1) {
                            return false;
                        }
                    }


                    tmpI = i;
                    tmpJ = j;


                    //再向右斜下
                    while (tmpI != chessboard.length - 1 && tmpJ != 0) {
                        tmpI++;
                        tmpJ--;
                        if (chessboard[tmpI][tmpJ] == 1) {
                            return false;
                        }
                    }

                    break;
                }
            }
        }

        return true;

    }
}

