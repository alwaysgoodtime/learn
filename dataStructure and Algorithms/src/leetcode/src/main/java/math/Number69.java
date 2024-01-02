package leetcode.src.main.java.math;

/**
 * @author goodtime
 * @create 2023-12-06 10:22
 */
public class Number69 {
    public static void main(String[] args) {
        System.out.println(new Solution69().mySqrt(8));
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

        int left = 1;
        int right = x;
        int ans = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            // upper bound的形式，因为我们要找的ans要是最接近于x的最大的数，利用upper bound
            if (mid <= x / mid) {//核心在这里，mid*mid <= x 可能会超出运算范围
                left = mid + 1;
                ans = mid; // 只要mid <= x/mid，left左边界就会一直向右移动，ans就会一直更新（变大），直到不在满足mid <= x/mid的条件为止，ans就会停止更新，永远停在满足mid<=x/mid条件下面的最后一次更新，即满足ans * ans <= mid的最大值。
            } else {
                right = mid - 1;
            }
        }

        return ans;

    }
}
