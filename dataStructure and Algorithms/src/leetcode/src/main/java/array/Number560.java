package leetcode.src.main.java.array;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/subarray-sum-equals-k/
 *
 * @author goodtime
 * @create 2024-01-12 18:56
 */
public class Number560 {
    public static void main(String[] args) {
        System.out.println("test");
    }
}

/**
 * 前缀和
 */
class Solution560 {
    public int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int count = 0;

        Map<Integer, Integer> map = new HashMap<>();

        //前缀和一般要放一个0，1的element
        map.put(0, 1);

        int sum = 0;

        for (int i = 0; i < nums.length; i++) {

            sum += nums[i];

            //即[0,i]的前缀和sum 减去 k，所得的差，是否有[0,j]的前缀和与其对应，这样 [j,i] 就是一个满足条件的数组
            //其中 j <= i，所以一定要从左往右遍历
            if (map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }

            map.merge(sum, 1, Integer::sum);
        }

        return count;
    }
}