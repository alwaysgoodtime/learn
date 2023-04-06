package leetcode.src.main.java.dp;

/**
 * https://leetcode.cn/problems/maximum-length-of-repeated-subarray/
 *
 * @author goodtime
 * @create 2023-04-05 23:25
 */
public class Number718 {

    public static void main(String[] args) {
        System.out.println(new Solution718().findLength(new int[]{
                1, 2, 3, 2, 1
        }, new int[]{3, 2, 1, 4, 7}));
    }

}

/**
 * 第一种动态规划
 *
 * dp[i][j]含义： [0,i]和[0,j]数组的最长重复子数组
 * 递推公式： dp[i][j] = dp[i][j-1] or dp[i][j-1]+1 （如果nums1[i] = nums2[j] && nums1[i-1] == nums2[j-1] ...）
 * 初始化：见代码
 *
 * 第二种动态规划
 *
 * dp[i][j]含义: 以nums1[i-1]为结尾的数组和以nums2[j-1]为结尾的数组的最长重复子数组（使用i-1和j-1，是为了初始化方便
 * 而且最长重复子数组必须以nums1[i-1]或nums2[j-1]为结尾）
 * 递推公式：dp[i][j] = dp[i-1][j-1] + 1; (如果nums1[i-1]= nums2[j-1])
 * 初始化：dp[0][j] = 0; dp[i][0] = 0
 */
class Solution718 {
    public int findLength(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
            return 0;
        }

        int[][] dp = new int[nums1.length][nums2.length];

        //初始化
        dp[0][0] = nums1[0] == nums2[0] ? 1 : 0;
        for (int i = 1; i < nums2.length; i++) {
            int equal = nums1[0] == nums2[i] ? 1 : 0;
            dp[0][i] = Math.max(dp[0][i - 1], equal);
        }


        //递推
        for (int i = 1; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                if (j == 0) {

                    if (dp[i - 1][nums2.length - 1] == 0 && nums1[i] == nums2[0]) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = dp[i - 1][nums2.length - 1];
                    }

                } else {

                    dp[i][j] = dp[i][j - 1];

                    if (nums1[i] == nums2[j] && dp[i][j] <= j) {
                        int count = 1;
                        int k = i - 1;
                        int l = j - 1;
                        while (k >= 0 && l >= 0 && nums1[k] == nums2[l]) {
                            count++;
                            k--;
                            l--;
                        }

                        if (count == dp[i][j - 1] + 1) {
                            dp[i][j] += 1;
                        }
                    }

                }
            }
        }
        return dp[nums1.length - 1][nums2.length - 1];

    }

    public int findLengths(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
            return 0;
        }

        int[][] dp = new int[nums1.length + 1][nums2.length + 1];


        //递推
        int result = 0;

        for (int i = 1; i < nums1.length; i++) {
            for (int j = 1; j < nums2.length; j++) {
                if (nums1[i] == nums2[j]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    result = Math.max(dp[i][j], result);
                }
            }
        }
        return result;

    }
}