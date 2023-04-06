package leetcode.src.main.java.dp;

/**
 * https://leetcode.cn/problems/is-subsequence/
 *
 * @author goodtime
 * @create 2023-04-06 05:11
 */
public class Number392 {

    public static void main(String[] args) {
        System.out.println(new Solution392().isSubsequence("abc", "ahbgdc"));
    }

}


/**
 * dp[i][j]含义：s[0,i-1]是否为t[0,j-1]字符串的子序列
 *
 * 递推公式： if(charsS[i-1] == charsT[j-1] && dp[i-1][j-1]){
 *              dp[i][j] = true;
 *          }else{
 *              dp[i][j] = dp[i][j-1];
 *          }
 *
 * 初始化： dp[0][i] = true;
 *
 * 也可以求s和t的最长公共子序列长度是否为s的长度来做判断，这样就变成number1143了
 *
 */
class Solution392 {
    public boolean isSubsequence(String s, String t) {
        if (s == null || s.length() == 0) {
            return true;
        }

        if (t == null || t.length() == 0) {
            return false;
        }

        char[] charsS = s.toCharArray();
        char[] charsT = t.toCharArray();

        boolean[][] dp = new boolean[s.length() + 1][t.length() + 1];

        //初始化
        for (int i = 0; i < charsT.length; i++) {
            dp[0][i] = true;
        }


        for (int i = 1; i <= charsS.length; i++) {
            for (int j = 1; j <= charsT.length; j++) {
                if (charsS[i - 1] == charsT[j - 1] && dp[i - 1][j - 1]) {
                    dp[i][j] = true;
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }

        return dp[s.length()][t.length()];
    }
}