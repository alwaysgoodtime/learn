package leetcode.src.main.java.dp;

/**
 * https://leetcode.cn/problems/uncrossed-lines/
 *
 * @author goodtime
 * @create 2023-04-06 04:29
 */
public class Number1035 {
    public static void main(String[] args) {
        System.out.println(new Solution1035().maxUncrossedLines(new int[]{
                1, 4, 2
        }, new int[]{1, 2, 4}));
    }
}

/**
 *
 * 关键是要意识到，这道题是要找两个数组的最长公共子序列（可以不连续，但不能打乱顺序）
 *
 * 基本和number1143相同,还是求最长公共子序列
 *
 * dp[i][j] [0,i-1]的nums1和[0,j-1]的nums2最长子序列
 *
 * 递推公式：
 *
 * 如果 nums1[i-1] = nums2[j-1] dp[i][j] = dp[i-1][j-1] + 1;
 * 如果 nums1[i-1] != nums2[j-1] dp[i][j] = max(dp[i-1][j], dp[i][j-1])
 *
 * 初始化： dp[0][j] = 0 dp[i][0] = 0
 */
class Solution1035 {
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
            return 0;
        }

        int[][] dp = new int[nums1.length + 1][nums2.length + 1];

        for (int i = 1; i <= nums1.length; i++) {
            for (int j = 1; j <= nums2.length; j++) {
                if(nums1[i-1] == nums2[j-1]){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        return dp[nums1.length][nums2.length];


    }
}