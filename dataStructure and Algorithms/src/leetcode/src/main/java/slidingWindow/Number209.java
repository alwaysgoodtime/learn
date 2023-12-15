package leetcode.src.main.java.slidingWindow;

/**
 * https://leetcode.cn/problems/minimum-size-subarray-sum/
 *
 * @author goodtime
 * @create 2023-12-01 04:21
 */
public class Number209 {
    public static void main(String[] args) {
        int target = 7;
        int[] nums = {2, 3, 1, 2, 4, 3};
        System.out.println(new Solution209().minSubArrayLen(target, nums));
    }
}

/**
 * 滑动窗口，两个指针都从0开始，如果sum值小于target，那么右指针往右滑；如果sum值大于等于target，那么左指针往右滑
 */
class Solution209 {
    public int minSubArrayLen(int target, int[] nums) {

        if (nums == null || nums.length == 0) {
            return 0;
        }

        int prePointer = 0;
        int postPointer = 0;
        int sum = nums[0];
        if (sum >= target) {
            return 1;
        }

        int minSubArrayLen = Integer.MAX_VALUE;

        while (prePointer <= postPointer) {
            if (sum >= target) {
                minSubArrayLen = Math.min(postPointer - prePointer + 1, minSubArrayLen);
                sum = sum - nums[prePointer];
                prePointer++;
            } else {
                postPointer++;
                if (postPointer > nums.length - 1) {
                    break;
                }
                sum = sum + nums[postPointer];
            }
        }

        if (minSubArrayLen == Integer.MAX_VALUE) {
            return 0;
        }

        return minSubArrayLen;
    }
}


