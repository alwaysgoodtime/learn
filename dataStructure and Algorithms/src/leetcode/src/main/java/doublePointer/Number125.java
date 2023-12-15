package leetcode.src.main.java.doublePointer;

/**
 * @author goodtime
 * @create 2023-12-01 01:22
 */
public class Number125 {

    public static void main(String[] args) {
        String s = ",,,,,,,,,,,,acva";
        System.out.println(new Solution125().isPalindrome(s));
    }

}

/**
 * 双指针，前指针从前往后，后指针从后往前
 */
class Solution125 {
    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }

        char[] chars = s.toCharArray();
        int prePointer = 0;
        int postPointer = s.length() - 1;

        while (prePointer < postPointer) {

            char aChar;
            char pChar;

            aChar = chars[prePointer];
            if (65 <= aChar && aChar <= 90) {
                aChar = (char) (aChar + 32);
            } else if (aChar >= 48 && aChar <= 57) {
            } else if (aChar < 97 || aChar > 122) {
                prePointer++;
                continue;
            }

            pChar = chars[postPointer];
            if (65 <= pChar && pChar <= 90) {
                pChar = (char) (pChar + 32);
            } else if (pChar >= 48 && pChar <= 57) {
            } else if (pChar < 97 || pChar > 122) {
                postPointer--;
                continue;
            }

            if (aChar != pChar) {
                return false;
            }

            prePointer++;
            postPointer--;

        }

        return true;

    }
}
