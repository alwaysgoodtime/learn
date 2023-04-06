package leetcode.src.main.java.dp;

/**
 * https://leetcode.cn/problems/palindromic-substrings/
 *
 * @author goodtime
 * @create 2023-04-06 07:24
 */
public class Number647 {
    public static void main(String[] args) {
        System.out.println(new Solution647().countSubstrings("aaa"));
    }
}

/**
 *
 * 第一种：
 *
 * dp[i]含义: s的[0,i]子串中回文子串的数目
 *
 * 递推公式： dp[i] = 1 + isPalindrome(s[n,i+1])(n>=0 && n<i-1) + dp[i-1]
 *
 * 初始化：dp[0] = 1
 *
 * 第二种：
 *
 * dp[i][j]含义：s的[i,j]子串是否为回文子串
 *
 * 递推公式：if(s[i] == s[j]) {  if(j-i<=1)   dp[i][j]= true
 *                             else if( dp[i+1][j-1] = true) dp[i][j] = true}
 *
 *
 * 初始化：所有dp[i][j]全部设置成false
 *
 * 遍历顺序：从下往上，从左往右
 *
 */
class Solution647 {
    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        boolean[][] dp = new boolean[s.length()][s.length()];



        int result = 0;

        //递推
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i; j < s.length(); j++) {

                if (s.charAt(i) == s.charAt(j)) {

                    if (j - i <= 1) {
                        dp[i][j] = true;
                        result++;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                        if (dp[i + 1][j - 1]) {
                            result++;
                        }
                    }

                }
            }
        }

        return result;

    }

}