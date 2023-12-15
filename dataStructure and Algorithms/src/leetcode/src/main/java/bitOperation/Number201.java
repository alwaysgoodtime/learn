package leetcode.src.main.java.bitOperation;

/**
 * https://leetcode.cn/problems/bitwise-and-of-numbers-range/description/
 *
 * @author goodtime
 * @create 2023-12-06 08:33
 */
public class Number201 {

    public static void main(String[] args) {
        System.out.println("test");
    }
}

/**
 * 题目要求无需考虑负数
 *
 * 范围内的数按位与的结果，其实与left、right的最高位相关，
 *
 * 11100 与 11000  结果是 11000
 *
 * 101 与 111 结果是 111
 *
 * 也就是说，两个数从高位开始只有都是1，按位与的结果才是1，只要有一个高位并非都是1，那么后面都可以填充0了
 */
class Solution201 {
    public int rangeBitwiseAnd(int left, int right) {

        int result = 0;

        for (int i = 1; i <= 31; i++) {

            int highNumberRight = (right >>> (31 - i)) & 1;

            int highNumberLeft = (left >>> (31 - i)) & 1;

            if (highNumberLeft + highNumberRight == 2) {
                result = result | highNumberRight << (31 - i);
            } else if (highNumberLeft + highNumberRight == 1) {
                return result;
            } else {

            }
        }

        return result;
    }
}