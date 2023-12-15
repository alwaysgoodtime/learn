package leetcode.src.main.java.bitOperation;

/**
 * https://leetcode.cn/problems/reverse-bits/description/
 *
 * @author goodtime
 * @create 2023-12-06 05:18
 */
public class Number190 {
    public static void main(String[] args) {
        System.out.println(new Solution190().reverseBitsAdvanced(-3));
    }
}

/**
 * 颠倒2进制位后，输出对应的int
 *
 * 第二种思路：逻辑右移即可
 */
class Solution190 {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {

        if (n == 0) {
            return 0;
        }

        int[] array = new int[32];

        int index = 0;

        //得到n每个2进制位值的相反值，并存入array中
        while (n != 0) {

            //正数高位全是0，正好符和array的默认值
            if (n > 0) {

                array[index] = n % 2;
                n = n / 2;
                index++;

            } else {

                //负数最高位是1，那么先把array[31]设为1，先假设int只能存4位，最高位为符号位
                //如果是1101（-3），去除符号位后我们希望得到0101（5）
                //如果是1111（-1），去除符号位后我们希望得到0111（7）
                //也就是说，我们想得到负数把最高为的符号位从1变为0后，其他位都不变的那个二进制数所代表的正数
                //可以如此计算，我们让Integer.MAX_VALUE(对于此处就是0111，也就是7)，加和原来的负数，这样得到的数再+1，即可得到我们想要的数
                //再看一例，1100（-4），加和7后为3，加1为4，也即0100
                array[31] = 1;
                n = n + Integer.MAX_VALUE + 1;

            }
        }

        int rate = 30;
        int result = 0;

        //跳过符号位
        for (int i = 1; i <= 31; i++) {

            if (array[i] == 1) {
                result += array[i] * Math.pow(2, rate);
            }

            rate--;

        }

        if (array[0] == 0) {
            //相反值的符号位为正数，正常返回即可
            return result;
        } else {
            //如果array[0]为1，其为符号位，代表将要转变的数为负数，先假设int只能存4位，最高位为符号位
            //也即array为1101（-3），我们能得到的为5（101），要想办法将其变为-3，显然可以让5 - Integer.MAX_VALUE - 1,
            //再看一例， 1100（-4），我们得到的为4（100）， 显然可以让4 - Integer.MAX_VALUE - 1，得到原来的数
            //再看一例， 1111（-1），我们得到的为7（111），显然可以让7 - Integer.MAX_VALUE - 1
            return result - Integer.MAX_VALUE - 1;
        }


    }

    public int reverseBitsAdvanced(int n) {

        if (n == 0) {
            return 0;
        }

        int result = 0;

        //通过逻辑右移取到n的最后一个数，然后填到新的result中
        for (int i = 0; i < 32; i++) {

            //1.让n与1按位与，取到最后一个数
            int lastNumber = n & 1;

            //2.让结果的第i个数与lastNumber所代表的数相同，那么就让lastNumber中的1往左移31-i下，然后和result按位或
            result = result | lastNumber << (31 - i);


            //3.将n用逻辑右移 >>> 1位，得到新的最后1个数
            n = n >>> 1;
        }

        return result;
    }
}
