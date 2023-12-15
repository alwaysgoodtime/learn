package leetcode.src.main.java.math;

/**
 * @author goodtime
 * @create 2023-12-06 09:50
 */
public class Number66 {
    public static void main(String[] args) {
        int[] digits = {9, 9, 9};
        new Solution66().plusOne(digits);
    }
}

class Solution66 {
    public int[] plusOne(int[] digits) {

        if (digits == null) {
            return null;
        }

        boolean isCarry = true;

        //先确认特殊情况，即digits中的数都为9
        for (int i = 0; i < digits.length; i++) {
            if (digits[i] != 9) {
                isCarry = false;
            }
        }

        if (isCarry) {
            int[] sum = new int[digits.length + 1];
            sum[0] = 1;
            return sum;
        }

        //其他情况
        int[] plusOne = new int[digits.length];

        //从后往前遍历
        isCarry = false;

        for (int i = digits.length - 1; i >= 0; i--) {

            if (i == digits.length - 1) {
                if (digits[i] == 9) {
                    isCarry = true;
                    plusOne[i] = 0;
                } else {
                    plusOne[i] = digits[i] + 1;
                }
            } else if (digits[i] == 9 && isCarry) {
                plusOne[i] = 0;
            } else if (isCarry) {
                plusOne[i] = digits[i] + 1;
                isCarry = false;
            } else {
                plusOne[i] = digits[i];
            }

        }

        return plusOne;

    }
}
