/**
 * @author goodtime
 * @create 2020-01-26 5:49 下午
 */
public class Number47 {
    public static void main(String[] args) {
        Solution47p solution47 = new Solution47p();
        int i = solution47.Sum_Solution(5);
        System.out.println(i);
    }
}

//思路：计算n个数，不能用循环、乘除,可以用递归,递归终止的条件本来是if n=1，return1，现在不能用if
//可以利用||或者&&的短路特性
class Solution47 {
    public int Sum_Solution(int n) {
         boolean flag = (n == 0) || ((n += Sum_Solution(n-1))>-1);//后面式子的意思是，给n赋值后，返回n>-1的值（1或者0）
         return n;
    }
}

//使用左移的位运算和Math类中的pow方法
class Solution47p {
    public int Sum_Solution(int n) {
        int i = (int) Math.pow(n, 2) + n;
        int rt = i >> 1;
        return rt;
    }
}

/**第三种思路：
 *快速幂的方法
 *
 * 原理是把a拆成2的幂的和，a = 2^e0 + 2^e1 + 2^e2....
 *  那么 a * b = (2^e0 + 2^e1 + 2^e2+...) * b
 *                       = b * 2^e0 + b * 2^e1 + b * 2^e2 + ...
 *                       = (b << e0) + (b << e1) + ....
 *
 * 如果b=11（3），a=101（5），（都是二进制）。a末位首先为1，b为11，a末位然后为0，b为110（也就是bX2），
 * 然后a末位为1，b为1100；也就是说，b乘以2的0次，然后看看加不加；接下来b乘以2的一次，然后看看加不加
 * a = 2的0次方乘以1（相当于b） + 2的一次方乘以0（相当于不加bX2） + 2的二次方乘以1（相当于加bX2的平方）
 * 对于2进制数来说，左移一位就是X2。
 *
 * public static int Sum_Solution2(int n) {
 *         int res = 0;
 *         int a = n;//若a=2=10
 *         int b = n + 1;//b=3=11
 *         while (a != 0) {
 *             if ((a & 1) == 1)//a在第二位==1的时候才更新res=0+110=6
 *                 res += b;
 *             a >>= 1;//a右移1位 1
 *             b <<= 1;//b左移动1位 110
 *         }
 *         return res>>=1;//n(n+1)/2     }
 */
