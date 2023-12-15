package leetcode.src.main.java.array;

/**
 * https://leetcode.cn/problems/product-of-array-except-self/
 *
 * @author goodtime
 * @create 2023-12-03 21:51
 */
public class Number238 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        System.out.println(new Solution238().productExceptSelf(nums));
    }
}

/**
 * 因为不可以使用除法，就不能使用把nums的元素全乘起来，再挨个除以各自元素的方法(而且遇到0也会失效)，但又要求时间复杂度为n，不能使用暴力解法
 *
 * 核心是认识到1个数除自身外的乘积，为它左边数的乘积，乘以它右边数的乘积，于是用两个列表分别存储这些乘积即可。
 */
class Solution238 {
    public int[] productExceptSelf(int[] nums) {

        if (nums == null || nums.length == 0) {
            return nums;
        }

        if(nums.length == 1){
            int[] result = {1};
            return result;
        }

        //左边数的乘积，结果也放入其中
        int[] numsLeft = new int[nums.length];
        //右边数的乘积
        int[] numsRight = new int[nums.length];

        numsLeft[0] = 1;
        numsRight[nums.length-1] = 1;

        for (int i = 1; i < nums.length; i++) {
            numsLeft[i] = numsLeft[i-1] * nums[i - 1];
        }

        for (int i = nums.length - 2 ;  i >= 0 ; i--) {
            numsRight[i] = numsRight[i + 1] * nums[i + 1];
        }

        for (int i = 0; i < nums.length; i++) {
            numsLeft[i] = numsLeft[i] * numsRight[i];
        }

        return numsLeft;
    }

    /**
     * 空间复杂度为1的版本，核心还是上面方法的优化
     */
    public int[] productExceptSelfAdvanced(int[] nums) {

        if (nums == null || nums.length == 0) {
            return nums;
        }

        if(nums.length == 1){
            int[] result = {1};
            return result;
        }

        //左边数的乘积，结果也放入其中
        int[] numsLeft = new int[nums.length];

        numsLeft[0] = 1;

        for (int i = 1; i < nums.length; i++) {
            numsLeft[i] = numsLeft[i-1] * nums[i - 1];
        }


        int numsRight = 1;

        //这次倒着装结果
        for (int i = nums.length - 2 ; i >= 0; i--) {
            numsRight = numsRight * nums[i+1];
            numsLeft[i] = numsLeft[i] * numsRight;
        }

        return numsLeft;
    }
}
