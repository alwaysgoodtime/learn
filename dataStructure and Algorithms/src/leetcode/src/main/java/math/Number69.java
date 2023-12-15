package leetcode.src.main.java.math;

/**
 * @author goodtime
 * @create 2023-12-06 10:22
 */
public class Number69 {
    public static void main(String[] args) {
        System.out.println(new Solution69().mySqrt(10));
    }
}

/**
 * 直观解法：从i=1开始计算 i * i 找到 i * i <= x 且 (i+1) * (i+1) > x的值，i即为所求的算术平方根
 *
 * 数学解法：用下二分查找，范围为[0,x]
 *
 * 还可以用牛顿迭代法
 */
class Solution69 {
    public int mySqrt(int x) {

        if (x == 0 || x == 1) {
            return x;
        }

        int left = 1;
        int right = x / 2;

        while (left < right) {

            int middle = left + (right - left + 1) / 2;

            if (middle > x / middle) {
                right = middle - 1;
            } else if (middle == x / middle) {
                return middle;
            } else {
                left = middle;
            }

        }

        return left;


    }
}
