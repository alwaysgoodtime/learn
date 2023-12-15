package leetcode.src.main.java.binarySearch;

/**
 * @author goodtime
 * @create 2023-12-07 22:14
 */
public class Number162 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 3, 5, 6, 4};
        System.out.println(new Solution162().findPeakElement(nums));
    }
}

/**
 * 本题的数组并非有序数组，直觉的解法是遍历一遍数组，总能找到解，或者说直接找到数组最大值，最大值肯定满足条件，而不能用二分查找
 *
 * 但是题目规定，需要用O(logn)的复杂度
 *
 * 所以需要想如何运用起二分。
 *
 * 如果我们从一个位置开始，不断地向高处走，那么最终一定可以到达一个峰值位置。这个位置可以是数组中的任一位置
 *
 * 因此，我们可以用二分法进行上述的位置选取，选取中点，如果中点左边<中点，中点>中点右边，那么中点就是所需位置
 *
 * 如果中点左边<中点，中点右边>中点，按照思维是往右找，那么我们就找中点与r的中点进行此过程
 *
 * 如果中点左边>中点，中点右边<中点，按照思维是往左找，那么我们就找l与中点的中点和中点进行此过程
 *
 * 如果中点既小于左边又小于右边，那么我们将其往右挪，因为两边都存在这类峰值点。
 *
 * 注意：题目要求数组的元素都不相同，可以不考虑相等的情况
 */
class Solution162 {
    public int findPeakElement(int[] nums) {

        if (nums == null || nums.length == 0) {
            return -1;
        }

        //只有一个元素，那么该元素就满足条件
        if (nums.length == 1) {
            return 0;
        }


        int l = -1, r = nums.length;


        while (l + 1 != r) {

            int m = (l + r) / 2;

            //此种情况下，一定已经验证过了nums.length-2才到达这里，而该点一定>右边的值，所以他就是要求的峰值点
            if (m == nums.length - 1) {
                return m;
            }

            if (nums[m] > nums[m + 1] && (m == 0 || nums[m] > nums[m - 1])) {
                return m;
            }

            if (nums[m] < nums[m + 1]) {
                l = m;
            } else {
                r = m;
            }

        }

        //一个数组一定有峰值点，所以这里是不可能抵达的地方
        return -1;

    }
}
