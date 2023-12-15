package leetcode.src.main.java.binarySearch;

/**
 * https://leetcode.cn/problems/search-insert-position/
 *
 * @author goodtime
 * @create 2023-12-07 20:39
 */
public class Number35 {
    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 7};
        System.out.println(new Solution35().searchInsert(nums, 5));
    }
}

/**
 * 二分查找
 */
class Solution35 {
    public int searchInsert(int[] nums, int target) {
        //1.红蓝区域 蓝：<=target 红>target
        //2.返回的是l
        //3.模板
        //4.后续处理（如果l为-1 或 l所指的数 != target，返回l+1, 其他情况返回l）
        if (nums == null) {
            return 0;
        }
        int l = -1, r = nums.length;
        while (l + 1 != r) {
            int m = (l + r) / 2;
            if (nums[m] <= target) {
                l = m;
            } else {
                r = m;
            }
        }

        if (l == -1 || nums[l] != target) {
            return l + 1;
        } else {
            return l;
        }


    }
}