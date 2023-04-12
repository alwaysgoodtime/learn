package leetcode.src.main.java.ms;

import java.util.Arrays;
import java.util.Stack;

/**
 * https://leetcode.cn/problems/next-greater-element-ii/
 *
 * @author goodtime
 * @create 2023-04-06 16:23
 */
public class Number503 {

    public static void main(String[] args) {
        int[] result = new Solution503().nextGreaterElements(new int[]{1, 2, 1});
        System.out.println(result[0]);
    }
}

/**
 * 多循环一次
 */
class Solution503 {
    public int[] nextGreaterElements(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        }

        Stack<Integer> ms = new Stack<>();
        ms.push(0);

        int[] result = new int[nums.length];
        Arrays.fill(result, -1);

        for (int i = 1; i < nums.length; i++) {

            while (!ms.empty()) {
                Integer peek = ms.peek();
                if (nums[peek] < nums[i]) {
                    result[peek] = nums[i];
                    ms.pop();
                } else {
                    break;
                }
            }

            ms.push(i);
        }

        if (ms.empty()) {
            return result;
        }

        //多循环一次，这一次只出栈不入栈（也可正常入栈出栈，只是这样不用费脑子分析数值可能会覆盖的情况）
        for (int i = 0; i < nums.length; i++) {

            while (!ms.empty()) {
                Integer peek = ms.peek();
                if (nums[peek] < nums[i]) {
                    result[peek] = nums[i];
                    ms.pop();
                } else {
                    break;
                }
            }

        }

        return result;

    }
}