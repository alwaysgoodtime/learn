package leetcode.src.main.java.section;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/summary-ranges/
 * @author goodtime
 * @create 2023-12-02 10:45
 */
public class Number228 {

    public static void main(String[] args) {
        int[] nums = {0, 1, 2, 4, 5, 7};
        System.out.println(new Solution228().summaryRanges(nums));
    }
}

/**
 * 用两个指针即解决问题
 *
 * 注意：两个int数相减可能溢出范围，可以将其转成long来处理
 *
 */

class Solution228 {
    public List<String> summaryRanges(int[] nums) {

        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }

        List<String> result = new ArrayList<>();

        if (nums.length == 1) {
            result.add(String.valueOf(nums[0]));
            return result;
        }

        int prePointer = nums[0];
        int postPointer = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int number = nums[i];

            if ((long)number - (long)postPointer <= 1L) {
                postPointer = nums[i];
            } else if (prePointer == postPointer) {
                result.add(String.valueOf(prePointer));
                prePointer = nums[i];
                postPointer = nums[i];
            } else {
                String builder = prePointer + "->" + postPointer;
                result.add(builder);
                prePointer = nums[i];
                postPointer = nums[i];
            }
        }

        //将最后一个元素放入结构
        if (prePointer == postPointer) {
            result.add(String.valueOf(prePointer));
        } else {
            String builder = prePointer + "->" + postPointer;
            result.add(builder);
        }

        return result;
    }
}
