package leetcode.src.main.java.dp;

/**
 * https://leetcode.cn/problems/partition-equal-subset-sum/
 *
 * @author goodtime
 * @create 2023-04-02 17:58
 */
public class Number416 {

    public static void main(String[] args) {
        System.out.println(new Solution416s().canPartition(new int[]{
                1, 5, 11, 5
        }));
    }

}

/**
 * 关键是要想到，可以把数字分成两堆，看一些数字的组合是否能组成数字总和的一半
 *
 * dp[i][j]，用前[0,i]个数是否能装满[j]
 *
 * 二维数组解法
 */
class Solution416 {
    public boolean canPartition(int[] nums) {

        if (nums == null || nums.length <= 1) {
            return false;
        }

        int allCount = 0;

        for (int num : nums) {
            allCount += num;
        }

        if (allCount % 2 != 0) {
            return false;
        }

        int count = allCount / 2;

        int[][] dp = new int[nums.length][count + 1];

        //初始化
        for (int i = 1; i <= count; i++) {
            dp[0][i] = i >= nums[0] ? nums[0] : 0;
        }


        //物品的重量和价值是相等的，所以对于[1,5,11,1]来说，问题转化成，容量为11的背包，每个物品只能装1次，在给定容量里能装的最大价值
        //是否为11
        for (int i = 1; i < nums.length; i++) {//物品
            for (int j = 1; j <= count; j++) {//背包
                if (nums[i] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - nums[i]] + nums[i]);
                    if (dp[i][j] == count) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}

/**
 * 一维数组解法
 */
class Solution416s {
    public boolean canPartition(int[] nums) {

        if (nums == null || nums.length <= 1) {
            return false;
        }

        int allCount = 0;

        for (int i = 0; i < nums.length; i++) {
            allCount += nums[i];
        }

        if (allCount % 2 != 0) {
            return false;
        }

        int count = allCount / 2;

        int[] dp = new int[count + 1];

        //递推,从重背包往轻背包看
        for (int i = 0; i < nums.length; i++) {//物品
            for (int j = count; j >= nums[i]; j--) {//背包

                int noPut = dp[j];
                int put = -1;
                if (j >= nums[i]) {
                    put = dp[j - nums[i]] + nums[i];
                }

                dp[j] = Math.max(noPut, put);

                if (dp[j] == count) {
                    return true;
                }

            }
        }

        return false;

    }
}