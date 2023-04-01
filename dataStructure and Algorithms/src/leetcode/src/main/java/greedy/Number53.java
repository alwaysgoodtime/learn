package leetcode.src.main.java.greedy;

/**
 * @author goodtime
 * @create 2023-03-31 22:55
 */
public class Number53 {
    public static void main(String[] args) {
        System.out.println(new Solution53().maxSubArray(new int[]{1, 2, 3}));
    }
}

/**
 * 贪心版本
 */
class Solution53 {
    public int maxSubArray(int[] nums) {

        if(nums == null || nums.length == 0){
            return 0;
        }

        int maxValue = nums[0];
        int value = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (value > 0) {
                value += nums[i];
            } else {
                value = nums[i];
            }

            if (maxValue < value) {
                maxValue = value;
            }
        }

        return maxValue;
    }
}
