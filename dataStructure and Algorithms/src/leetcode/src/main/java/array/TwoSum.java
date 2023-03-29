package array;

import java.util.HashMap;

/**
 * 还可以用hashmap存储
 *
 * @author goodtime
 * @create 2022-05-10 9:52 下午
 */
public class TwoSum {
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
}