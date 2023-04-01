package 剑指0ffer.dp;

/**
 * JZ69 跳台阶
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。
 * 求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
 *
 * @author goodtime
 * @create 2020-01-18 10:40 下午
 */
public class Number8 {
    public static void main(String[] args) {
        Solution8 solution8 = new Solution8();
        int i = solution8.JumpFloor(7);
        System.out.println(i);
    }
}

/**
 * 动态规划解法
 */
class Solution8s {
    public int JumpFloor(int target) {
        if (target == 1) {
            return 1;
        } else if (target == 2) {
            return 2;
        }

        int[] dp = new int[2];
        dp[0] = 1;
        dp[1] = 2;

        int sum = 0;
        for (int i = 2; i < target; i++) {
            sum = dp[0] + dp[1];
            dp[0] = dp[1];
            dp[1] = sum;
        }

        return sum;

    }
}


/**
 * 递归解法
 */
class Solution8 {
    public int JumpFloor(int target) {
        if (target == 1) {
            return 1;
        } else if (target == 2) {
            return 2;
        } else {
            return JumpFloor(target - 1) + JumpFloor(target - 2);
        }
    }
}