package leetcode.src.main.java.array;

/**
 * https://leetcode.cn/problems/roman-to-integer/description/
 *
 * @author goodtime
 * @create 2023-12-04 02:31
 */
public class Number13 {

    public static void main(String[] args) {
        String s = "MCMXCIV";
        System.out.println(new Solution13().romanToInt(s));
    }

}

/**
 *
 */
class Solution13 {
    public int romanToInt(String s) {

        int number = 0;

        for (int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);
            char next = ' ';

            if (i < s.length() - 1) {
                next = s.charAt(i + 1);
            }

            //处理特殊情况
            if (c == 'I' && next == 'V') {
                number += 4;
                i++;
            } else if (c == 'I' && next == 'X') {
                number += 9;
                i++;
            } else if (c == 'X' && next == 'L') {
                number += 40;
                i++;
            } else if (c == 'X' && next == 'C') {
                number += 90;
                i++;
            } else if (c == 'C' && next == 'D') {
                number += 400;
                i++;
            } else if (c == 'C' && next == 'M') {
                number += 900;
                i++;
            } else {
                if (c == 'I') {
                    number += 1;
                } else if (c == 'V') {
                    number += 5;
                } else if (c == 'X') {
                    number += 10;
                } else if (c == 'L') {
                    number += 50;
                } else if (c == 'C') {
                    number += 100;
                } else if (c == 'D') {
                    number += 500;
                } else if (c == 'M') {
                    number += 1000;
                } else {
                    System.out.println("输入异常");
                }

            }

        }
        return number;

    }
}
