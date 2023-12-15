package leetcode.src.main.java.dp;

/**
 * https://leetcode.cn/problems/longest-palindromic-substring/
 *
 * @author goodtime
 * @create 2023-11-29 20:11
 */
public class Number5 {
    public static void main(String[] args) {
        System.out.println(new Solution5().longestPalindrome("ceecss"));
    }
}

/**
 * 核心逻辑：回文子串，其首尾字母必相同，而去掉首尾字母的子串也是回文子串
 *
 * dp[i][j] 以第i个字母开头j字母结尾的是否为回文串
 * 初始化 dp[i][j] = true(i=j)
 * 递推公式 dp[i][j] = dp[i+1][j-1] && value(i) == value(j)
 * 遍历顺序 从下往上 从左往右
 */
class Solution5 {
    public String longestPalindrome(String s) {
        if (s == null || s.equals("")) {
            return "";
        }

        if (s.length() == 1) {
            return s;
        }

        int length = s.length();
        boolean[][] dp = new boolean[length][length];

        //初始化
        for (int i = 0; i < length; i++) {
            dp[i][i] = true;
        }

        String longestPalindrome = s.substring(0, 1);

        for (int i = length - 2; i >= 0; i--) {
            for (int j = i + 1; j < length; j++) {
                boolean sameLetter = s.charAt(i) == s.charAt(j);
                if (j - i <= 2) {
                    dp[i][j] = sameLetter;
                } else {
                    dp[i][j] = dp[i + 1][j - 1] && sameLetter;
                }
                if (dp[i][j] && j - i + 1 > longestPalindrome.length()) {
                    longestPalindrome = s.substring(i, j + 1);
                }
            }
        }

        return longestPalindrome;
    }
}
