package leetcode.src.main.java.dp;

/**
 * https://leetcode.cn/problems/longest-palindromic-subsequence/
 *
 * @author goodtime
 * @create 2023-04-06 08:24
 */
public class Number516 {
    public static void main(String[] args) {
        System.out.println(new Solution516().longestPalindromeSubseq("bbbab"));
    }

}

/**
 * dp[i][j]含义：字符串[i,j]范围内最大回文子串的字符数
 *
 * 递推公式：if(s[i] = s[j]){ if(j-i <= 1) dp[i][j] = j-i+1
 *                      if(j-i > 1) dp[i][j] = 2 + dp[i-1][j+1] }
 *
 * else dp[i][j] = max(dp[i][j-1], dp[i+1][j])
 *
 * 初始化：都是0
 *
 * 遍历顺序：从下到上，从左到右
 */
class Solution516 {
    public int longestPalindromeSubseq(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        if (s.length() == 1) {
            return 1;
        }

        int[][] dp = new int[s.length()][s.length()];

        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i; j < s.length(); j++) {

                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i <= 1) {
                        dp[i][j] = j - i + 1;
                    } else {
                        dp[i][j] = 2 + dp[i + 1][j - 1];
                    }
                }else {
                    dp[i][j] = Math.max(dp[i][j-1], dp[i+1][j]);
                }

            }
        }

        return dp[0][s.length()-1];

    }
}