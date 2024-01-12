package interview.bytedance;

import java.util.Arrays;

/**
 * @author goodtime
 * @create 2023-12-31 23:12
 */
public class 小于n的最大数 {
    public static void main(String[] args) {
        int[] array = {4, 5, 2};
        int number = 425;

        System.out.println(new Solution().handle(array, number));
    }
}

/**
 * 对于本题而言，证明的方法是从解的性质反推，归纳。
 * 首先，小于n的数字有什么性质？显然可以分为两类：
 * 1.位数和n相同，但是字典序小;2.位数比n小
 * 对于情形1，我们要找字典序小的数，那么什么条件下字典序小？
 * 两个字符串有一段相同的前缀（可以长度为0）；
 * 在这段前缀后的第一个字符更小。
 * 我们只需枚举前述第一个更小的位置即可。对于这个更小的字符，我们应当让它尽可能大，但是不能等于或超过限值；对于其余字符，则没有限制，取可选的最大即可。
 * 这就得出了前面的构造方法。对于情形2，位数小于 n 的数字中，最大的显然就是位数-1，但所有数位都最大的数字。
 *
 * 这样就推出了解法，同时从推理的过程中验证了解的正确性。
 *
 * 作者：newhar
 */
class Solution {

    public int handle(int[] array, int number) {

        //先对数组排序
        Arrays.sort(array);
        //将number每个位数打散
        String s = String.valueOf(number);
        char[] chars = s.toCharArray();

        int order = -1;//需要变化的位数
        int value = -1;//需要变化位数的值
        int res = 0;//返回结果
        int digit = 10;//乘积
        int maxNumber = array[array.length - 1];

        //情形1，找位数相同，字典序小的数字
        for (int i = 0; i < chars.length; i++) {

            int num = chars[i] - '0';
            //二分查找array,试图找到与num相同的数字
            int result = binarySearch(array, num);

            //此时无法保持前缀相同，或者已经到了最后一个位数字，那么试图找更小的数字
            if (result == -1 || i == chars.length - 1) {
                //如果当前位置找不到更小的数字，那么就一直往前回溯
                for (int j = i; j >= 0; j--) {
                    int maxNumberLessThanNum = getMaxNumberLessThanNum(array, chars[j] - '0');
                    if (maxNumberLessThanNum != -1) {
                        //回溯到此为止
                        order = j;
                        value = maxNumberLessThanNum;
                        break;
                    }
                }
                break;
            }
        }

        //情形2
        if (order == -1) {
            for (int i = 0; i < chars.length - 1; i++) {
                res = res * digit + maxNumber;
            }
            return res;
        }

        //情形1
        for (int i = 0; i < chars.length; i++) {
            if (order > i) {
                res = res * digit + (chars[i] - '0');
            } else if (order == i) {
                res = res * digit + value;
            } else {
                res = res * digit + maxNumber;
            }
        }

        return res;

    }

    //二分查找，查找库里最后一个<=num的数字，而非下标
    public int binarySearch(int[] array, int num) {

        int left = 0;
        int right = array.length;

        while (left < right) {

            int middle = left + (right - left) / 2;

            if (array[middle] < num) {
                left = middle + 1;
            } else if (array[middle] == num) {
                return middle;
            } else {
                right = middle;
            }
        }

        return -1;
    }

    //二分查找，查找库里最后一个<num的数字，而非下标
    private int getMaxNumberLessThanNum(int[] array, int num) {

        int left = 0;
        int right = array.length - 1;
        int res = -1;

        while (left <= right) {

            int middle = left + (right - left) / 2;

            if (array[middle] < num) {
                res = array[middle];
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }

        return res;

    }

}