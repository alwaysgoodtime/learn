package leetcode.src.main.java.doublePointer;

import java.util.Arrays;

/**
 * @author goodtime
 * @create 2024-01-12 00:01
 */
public class Number283 {
    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 3, 12};
        new Solution283().moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }
}

/**
 * 快慢指针，两个指针遇到非0值往前，遇到0值，快指针往前，慢指针停下
 */
class Solution283 {
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        int slowPointer = 0;
        int fastPointer = 0;

        while (fastPointer < nums.length) {

            if (nums[fastPointer] != 0) {
                if (slowPointer != fastPointer) {
                    int tmp = nums[slowPointer];
                    nums[slowPointer] = nums[fastPointer];
                    nums[fastPointer] = tmp;
                }
                slowPointer++;
            }
            fastPointer++;
        }

    }
}
