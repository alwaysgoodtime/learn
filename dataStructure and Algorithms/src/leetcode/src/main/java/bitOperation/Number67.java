package leetcode.src.main.java.bitOperation;

import java.util.ArrayList;

/**
 * https://leetcode.cn/problems/add-binary/
 *
 * @author goodtime
 * @create 2023-12-05 17:18
 */
public class Number67 {
    public static void main(String[] args) {
        System.out.println(new Solution67().addBinary("1","0"));
    }
}

/**
 * 通过对位数少的数高位补0来方便计算
 */
class Solution67 {
    public String addBinary(String a, String b) {

        if (a == null || a.length() == 0 || a.equals("0")) {
            return b;
        }

        if (b == null || b.length() == 0 || b.equals("0")) {
            return a;
        }

        int aLength = a.length();
        int bLength = b.length();

        for (int i = 0; i < Math.abs(bLength - aLength); i++) {
            if (aLength < bLength) {
                a = "0" + a;
            } else {
                b = "0" + b;
            }
        }

        ArrayList<Character> result = new ArrayList<>();

        boolean carry = false;

        for (int i = a.length() - 1; i >= 0; i--) {


            char tmpa = a.charAt(i);
            char tmpb = b.charAt(i);

            if (tmpa == '0' && tmpb == '0') {
                if (carry) {
                    result.add('1');
                    carry = false;
                } else {
                    result.add('0');
                }
            } else if (tmpa == '1' && tmpb == '1') {
                if (carry) {
                    result.add('1');
                } else {
                    result.add('0');
                    carry = true;
                }
            } else {
                if (carry) {
                    result.add('0');
                } else {
                    result.add('1');
                }

            }
        }

        if (carry) {
            result.add('1');
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = result.size() - 1; i >= 0; i--) {
            stringBuilder.append(result.get(i));
        }

        return stringBuilder.toString();
    }
}