package leetcode.src.main.java.array;

/**
 * https://leetcode.cn/problems/zigzag-conversion/
 *
 * @author goodtime
 * @create 2023-12-04 03:46
 */
public class Number6 {
    public static void main(String[] args) {
        System.out.println(new Solution6().convert("Apalindromeisaword,phrase,number,orothersequenceofunitsthatcanbereadthesamewayineitherdirection,withgeneralallowancesforadjustmentstopunctuationandworddividers.", 3));
    }
}

/**
 * 先定好顺序，再做输出，关键是找好单词在二维数组中的移动方式
 *
 * 优化：按顺序填到n行中，然后输出，可以用flag来表示填行的顺序
 * n行用数组模拟，见下
 */
class Solution6 {

    public String convert(String s, int numRows) {

        if (s == null || s.length() <= 1 || numRows <= 1) {
            return s;
        }


        //用来模拟真实行
        StringBuilder[] row = new StringBuilder[numRows];

        int currentRow = 0;
        boolean flag = true;// true为顺着走，false为反着走

        for (int i = 0; i < s.length(); i++) {
            if(row[currentRow] == null){
                row[currentRow] = new StringBuilder();
                row[currentRow].append(s.charAt(i));
            }else {
                row[currentRow] = row[currentRow].append(s.charAt(i));
            }

            if (currentRow == 0) {
                flag = true;
                currentRow++;
            } else if (currentRow + 1 < numRows && flag) {
                currentRow++;
            } else if (currentRow + 1 == numRows) {
                currentRow--;
                flag = false;
            } else {
                currentRow--;
            }
        }

        for (int i = 0; i < numRows; i++) {
            if (row[i] == null) {
                break;
            }
            row[0].append(row[i]);
        }

        return row[0].toString();
    }


    public String convert2(String s, int numRows) {

        //numRows为1时，就是s本身
        if (s == null || s.length() <= 1 || numRows <= 1) {
            return s;
        }

        int column = (s.length() / (numRows * 2 - 2)) * (numRows - 1) + numRows;

        char[][] array = new char[numRows][column];

        int currentRow = 0;
        int currentColumn = 0;

        for (int i = 0; i < s.length(); i++) {

            array[currentRow][currentColumn] = s.charAt(i);

            //往下走
            if (currentColumn % (numRows - 1) == 0 && currentRow + 1 != numRows) {
                currentRow++;
            } else {
                //斜着走
                currentRow--;
                currentColumn++;
            }

        }

        StringBuilder result = new StringBuilder();

        currentRow = 0;
        currentColumn = 0;

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                if (array[i][j] != 0) {
                    result.append(array[i][j]);
                }
            }
        }

        return result.toString();

    }
}
