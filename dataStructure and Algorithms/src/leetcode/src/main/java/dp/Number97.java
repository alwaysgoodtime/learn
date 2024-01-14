package leetcode.src.main.java.dp;


/**
 * https://leetcode.cn/problems/interleaving-string/description/
 *
 * @author goodtime
 * @create 2023-11-29 22:27
 */
public class Number97 {
    public static void main(String[] args) {
        String s1 = "a", s2 = "", s3 = "aa";
        System.out.println(new Solution97().isInterleave(s1, s2, s3));
    }
}

/**
 * 核心思想：如果s1和s2交错组成s3,那么s3的从开始到结尾子字符串也是交错组成的
 *
 * dp[i][j]表示s3从0到n的子字符串，可以用s1的字母（0，i)与s2的字母（0，j）交错而成
 *
 * 初始化：dp[0][0] = false  dp[0][i]与dp[i][0]进行子字符串匹配判断
 *
 * 递推公式：dp[i][j] = (dp[i][j-1] && s2[j-1] == s3.charAt(i + j - 1) ) || ( dp[i-1][j] && s1[i-1] == s3.charAt(i + j - 1))
 *
 * 递归顺序：从左到右 从上到下
 */
class Solution97 {
    public boolean isInterleave(String s1, String s2, String s3) {


        if (s3 == null || s3.length() == 0) {
            return true;
        }

        int row = s1.length();
        int column = s2.length();

        boolean[][] dp = new boolean[row + 1][column + 1];

        //简单校验，防止 String s1 = "a", s2 = "", s3 = "aa";的情况被统计为交错
        if(s1.length() + s2.length() != s3.length()){
            return false;
        }

        //初始化
        char c1 = s3.charAt(0);

        for (int i = 1; i < dp.length; i++) {
            dp[i][0] = s3.substring(0, i).equals(s1.substring(0, i));
        }

        for (int j = 1; j < dp[0].length; j++) {
            dp[0][j] = s3.substring(0, j).equals(s2.substring(0, j));
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {


                char s1letter = s1.charAt(i - 1);
                char s2letter = s2.charAt(j - 1);

                if (dp[i - 1][j] && s1letter == s3.charAt(i + j - 1)) {
                    dp[i][j] = true;
                } else {
                    dp[i][j] = dp[i][j - 1] && s2letter == s3.charAt(i + j - 1);
                }
            }

        }

        return dp[row][column];
    }
}