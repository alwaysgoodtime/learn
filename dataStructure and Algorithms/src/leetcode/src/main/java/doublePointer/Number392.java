package leetcode.src.main.java.doublePointer;

/**
 * https://leetcode.cn/problems/is-subsequence/
 *
 * @author goodtime
 * @create 2023-12-01 02:03
 */
public class Number392 {
    public static void main(String[] args) {
        String s = "abc", t = "ahbgdc";
        System.out.println(new Solution392().isSubsequence(s, t));
    }
}

/**
 * 快慢指针，一个指针在s上，一个指针在t上
 * 本题也可用dp，
 *
 * @see leetcode.src.main.java.dp.Number392
 */
class Solution392 {
    public boolean isSubsequence(String s, String t) {

        if (s == null || t == null) {
            return false;
        }

        int sPointer = 0;
        int tPointer = 0;

        int sLength = s.length();
        int tLength = t.length();

        while (sPointer < sLength) {
            char c = s.charAt(sPointer);
            while (tPointer < tLength && t.charAt(tPointer) != c) {
                tPointer++;
            }

            if (tPointer == tLength) {
                return false;
            }

            sPointer++;
            tPointer++;
        }

        return true;
    }
}