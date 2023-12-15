package leetcode.src.main.java.hashmap;

import java.util.HashMap;

/**
 * @author goodtime
 * @create 2023-11-30 23:53
 */
public class Number219 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};
        int k = 3;
        System.out.println(new Solution219().containsNearbyDuplicate(nums, k));
    }
}

/**
 * hashmap key为nums[i],value为上一次nums[i]出现的下标,时间复杂度n
 */
class Solution219 {
    public boolean containsNearbyDuplicate(int[] nums, int k) {

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {

            int num = nums[i];

            Integer index = map.get(num);

            if (index != null && Math.abs(i - index) <= k) {
                return true;
            }
            map.put(num, i);
        }

        return false;

    }
}
