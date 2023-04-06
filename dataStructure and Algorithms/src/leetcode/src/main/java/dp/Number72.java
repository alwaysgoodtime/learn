package leetcode.src.main.java.dp;

/**
 * https://leetcode.cn/problems/edit-distance/
 * @author goodtime
 * @create 2023-04-06 06:59
 */
public class Number72 {
    public static void main(String[] args) {
        System.out.println(new Solution72().minDistance("ros","horse"));
    }
}

/**
 * dp[i][j]: 对word1的[0,i]和word2的[0,j],编辑成相同字符的最少操作数
 *
 * 递推公式：if(word1[i-1] = word2[j-1]) dp[i][j] = dp[i-1][j-1]
 *         if(word1[i-1] != word2[j-1]) dp[i][j] = min(dp[i-1][j] +1, dp[i][j-1] +1, dp[i-1][j-1] + 1)
 *
 *
 * 初始化：dp[i][0] = i  dp[0][j] = j (相当与有几个字符，就需要删几次，来和空字符串相同)
 *
 */
class Solution72 {
    public int minDistance(String word1, String word2) {

        if (word1 == null || word2 == null) {
            return 0;
        }

        if (word1.length() == 0) {
            return word2.length();
        }

        if (word2.length() == 0) {
            return word1.length();
        }

        char[] wordChars = word1.toCharArray();
        char[] word2Chars = word2.toCharArray();

        int[][] dp = new int[word1.length() + 1][word2.length() + 1];

        //初始化
        for (int i = 0; i <= word1.length(); i++) {
            dp[i][0] = i;
        }
        for (int i = 1; i <= word2.length(); i++) {
            dp[0][i] = i;
        }

        //递推
        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                if (wordChars[i - 1] == word2Chars[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1);
                    dp[i][j] = Math.min(dp[i][j],dp[i-1][j-1] + 1);
                }

            }
        }

        return dp[word1.length()][word2.length()];
    }
}