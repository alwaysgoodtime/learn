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
 * <p>
 * 二维数组解法
 */
class Solution416 {
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

        int[][] dp = new int[nums.length][count + 1];

        //初始化第一列
        for (int i = 0; i < nums.length; i++) {
            dp[i][0] = 0;

        }

        //初始化第一行
        for (int i = 1; i <= count; i++) {
            dp[0][i] = i >= nums[0] ? nums[0] : 0;
        }

        //递推
        for (int i = 1; i < nums.length; i++) {//物品
            for (int j = 1; j <= count; j++) {//背包

                int noPut = dp[i - 1][j];
                int put = -1;
                if (j >= nums[i]) {
                    put = dp[i - 1][j - nums[i]] + nums[i];
                }

                dp[i][j] = Math.max(noPut, put);

                if (dp[i][j] == count) {
                    return true;
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