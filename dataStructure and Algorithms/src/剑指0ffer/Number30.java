/**
 * JZ42 连续子数组的最大和
 * 输入一个长度为n的整型数组array，数组中的一个或连续多个整数组成一个子数组，子数组最小长度为1。求所有子数组的和的最大值。
 *
 * @author goodtime
 * @create 2020-01-21 10:31 下午
 */
public class Number30 {
    public static void main(String[] args) {
        Solution30 solution30 = new Solution30();
        int[] a = {2, 3, 8, -10000, 10000, -3};
        int i = solution30.FindGreatestSumOfSubArray(a);
        System.out.println(i);
    }
}


//动态规划

/*
算法时间复杂度O（n）
用total记录累计值，maxSum记录和最大
基于思想：对于一个数A，若是A的左边累计数非负，那么加上A能使得值不小于A，认为累计值对
        整体和是有贡献的。如果前几项累计值负数，则认为有害于总和，total记录当前值。
        此时,若和大于maxSum,则用maxSum记录下来
改编自牛客网
*/
class Solution30 {

    public int FindGreatestSumOfSubArray(int[] array) {
        if (array == null || array.length == 0) {
            return 0;//排除array为null或array为空数组的情况，暂时返回一个1
        }

        int total = array[0], maxSum = array[0];
        for (int i = 1; i < array.length; i++) {
            if (total >= 0) {
                total += array[i];
            } else {
                total = array[i];
            }
            if (total > maxSum) {
                maxSum = total;
            }
        }
        return maxSum;

    }
}