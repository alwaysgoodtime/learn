package leetcode.src.main.java.binarySearch;

/**
 * https://leetcode.cn/problems/find-minimum-in-rotated-sorted-array/
 * @author goodtime
 * @create 2023-12-08 01:20
 */
public class Number153 {
    public static void main(String[] args) {
        int[] nums = {3, 4, 5, 1, 2};
        System.out.println(new Solution153().findMin(nums));
    }
}

/**
 * 二分查找，关键是想明白当中间值<2边最小值，和中间值>2边最小值时，该如何走
 */
class Solution153 {
    public int findMin(int[] nums) {

        if (nums == null || nums.length == 0) {
            return -1;
        }

        if (nums.length == 1) {
            return nums[0];
        }


        int l = 0;
        int r = nums.length - 1;
        int min = Math.min(nums[l], nums[r]);

        while (l + 1 != r) {
            int m = (l + r) / 2;

            //往左找
            if (nums[m] < min) {
                min = nums[m];
                r = m;
            } else {
                l = m;
            }
        }

        return min ;
    }
}
