package leetcode.src.main.java.doublePointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.cn/problems/3sum/description/
 *
 * @author goodtime
 * @create 2023-12-01 03:14
 */
public class Number15 {
    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        System.out.println(new Solution15().threeSum(nums));
    }
}


/**
 * 核心是固定一个数，那么问题就变为了两数之和
 *
 * 第一种是可以用for循环内层嵌套哈希表解两数之和的方法来实现，但去重逻辑会很复杂
 *
 * 第二种是先对nums做排序，再用双指针来完成这一点，去重逻辑通过如下两步来完成
 */
class Solution15 {
    public List<List<Integer>> threeSum(int[] nums) {

        if (nums == null || nums.length == 0) {
            return null;
        }

        Arrays.sort(nums);

        List<List<Integer>> result = new ArrayList<>();

        //前一个处理的数
        int preNum = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {

            int num = nums[i];

            //去重第一步，举例：[-1,-1,1,1,2]，如果第一个-1与第一个1加和为0并进入结果集，那么无需考察第二个-1，即使有加和为0的情况
            //第一个-1也都已经考虑到了
            if (preNum == num) {
                continue;
            } else {
                preNum = num;
            }

            int prePointer = i + 1;
            int postPointer = nums.length - 1;
            //其他两数之和需要为sum
            int sum = -num;

            while (prePointer < postPointer) {

                int numPre = nums[prePointer];
                int numPost = nums[postPointer];

                if (numPre + numPost == sum) {
                    List<Integer> element = new ArrayList<>();
                    element.add(num);
                    element.add(numPre);
                    element.add(numPost);
                    result.add(element);

                    //去重第二步，跳过因重复元素导致频繁命中解，举例：-1，-1，1，,1，,2 第一次是第1个-1与第2个1命中
                    //那么就需要把postPointer滑过第一个1，滑至第二个-1  注意不要溢出边界
                    while (postPointer >= 1 && nums[postPointer] == nums[postPointer - 1]) {
                        postPointer--;
                    }

                    postPointer--;

                } else if (numPre + numPost < sum) {
                    prePointer++;
                } else {
                    postPointer--;
                }

            }

        }

        return result;
    }
}
