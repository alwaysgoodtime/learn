package leetcode.src.main.java.hashmap;

import java.util.HashMap;

/**
 * 还可以用hashmap存储
 *
 * @author goodtime
 * @create 2022-05-10 9:52 下午
 */
public class Number1 {

    public static void main(String[] args) {
        int[] nums = {-10,-8,-2,1,2,5,6};
        int target = 0;
        System.out.println(new Solution1().twoSum22(nums, target));
    }

}

class Solution1 {

    /**
     * 暴力解法，两层for遍历
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        int[] a = new int[2];
        int i = 0;
        int j = nums.length - 1;
        for (; i < j; j--) {
            for (; i < j; i++) {
                if (nums[i] + nums[j] == target) {
                    a[0] = i;
                    a[1] = j;
                    return a;
                }
            }
            i = 0;
        }
        return null;
    }

    public int[] twoSumHash(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] a = new int[2];
        for (int i = 0; i < nums.length; i++) {
            int key = nums[i];
            if (map.containsKey(target - key)) {
                a[0] = i;
                a[1] = map.get(target - key);
                return a;
            }
            map.put(key, i);
        }
        return null;
    }

    /**
     * 有序的numbers可以用双指针，来自剑指offer
     * 双指针一般一个指头一个指尾，也有时用快慢指针，此题不是
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum22(int[] numbers, int target) {

        int prePointer = 0;
        int postPointer = numbers.length-1;
        int[] result = new int[2];

        while (prePointer < postPointer && postPointer < numbers.length) {

            int number1 = numbers[prePointer];
            int number2 = numbers[postPointer];

            if (number1 + number2 == target) {
                result[0] = prePointer + 1;
                result[1] = postPointer + 1;
                return result;
            } else if (number1 + number2 > target) {
                postPointer--;
            } else {
                prePointer++;
            }

        }

        return result;
    }
}