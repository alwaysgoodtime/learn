package leetcode.src.main.java.binarySearch;

import java.util.Arrays;

/**
 * @author goodtime
 * @create 2023-12-08 00:40
 */
public class Number34 {
    public static void main(String[] args) {
        int[] nums = {5, 7, 7, 8, 8, 10};
        System.out.println(Arrays.toString(new Solution34().searchRange(nums, 6)));
    }
}

/**
 * 可用红蓝染色法，二分查找两次，第一次红的范围是>=target,蓝的范围是<target，查找范围为[0,nums.length-1];
 * 第二次红的范围是>target,蓝的范围是==target,查找范围是[第一次蓝下标+1，nums.length-1];
 */
class Solution34 {
    public int[] searchRange(int[] nums, int target) {

        int[] result = {-1, -1};

        if (nums == null || nums.length == 0) {
            return result;
        }

        int l = -1, r = nums.length;

        //第一次找寻
        while (l + 1 != r) {
            int m = (l + r) / 2;
            if (nums[m] < target) {
                l = m;
            } else {
                r = m;
            }
        }

        //此时l为<target的最后一个下标，r为>=target的第一个下标
        //如果l为nums.length -1, 不需要下一步，因为整个数组都<target
        if (l == nums.length - 1) {
            return result;
        }

        int l2 = l;
        int r2 = nums.length;

        while (l2 + 1 != r2){
            int m = (l2 + r2) / 2;
            if(nums[m] == target){
                l2 = m;
            }else {
                r2 = m;
            }
        }

        //此时l为<target的最后一个下标，l2为==target的最后一个下标
        //如果l=l2,那么说明没有数字==target
        if(l2 == l){
            return result;
        }

        result[0] = l + 1;
        result[1] = l2;

        return result;

    }

}
