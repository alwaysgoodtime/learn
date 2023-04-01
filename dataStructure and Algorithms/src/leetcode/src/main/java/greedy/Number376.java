package leetcode.src.main.java.greedy;

/**
 * https://leetcode.cn/problems/wiggle-subsequence/
 *
 * @author goodtime
 * @create 2023-03-31 21:47
 */
public class Number376 {
    public static void main(String[] args) {

        System.out.println(new Solution376().wiggleMaxLength(new int[]{1, 2, 4, 3}));

    }
}

/**
 * 找到从数组第一个值开头的最长摆动序列长度，就是目标值
 */
class Solution376 {
    public int wiggleMaxLength(int[] nums) {

        if (nums == null) {
            return 0;
        }

        if (nums.length <= 1) {
            return nums.length;
        }


        return getValidMaxLength(nums, 0);

    }

    private int getValidMaxLength(int[] nums, int startIndex) {

        int length = 1;

        int preDiff = 0;
        int diff = 0;

        int pre = nums[startIndex];

        //一小一大，length + 1，无论是不是摆动序列，都可以把摆动序列的最前下标更新为遍历到的最新下标
        //如果是摆动序列，最前下标自然要更新
        //不如不是摆动序列，说明是 a < 最前下标(b) < 最新下标（c） , 或者 a > 最前下标（b）> 最新下标（c）
        //那么把摆动序列的下标从b更新到c，都有利于找到下一次向上或向下地摆动。
        for (int j = startIndex + 1; j <= nums.length - 1; j++) {

            diff = nums[j] - pre;

            if ((preDiff == 0 && diff != 0) || (preDiff < 0 && diff > 0) || (preDiff > 0 && diff < 0)) {
                preDiff = diff;
                length++;
            }

            pre = nums[j];
        }

        return length;
    }

}
