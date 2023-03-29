/**
 * @author goodtime
 * @create 2020-01-26 8:18 下午
 */
public class Number48 {
    public static void main(String[] args) {
        Solution48 solution48 = new Solution48();
        int add = solution48.Add(9,7);
        System.out.println(add);

    }
}

/**
 * 0001001 9
 * 0000111 7
 *
 * 0000010（进位） num2
 * 0001110 ^ 异或运算符 （位数相加的值） num1
 *
 * 0000100 （进位） num2
 * 0001100 异或 num1
 *
 * 0001000 （进位） num2
 * 0001000 异或 num1
 *
 * 0010000 (进位) num2
 * 0000000 异或 num1
 *
 * 0000000 （进位） num2
 * 0010000 异或 num1
 *
 * 循环结束
 */

//写一个函数，求两个整数之和，要求在函数体内不得使用+、-、*、/四则运算符号。
//思路：两个数异或：相当于每一位相加，而不考虑进位；
//两个数相与，并左移一位：相当于求得进位；
//将上述两步得到的两个数相加，和原来两个数相加的值相同，所以我们继续用上面的两步把新的两个数进行相加。
//循环结束的标志，是进位的值变成0；
class Solution48 {
    public int Add(int num1,int num2) {
        int i = 1;
        int j = 1;
        while(j != 0) {
            i = num1 ^ num2;
            j = (num1 & num2) << 1;
            num1 = i;
            num2 = j;
        }
        return i;
    }
}
