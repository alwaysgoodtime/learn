package leetcode.src.main.java.hashmap;

import java.util.HashMap;

/**
 * https://leetcode.cn/problems/longest-consecutive-sequence/
 *
 * @author goodtime
 * @create 2023-12-01 00:09
 */
public class Number128 {
    public static void main(String[] args) {
        int[] nums = {100, 4, 200, 1, 3, 2};
        System.out.println(new Solution128().longestConsecutive(nums));
    }
}

/**
 * hashmap key为nums[i], value为1
 * 核心在于，先把数组排到hashmap里，如果在hashmap中，有比nums[i]小1的key存在，那么跳过这个nums[i]即可，这样每个数只需遍历一次
 */
class Solution128 {
    public int longestConsecutive(int[] nums) {


        if (nums == null || nums.length == 0) {
            return 0;
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        int longestConsecutive = 1;

        for (int i = 0; i < nums.length; i++) {
            int number = nums[i];
            map.put(number, 1);
        }

        for (int i = 0; i < nums.length; i++) {
            int number = nums[i];
            if (map.containsKey(number - 1)) {
                continue;
            } else {
                int length = 1;
                while (map.containsKey(number + 1)) {
                    number++;
                    length++;
                }

                if (longestConsecutive < length) {
                    longestConsecutive = length;
                }
            }

        }


        return longestConsecutive;

    }
}