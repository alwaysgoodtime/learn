/**
 * @author goodtime
 * @create 2020-01-19 9:53 上午
 */
public class Number11 {
    public static void main(String[] args) {
        Solution11 solution11 = new Solution11();
        solution11.NumberOf1(3);
    }
}

class Solution11 {
    public int NumberOf1(int n) {
//        下面两个都是自带的函数实现
//        return Integer.toBinaryString(n).replace("0","").length();
//        return Integer.bitCount(n);

        int count = 0;
        if(n == 0){
            return 0;
        }
        if (n != 0){
            count ++;
            n = n&(n-1);
        }
        return  count;


    }
}
