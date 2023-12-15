package leetcode.src.main.java.array;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * https://leetcode.cn/problems/majority-element/
 *
 * @author goodtime
 * @create 2023-12-03 19:23
 */
public class Number169 {
    public static void main(String[] args) {
        int[] nums = {2, 2, 1, 1, 1, 2, 2};
    }
}

/**
 * 用hashmap表做辅助，存储每个数的出现次数
 * 最后在遍历一般hashmap，找到出现次数>n/2的即可
 */
class Solution169 {
    public int majorityElement(int[] nums) {

        if (nums == null) {
            return 0;
        }

        if (nums.length == 1) {
            return nums[0];
        }

        HashMap<Integer, Integer> map = new HashMap<>(nums.length);

        for (int i = 0; i < nums.length; i++) {
            map.merge(nums[i], 1, Integer::sum);
        }

        Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();

        int maxCount = nums.length / 2;

        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> next = iterator.next();

            if (next.getValue() > maxCount) {
                return next.getKey();
            }
        }

        return 0;
    }
}
