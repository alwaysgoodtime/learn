package leetcode.src.main.java.dp;

/**
 * https://leetcode.cn/problems/climbing-stairs/
 *
 * @author goodtime
 * @create 2023-11-29 16:06
 */
public class Number70 {
    public static void main(String[] args) {
        System.out.println(new Solution70().climbStairs(1000));
    }
}

/**
 * 爬楼梯
 * 递推公式：dp[i]= dp[i-2] + dp[i-1];  i代表第i+1阶台阶有几种到达方法
 */
class Solution70 {
    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }

        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 2;

        for (int i = 2; i < n; i++) {
            dp[i] = dp[i - 2] + dp[i - 1];
        }

        return dp[n-1];

    }
}
