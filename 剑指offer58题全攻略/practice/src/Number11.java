/**
 * @author goodtime
 * @create 2020-01-19 9:53 上午
 */
public class Number11 {
    public static void main(String[] args) {
        Solution11s solution11 = new Solution11s();
        int i = solution11.NumberOf1(-2);
        System.out.println(i);
    }
}

class Solution11 {
    public int NumberOf1(int n) {
//        下面两个都是自带的函数实现
//        return Integer.toBinaryString(n).replace("0","").length();
        return Integer.bitCount(n);
    }
}

class Solution11s {
    public int NumberOf1(int n) {
        int count = 0;
        do{
            if(n % 2 == 1 || n % 2 == -1){//负数%2,都是-1
                count++;
            }
        }
        while ((n = n >>> 1)!= 0);
        return count;
    }
}

