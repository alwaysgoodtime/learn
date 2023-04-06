package leetcode.src.main.java.dp;

/**
 * https://leetcode.cn/problems/distinct-subsequences/
 *
 * @author goodtime
 * @create 2023-04-06 05:47
 */
public class Number115 {
    public static void main(String[] args) {
        System.out.println(new Solution115().numDistinct("babgbag", "bag"));
    }

}

/**
 * dp[i][j]含义：[0,i-1]的t在[0,j-1]的s中出现的次数
 *
 * 递推公式：if(s[i-1] = t[j-1]) dp[i][j] = dp[i-1][j-1] + dp[i][j-1]
 * if(s[i-1] != t[j-1]) dp[i][j] = dp[i][j-1]
 *
 * 初始化： dp[0][j] = 1(空字符串默认在数组s中出现了一次) dp[i][0] = 0（i>=1）
 */
class Solution115 {
    public int numDistinct(String s, String t) {
        if (s == null || s.length() == 0 || t == null || t.length() == 0) {
            return 0;
        }

        int[][] dp = new int[t.length() + 1][s.length() + 1];

        char[] charsS = s.toCharArray();
        char[] charsT = t.toCharArray();

        //初始化
        for (int i = 0; i <= s.length(); i++) {
            dp[0][i] = 1;
        }

        //递归
        for (int i = 1; i <= t.length(); i++) {
            for (int j = 1; j <= s.length(); j++) {
                if(charsT[i-1] == charsS[j-1]){
                    dp[i][j] = dp[i-1][j-1] + dp[i][j-1];
                }else {
                    dp[i][j] = dp[i][j-1];
                }
            }
        }

        return dp[t.length()][s.length()];
    }
}