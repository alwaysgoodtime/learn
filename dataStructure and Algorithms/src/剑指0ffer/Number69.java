/**
 * JZ44 数字序列中某一位的数字
 * 数字以 0123456789101112131415... 的格式作为一个字符序列，
 * 在这个序列中第 2 位（从下标 0 开始计算）是 2 ，第 10 位是 1 ，
 * 第 13 位是 1 ，以此类题，请你输出第 n 位对应的数字。
 *
 * @author goodtime
 * @create 2023-03-29 12:54
 */
public class Number69 {

    public static void main(String[] args) {
        int n = 1000000000;
        Solution69 solution69 = new Solution69();
        System.out.println(solution69.findNthDigit(n));
    }

}

/**
 * 找规律题，一般做法会超时
 * 小于10，1-9，9个数字，9 位
 * 小于100，10-99，90个数字，180位
 * 小于1000,100-999，900个数字，2700位
 * 注意用long,如果数字再大，就得用其他方式存储整数了
 */
class Solution69 {
    public int findNthDigit(int n) {

        if (n < 0) {
            return -1;
        }

        if (n <= 9) {
            return n;
        }

        long x = n;
        long digitSum = 0;

        for (int j = 1;; j++) {

            //180、2700...当前进制位的位数
            long digit = (long)Math.pow(9, j) * j;

            if (x <= digitSum + digit) {

                //距离上一个位数的偏移量，12是两位数的一个下标，1位数最大下标是9，偏移量是3
                long offset = x - digitSum;
                //位数所在数字的的具体偏移，比如12偏移量是3，,3%2 = 1，也即它是所在数字的第一位数。
                long position = offset % j;

                //没有偏移，就代表位数在所在数字的最后一位
                if (position == 0) {
                    position = j;
                }

                long num = 0;

                if (position == j) {
                    //偏移量所在的数字，以13为例，它在数字就是4/2的商+10-1，也就是11。
                    num = offset / j + (int)Math.pow(10,(j-1)) -1;
                } else {
                    //偏移量所在的数字，以12为例，它在数字就是3/2的商+10-1后再加1位（因为没有除尽），也就是11。
                    num = offset / j + (int)Math.pow(10,(j-1));
                }

                StringBuilder tmp = new StringBuilder();
                tmp.append(num);

                return tmp.charAt((int)position - 1) - '0';
            }

            digitSum += digit;
        }
    }
}
