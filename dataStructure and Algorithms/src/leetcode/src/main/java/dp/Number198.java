package leetcode.src.main.java.dp;

/**
 * https://leetcode.cn/problems/house-robber/
 *
 * @author goodtime
 * @create 2023-04-04 16:20
 */
public class Number198 {
    public static void main(String[] args) {
        System.out.println(new Solution198().rob(new int[]{
                1, 5, 3, 1, 3
        }));
    }
}

/**
 * 打家劫舍
 *
 * 二维数组做法
 * dp[i][j]含义：在[0,i]房屋任选房屋打劫，如果共打劫j所房屋，所能得到的最大金额
 * 递推公式：dp[i][j] = max(dp[i-1][j], value(i) + dp[i-2][j-1])
 * 初始化：dp[i][0] = 0  dp[0][j] = value(第一所屋子) （j>=1）
 * <p>
 * 一维数组做法
 * dp[i]含义：在[0,i]房屋任选房屋打劫，所能得到的最大金额
 * 递推公式：dp[i] = max(dp[i-2]+ value(i), dp[i-1])
 * 初始化：dp[0]=nums[0] dp[1]= max(dp[0], nums[1])
 */
class Solution198 {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int length = nums.length;

        int maxHouseCount = 0;
        if (length % 2 == 0) {
            maxHouseCount = length / 2;
        } else {
            maxHouseCount = length / 2 + 1;
        }


        int[][] dp = new int[nums.length][maxHouseCount + 1];

        //初始化第一列
        for (int i = 0; i < nums.length; i++) {
            dp[i][0] = 0;
        }

        //初始化第一行
        for (int i = 1; i <= maxHouseCount; i++) {
            dp[0][i] = nums[0];
        }

        //递推公式
        for (int i = 1; i < nums.length; i++) {//房子
            for (int j = 1; j <= maxHouseCount; j++) {//打劫数

                int beforeTwo = 0;

                if (i >= 2) {
                    beforeTwo = dp[i - 2][j - 1];
                }

                //打劫本房屋
                int rob = nums[i] + beforeTwo;

                //不打劫本房屋
                int noRob = dp[i - 1][j];

                dp[i][j] = Math.max(rob, noRob);
            }
        }

        return dp[nums.length - 1][maxHouseCount];
    }

    /**
     * 一维数组做法
     *
     * @param nums
     * @return
     */
    public int robs(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        if (nums.length == 1) {
            return nums[0];
        }

        int length = nums.length;


        int[] dp = new int[length];

        //初始化
        dp[0] = nums[0];
        dp[1] = Math.max(nums[1], dp[0]);

        //递推公式
        for (int i = 2; i < nums.length; i++) {

            int rob = dp[i - 2] + nums[i];
            int noRob = dp[i - 1];

            dp[i] = Math.max(rob, noRob);

        }

        return dp[nums.length - 1];
    }
}