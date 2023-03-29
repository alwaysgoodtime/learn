/**
 * @author goodtime
 * @create 2020-01-22 1:24 上午
 */
public class Number31 {
    public static void main(String[] args) {
        Solution31 solution31 = new Solution31();
        int i = solution31.NumberOf1Between1AndN_Solution(100);
        System.out.println(i);
    }
}

//      n等于0，跳出循环，count为1出现的个数，digits为位数，比如个位，十位，i为总的n,每次除10后的余数。
//        k为上个位数所有1可能出现的次数，比如n为110，那么k就是0-99中1可能出现的总次数。称其为进位补偿，相当于
//        计算9、99、999 ... 9...9（位数比n少1）个数中包含的1个个数。
//        m为当前的余数

class Solution31 {
    public int NumberOf1Between1AndN_Solution(int n) {
        int count = 0;
        int digits = 1;
        int i;
        int l = n;
        int k = 0;

        while(n != 0) {
            int m = n % 10;
            i = l%digits;
            if(m == 1){
                count = count + i + 1 + k;
            }
            if(m > 1){
                count = digits + count + m*k;
            }
            n = n / 10;
            if(n != 0){
                k = digits + 10*k;
            }
            digits = digits*10;
        }
        return count;
    }
}
