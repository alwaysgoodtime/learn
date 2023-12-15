package leetcode.src.main.java.math;

/**
 * https://leetcode.cn/problems/palindrome-number/description/
 *
 * @author goodtime
 * @create 2023-12-06 09:09
 */
public class Number9 {
    public static void main(String[] args) {
        System.out.println(new Solution9().isPalindrome(1000000001));
    }
}

/**
 * 直观解法是把int转成string来解
 *
 * 不转成string的话，可以先确定它的位数，然后观察每个位数对应的值
 *
 * 注意：digit != 1000000000，防止digit过大溢出导致数值异常
 *
 *
 * 还有一种解法：反转一半数字，举例12321，反转321为123，与12321的前3个数字相同，那么就是回文数
 *
 *
 */
class Solution9 {
    public boolean isPalindrome(int x) {

        int digit = 10;

        if (x == Integer.MIN_VALUE) {
            return false;
        }

        if (x < 0) {
            return false;
        }

        while (x >= digit) {
            if (digit != 1000000000) {
                digit = digit * 10;
            }else {
                break;
            }
        }

        if (digit != 1000000000) {
            digit = digit / 10;
        }


        while (digit >= 10 || x >= 10) {

            int highNumber = x / digit;

            int lowNumber = x % 10;

            if (highNumber != lowNumber) {
                return false;
            }

            x =  (x - highNumber * digit) / 10;

            digit = digit / 100;

        }

        return true;

    }
}
