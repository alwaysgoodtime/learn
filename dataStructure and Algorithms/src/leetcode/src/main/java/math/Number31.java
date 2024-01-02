package leetcode.src.main.java.math;

/**
 * https://leetcode.cn/problems/next-permutation
 *
 * @author goodtime
 * @create 2024-01-01 23:57
 */
public class Number31 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        new Solution31().nextPermutation(nums);
    }
}


class Solution31 {


    /**
     * 更优雅的方法，利用的数学知识
     */

    public void nextPermutationAdvanced(int[] nums) {

        if (nums == null || nums.length <= 1) {
            return;
        }

        int exchangeIndex = -1;

        //1.从后往前遍历，找到第一个高位比低位小的数
        for (int i = nums.length - 1; i > 0; i--) {

            if (nums[i] > nums[i - 1]) {
                exchangeIndex = i - 1;
                break;
            }


        }

        //如果为-1，说明是递减序列，需要将整个数组全部旋转，直接跳到第四步即可
        if (exchangeIndex != -1) {
            int targetIndex = -1;

            //2.从后往前遍历，找到后面第一个比exchangeIndex的数大的数
            for (int i = nums.length - 1; i > exchangeIndex; i--) {
                if (nums[i] > nums[exchangeIndex]) {
                    targetIndex = i;
                    break;
                }
            }

            //3.交换两个位置
            if (exchangeIndex != -1) {
                int tmp = nums[exchangeIndex];
                nums[exchangeIndex] = nums[targetIndex];
                nums[targetIndex] = tmp;
            }
        }


        //4.将exchangeIndex后的值从顺序改为逆序
        for (int i = 1; exchangeIndex + i < nums.length - i; i++) {
            int tmp = nums[exchangeIndex + i];
            nums[exchangeIndex + i] = nums[nums.length - i];
            nums[nums.length - i] = tmp;
        }


    }


    /**
     * 下面是暴力回溯法，罗列出所有的可能性，实测会超时
     */
    int minNums = Integer.MAX_VALUE;
    int minimum = Integer.MAX_VALUE;

    public void nextPermutation(int[] nums) {


        if (nums == null || nums.length == 0) {
            return;
        }

        int number = 0;

        for (int i = 0; i < nums.length; i++) {
            number = number * 10 + nums[i];
        }

        backTrack(nums, 0, 0, number);


        if (minNums == Integer.MAX_VALUE) {
            char[] chars = String.valueOf(minimum).toCharArray();
            for (int i = 0; i < nums.length; i++) {

                //得到的值如果高位置0，需要补0
                int diff = nums.length - chars.length;
                if (diff == 0) {
                    nums[i] = chars[i] - '0';
                } else {
                    nums[i] = i < diff ? 0 : chars[i - diff] - '0';
                }
            }
        } else {
            char[] chars = String.valueOf(minNums).toCharArray();
            for (int i = 0; i < nums.length; i++) {

                int diff = nums.length - chars.length;
                if (diff == 0) {
                    nums[i] = chars[i] - '0';
                } else {
                    nums[i] = i < diff ? 0 : chars[i - diff] - '0';
                }
            }
        }
    }

    private void backTrack(int[] nums, int length, int count, int number) {

        if (nums.length == 0) {

            if (count > number) {
                minNums = Math.min(count, minNums);
            }
            minimum = Math.min(count, minNums);
            return;
        }

        for (int i = 0; i < nums.length; i++) {

            int tmpCount = count * 10 + nums[i];

            int[] tmpArray = new int[nums.length - 1];

            for (int j = 0; j < tmpArray.length; j++) {
                if (j < i) {
                    tmpArray[j] = nums[j];
                } else {
                    tmpArray[j] = nums[j + 1];
                }
            }

            backTrack(tmpArray, length + 1, tmpCount, number);
        }


    }
}