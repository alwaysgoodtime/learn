package leetcode.src.main.java.greedy;

import java.util.ArrayList;

/**
 * https://leetcode.cn/problems/monotone-increasing-digits/
 *
 * @author goodtime
 * @create 2023-04-01 23:29
 */
public class Number738 {
    public static void main(String[] args) {
        System.out.println(new Solution738().monotoneIncreasingDigits(341));
    }
}

/**
 * 这里不用暴力算法，暴力算法也简单，一直n--，然后看是否为单调递增即可
 * <p>
 * 非暴力算法，从高位往低位看，比如231，第一位能否是2，取决于第二位能否>=2。
 * 第二位只要>2就肯定可以（最差不过是229999....），等于2的话需要看下一位，本例就可以。
 *
 * 再看第二位，是否可以为3，取决于第三位能否>=3，不行，所以最大只能取2，后面无论有几位，全赋9即可。
 *
 *
 *
 * 还有一种贪心算法：从后往前两位两位比较，比如2310，先算10的最大值，为09（此时数为2309），再算30的最大值，为29（2299），最后算22的最大值
 * 22是单调递增的，所以最大单调递增数就是2299。
 *
 * 注意，一开始不要提前变9，可以设置个flag，记录变9的最高一位，flag后的统一变9。
 * 防止比如1000的情况，先比较00，符合规则，最后比较10，变成09。最后值是0900，
 * 其实最大单调递增数是999。
 *
 */
class Solution738 {
    public int monotoneIncreasingDigits(int n) {

        if (n <= 9) {
            return n;
        }

        //数字从低位到高位排列
        ArrayList<Integer> nums = new ArrayList<>();

        while (n != 0) {
            nums.add(n % 10);
            n = n / 10;
        }

        for (int i = nums.size() - 1; i >= 0; i--) {

            int num = nums.get(i);

            if (!canSet(num, i, nums)) {
                //低位直接返回9999即可。
                for (int j = 0; j < i; j++) {
                    nums.set(j, 9);
                }
                //本位置设置为num-1
                nums.set(i, num - 1);
                return combine(nums);
            }
        }

        //拼接字符串
        return combine(nums);
    }

    private int combine(ArrayList<Integer> nums) {

        int n = 1;
        int maxResult = 0;

        for (int i = 0; i < nums.size(); i++) {
            maxResult += nums.get(i) * n;
            n = n * 10;
        }

        return maxResult;
    }

    //i位是否可以设置为num
    //不断向它的更低位询问
    private boolean canSet(int num, int i, ArrayList<Integer> nums) {

        //只要个位>=num这个数，更高位即可设置
        if (i == 0) {
            Integer bitNum = nums.get(i);
            return num <= bitNum;
        }

        Integer bitNum = nums.get(i);

        if (bitNum < num) {
            return false;
        } else if (bitNum > num) {
            return true;
        } else {
            for (int j = num; j <= 9; j++) {
                if (canSet(j, i - 1, nums)) {
                    return true;
                }
            }

            return false;
        }
    }
}