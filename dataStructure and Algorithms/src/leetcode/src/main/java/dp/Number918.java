package leetcode.src.main.java.dp;


import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.cn/problems/maximum-sum-circular-subarray/
 *
 * @author goodtime
 * @create 2023-12-16 11:22
 */
public class Number918 {
    public static void main(String[] args) {
        System.out.println(new Solution918().maxSubarraySumCircularDP(new int[]{-3, -2, -3}));
    }
}

/**
 * 相比于Number53,现在数组可以环形
 *
 * dp做法：结果有两个可能，一种是类似Number53的情况，在数组中正常取一段值，这里的dp公式与其相同
 *
 * 另一种可能是数组的尾巴+一段数组的前缀拼接而成
 *
 * 还有一种做法：该问题可以转换为：求解以nums[i]开始，长度不超过n的最大连续子数组和
 * 问题进一步转换为，长度为2n的数组里，j的前缀和-i的前缀和的最大值，其中j>i,且j-i<=n
 */
class Solution918 {


    /**
     * 队列做法
     *
     * @param nums
     * @return
     */
    public int maxSubarraySumCircular(int[] nums) {

        if (nums == null || nums.length == 0) {
            return 0;
        }

        if (nums.length == 1) {
            return nums[0];
        }

        int n = nums.length;

        Deque<int[]> deque = new ArrayDeque<>();

        deque.addFirst(new int[]{0, nums[0]});
        int preSum = nums[0];
        int result = nums[0];

        for (int i = 1; i < n * 2; i++) {

            while (deque.size() != 0 && deque.peekFirst()[0] < i - n) {
                deque.pollFirst();
            }

            preSum += nums[i % n];
            //为什么只用考虑preSum减去第一个元素的前缀和，而不考虑其他元素的前缀和情况呢？
            //答案是：deque通过下面一步的弹出比preSum大的值的处理，已经形成了一个递增序列
            //所以preSum减去第一个元素前缀和，所得的值是最大的，与其他元素的差值没有必要考虑了
            result = Math.max(result, preSum - deque.peekFirst()[1]);


            while (deque.size() != 0 && deque.peekLast()[1] >= preSum) {
                deque.pollLast();
            }

            deque.addLast(new int[]{i, preSum});
        }

        return result;
    }


    public int maxSubarraySumCircularDP(int[] nums) {

        if (nums == null || nums.length == 0) {
            return 0;
        }

        if (nums.length == 1) {
            return nums[0];
        }

        //dp[i]含义，以在0到dp[i]范围的最大前缀和
        int[] dp = new int[nums.length];

        //前缀和
        int preSum = nums[0];
        dp[0] = nums[0];

        for (int i = 1; i < dp.length; i++) {
            preSum += nums[i];
            dp[i] = Math.max(dp[i - 1], preSum);
        }

        int[] postDP = new int[nums.length];

        //后缀和
        int postSum = nums[nums.length - 1];
        postDP[nums.length - 1] = nums[nums.length - 1];

        for (int i = nums.length - 2; i >= 0; i--) {
            postSum += nums[i];
            postDP[i] = Math.max(postDP[i + 1], postSum);
        }

        //组合两者
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length - 1; i++) {
            if (max < dp[i] + postDP[i + 1]) {
                max = dp[i] + postDP[i + 1];
            }
        }

        if (dp[0] > max) {
            max = dp[0];
        }

        //之前的第一种情形，即在数组中间取值，复用dp数组即可
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            if (dp[i] > max) {
                max = dp[i];
            }
        }

        return max;

    }
}