package leetcode.src.main.java.math;

/**
 * https://leetcode.cn/problems/factorial-trailing-zeroes/
 *
 * @author goodtime
 * @create 2023-12-06 10:01
 */
public class Number172 {

    public static void main(String[] args) {
        System.out.println(new Solution172().trailingZeroes(5));
    }

}

/**
 * 简单方法就是计算出n的阶乘，然后查看最终的结果有几个0，时间复杂度为n
 *
 * 但n的阶乘非常大，于是可是考察结果n中有几个0，也即结果n的因子中最大有几个10相乘
 *
 * 举例：1200 = 10 * 10 * 12， 那么它有两个10，也即2个0，每个10又可以拆为2 * 5，那么也即 1200 中最多有几个 2 * 5
 * 经过数学证明或者直觉，阶乘中是不缺2的，缺的是5，那么可以考察阶乘中最多有几个5即可（10 = 2* 5 15 = 3 * 5 25 = 5*5 ，25能提供两个五）
 */
class Solution172 {
    public int trailingZeroes(int n) {

        int result = 0;

        for (int i = 5; i <= n; i = i + 5) {

            int tmp = i;

            while (tmp % 5 == 0) {
                result++;
                tmp = tmp / 5;
            }

        }

        return result;

    }


    /**
     * 优化思路：
     *
     * 本题实际上就是计算1-n之中有多少个5的因数。以130为例：
     * 第一次除以5时得到26，表明存在26个包含 [一] 个因数5的数；
     * 第二次除以5得到5，表明存在5个包含 [二] 个因数5的数(这些数字的一个因数5已经在第一次运算的时候统计了)；
     * 第三次除以5得到1，表明存在1个包含 [三] 个因数5的数(这些数字的两个因数5已经在前两次运算的时候统计了)；
     * 得到从1-n中所有5的因数的个数
     */
    public int trailingZeroesAdvanced(int n) {

        int result = 0;

        while (n != 0) {


            result += n / 5;
            n = n / 5;

        }

        return result;

    }

}