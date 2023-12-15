package leetcode.src.main.java.array;

/**
 * https://leetcode.cn/problems/remove-duplicates-from-sorted-array/description/
 *
 * @author goodtime
 * @create 2023-12-03 19:03
 */
public class Number26 {
    public static void main(String[] args) {
        int[] nums = {1, 1, 2};
        new Solution26().removeDuplicates(nums);
    }
}

/**
 * 双指针，后面的指针1步1格往里放不重复的元素
 * 前面的指针当做探测器，探测元素让后面的指针放
 */
class Solution26 {

    public int removeDuplicates(int[] nums) {

        if (nums == null) {
            return 0;
        }

        if (nums.length <= 1) {
            return nums.length;
        }

        //默认第一个元素是不会重复的元素
        int slowPointer = 0;
        int fastPointer = 1;

        while (fastPointer < nums.length) {

            if (nums[fastPointer] != nums[slowPointer]) {
                slowPointer++;
                nums[slowPointer] = nums[fastPointer];
            }

            fastPointer++;
        }

        return slowPointer + 1;

    }
}