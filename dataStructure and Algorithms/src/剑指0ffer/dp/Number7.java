package 剑指0ffer.dp;

import java.util.HashMap;

/**
 * JZ10 斐波那契数列
 * 大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0）。
 * n<=39
 *
 * @author goodtime
 * @create 2020-01-18 10:28 下午
 */
public class Number7 {
    public static void main(String[] args) {
        Solution7s solution = new Solution7s();
        System.out.println(solution.Fibonacci(6));

    }
}

/**
 * 动态规划版本
 */
class Solution7 {
    public int Fibonacci(int n) {

        if (n == 1 || n == 2) {
            return 1;
        }


        int[] dp = new int[n];

        //初始化
        dp[0] = 1;
        dp[1] = 1;

        //递推公式
        for (int i = 2; i < n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp[n-1];
    }
}

/**
 * 动态规划版本，压缩状态
 */
class Solution7ss {
    public int Fibonacci(int n) {

        if (n == 1 || n == 2) {
            return 1;
        }


        int[] dp = new int[2];

        //初始化
        dp[0] = 1;
        dp[1] = 1;

        int sum = 0;

        //递推公式，因为dp[i](也就是这里的sum)的值只依赖前两个数的值，我们就可以反复利用前两个数
        for (int i = 2; i < n; i++) {
            sum = dp[0] + dp[1];
            dp[0] = dp[1];
            dp[1] = sum;
        }

        return sum;
    }
}



/**
 * 递归版本
 * log(n)解法，为什么是log(n)，可以看这篇文章：https://blog.csdn.net/u010365819/article/details/121000532
 */
class Solution7s {

    //备忘录，空间换时间，hashmap的get是o(1)
    HashMap<Integer, Integer> hashMap = new HashMap<>();

    public int Fibonacci(int n) {

        if (n == 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }

        if(hashMap.containsKey(n)){
            return hashMap.get(n);
        }else {
            hashMap.put(n,Fibonacci(n - 1) + Fibonacci(n - 2));
        }

        return hashMap.get(n);
    }
}