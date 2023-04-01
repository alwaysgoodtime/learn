package leetcode.src.main.java.dp;

import java.util.HashMap;

/**
 * https://leetcode.cn/problems/integer-break/
 *
 * @author goodtime
 * @create 2023-03-30 13:29
 */
public class Number343 {

    public static void main(String[] args) {
        int i = 3;
        System.out.println(new Solution343().integerBreak(i));
    }

}


/**
 * dp写法（注意，dp写法里是不会使用递归的）
 */
class Solution343 {
    public int integerBreak(int n) {

        if (n <= 0) {
            return 0;
        }

        if (n <= 2) {
            return n - 1;
        }

        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 0;
        dp[2] = 1;

        for (int i = 3; i <= n; i++) {
            //这里可以优化一下，j <= i/2即可，因为在j > i/2的情况下，实际上我们已经考虑了所有的可能性
            for (int j = 1; j <= i; j++) {
                //这里注意，一定要用一个tmp,和之前内循环的dp[i]最大值做完比较后再赋值
                int tmp = Math.max(j * dp[i - j], j * (i - j));
                if (dp[i] < tmp) {
                    dp[i] = tmp;
                }
            }
        }

        return dp[n];
    }
}

/**
 * 递归 + 备忘录
 */
class Solution343s {

    HashMap<Integer, Integer> hashMap = new HashMap<>();

    public int integerBreak(int n) {

        if (n <= 0) {
            return 0;
        }

        if (n <= 2) {
            return n - 1;
        }

        if (!hashMap.containsKey(n)) {

            int result = 0;

            for (int i = 1; i < n; i++) {
                int tmp = Math.max(integerBreak(n - i) * i, (n - i) * i);
                if (tmp > result) {
                    result = tmp;
                }
            }

            hashMap.put(n, result);

        }
        return hashMap.get(n);
    }
}