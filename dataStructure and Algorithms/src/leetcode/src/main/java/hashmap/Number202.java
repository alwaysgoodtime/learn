package leetcode.src.main.java.hashmap;

import java.util.HashMap;

/**
 * https://leetcode.cn/problems/happy-number/
 *
 * @author goodtime
 * @create 2023-11-30 22:58
 */
public class Number202 {

    public static void main(String[] args) {
        int n = 19;
        System.out.println(new Solution202().isHappy(n));
    }

}

/**
 * 核心思路：如果一个数无法跌到1，那么一定会进入循环，也即，一定会重复平方的和
 * hashmap：key 各位数平方和相加的和 value true/false
 */
class Solution202 {
    public boolean isHappy(int n) {

        HashMap<Integer, Boolean> map = new HashMap<>();

        while (1 == 1) {
            int sum = calSum(n);

            if (sum == 1) {
                return true;
            }

            if (map.containsKey(sum)) {
                return false;
            } else {
                map.put(sum, true);
            }

            n = sum;
        }

    }

    int calSum(int number) {

        int num = number;
        int sum = 0;

        while (num != 0) {
            int a = num % 10;
            num = num / 10;
            sum = sum + a * a;
        }

        return sum;
    }
}
