package leetcode.src.main.java.greedy;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

/**
 * https://leetcode.cn/problems/maximize-sum-of-array-after-k-negations/
 *
 * @author goodtime
 * @create 2023-04-01 00:14
 */
public class Number1005 {
    public static void main(String[] args) {
        System.out.println(new Solution1005().largestSumAfterKNegations(new int[]{1, 2, -3, -1}, 1));
    }
}

/**
 * 如果nums中有负数，先把最小的负数翻转正数
 * 如果nums全是正数或0，翻转最小的即可，
 * 总的来看，只有不停翻转最小的数就行
 */
class Solution1005 {
    public int largestSumAfterKNegations(int[] nums, int k) {
        Arrays.sort(nums);
        for (int i = 0; i < k; i++) {
            nums[0] = -nums[0];
            Arrays.sort(nums);
        }

        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }

        return sum;
    }
}

/**
 * 优化一下性能，按照绝对值排序
 */
class Solution1005s {
    public int largestSumAfterKNegations(int[] nums, int k) {

        Integer[] num = new Integer[nums.length];

        for (int i = 0; i < num.length; i++) {
            num[i] = nums[i];
        }

        //绝对值排序
        Arrays.sort(num, Comparator.comparingInt(Math::abs));

        //先消耗绝对值大的负数，再把负数全转成正数
        for (int i = num.length - 1; i >= 0; i--) {
            if (num[i] < 0 && k > 0) {
                num[i] = -num[i];
                k--;
                //保证num[0]是数组自然数中最小的
                if(num[0] > num[i]){
                    int tmp = num[i];
                    num[i] = num[0];
                    num[0] = tmp;
                }
            }
        }

        //如果把整个数组都转成了正数，而且k是偶数，就不用管了，如果是奇数，就把第一个数变成负数（也可能是0）
        if(k % 2 == 1) {
            num[0] = -num[0];
        }

        int sum = 0;

        for (int i = 0; i < num.length; i++) {
            sum += num[i];
        }

        return sum;
    }
}