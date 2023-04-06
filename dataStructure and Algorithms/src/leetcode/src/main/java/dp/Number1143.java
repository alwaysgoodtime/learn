package leetcode.src.main.java.dp;

/**
 * https://leetcode.cn/problems/longest-common-subsequence/
 *
 * @author goodtime
 * @create 2023-04-06 03:11
 */
public class Number1143 {

    public static void main(String[] args) {
        System.out.println(new Solution1143().longestCommonSubsequence("abcde", "ace"));
    }
}

/**
 * 滚动数组，其实是二维数组压缩而成
 *
 * dp[i] 含义：[0,i-1]的text2和text1的最长公共后缀
 *
 * 递推公式：dp[i] = dp[j] + 1 (如果 text1[i-1] = text2[j-1]， 0<=j<=i)
 *
 * 初始化：dp[i] = 0
 *
 * 遍历顺序：先遍历text1，遍历text2时，如果是压缩后的一维数组，必须从后往前（因为如果一个元素在text2中多次出现时，从前到后会统计多次）
 */
class Solution1143 {
    public int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) {
            return 0;
        }

        char[] chars1 = text1.toCharArray();
        char[] chars2 = text2.toCharArray();

        int[] dp = new int[chars2.length + 1];


        for (int i = 0; i < chars1.length; i++) {
            for (int j = chars2.length; j >= 1; j--) {
                if (chars1[i] == chars2[j-1]) {
                    int max = 0;
                    for (int k = 1; k < j; k++) {
                        max = Math.max(dp[k], max);
                    }
                    dp[j] = max + 1;
                }
            }
        }

        int result = 0;
        for (int i = 1; i <= chars2.length; i++) {
            result = Math.max(result, dp[i]);
        }

        return result;
    }
}
