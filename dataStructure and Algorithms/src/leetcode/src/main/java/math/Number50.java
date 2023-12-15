package leetcode.src.main.java.math;

/**
 * https://leetcode.cn/problems/powx-n/
 *
 * @author goodtime
 * @create 2023-12-06 11:10
 */
public class Number50 {

    public static void main(String[] args) {

        System.out.println(new Solution50().myPow(34.00515, -3));

    }

}

/**
 * 这里的关键是用快速幂法，普通相乘会超时
 *
 * 假如n=15，那么n的二进制表示为1111
 *
 * 假设x=3，3的15次幂，等于3的1次幂 * 3的2次幂 * 3的4次幂 * 3的8次幂
 *
 * 3的2次幂，是3的1次幂 * 3的1次幂
 *
 * 3的4次幂，其实就是3的2次幂 * 3的2次幂
 *
 * 3的8次幂，是3的4次幂 * 3的4次幂
 *
 * 这样，通过对n进行二进制处理，可以快速得到3的15次幂乘积，原来需要循环相乘15次，现在只需要4次
 */
class Solution50 {
    public double myPow(double x, int n) {

        if (n == 0) {
            return 1d;
        }

        //防止Integer.MIN_VALUE转为正数时，越界
        long b = n;

        double result = 1;

        long tmp = Math.abs(b);

        //快速幂
        while (tmp != 0) {

            if ((tmp & 1) == 1) {
                result = result * x;
            }

            x = x * x;

            tmp = tmp >> 1;

        }

        if (n < 0) {
            result = 1 / result;
        }

        return result;

    }


    /**
     * 递归版本，比如15次幂，就是2个7次幂相乘再乘1次幂
     * 7次幂，就是两个3次幂相乘再乘1次幂
     */
    public double myPowRecursion(double x, int n) {

        if (n == 0) {
            return 1d;
        }

        //防止Integer.MIN_VALUE转为正数时，越界
        long b = n;

        double result = 1;

        long tmp = Math.abs(b);


        result = recursion(x, tmp);

        if (n < 0) {
            result = 1 / result;
        }

        return result;

    }

    private double recursion(double x, long tmp) {

        //结束条件
        if (tmp == 1) {
            return x;
        }

        long halfTmp = tmp / 2;

        double recursion = recursion(x, halfTmp);

        if (tmp % 2 != 0) {
            return recursion * recursion * x;
        } else {
            return recursion * recursion;
        }

    }

}