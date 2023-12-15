package leetcode.src.main.java.bitOperation;

/**
 * https://leetcode.cn/problems/number-of-1-bits/
 *
 * @author goodtime
 * @create 2023-12-06 07:06
 */
public class Number191 {

    public static void main(String[] args) {
        System.out.println("test");
    }

}

/**
 * 对n进行位操作，从头到尾遍历一遍，找到为1的值即可
 */
class Solution191 {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {

        if (n == 0) {
            return 0;
        }

        int result = 0;

        for (int i = 0; i <= 31; i++) {

            result += n & 1;
            n = n >>> 1;

        }

        return result;
    }
}
