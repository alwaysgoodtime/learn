package leetcode.src.main.java.ms;

import java.util.Stack;

/**
 * https://leetcode.cn/problems/daily-temperatures/
 *
 * @author goodtime
 * @create 2023-04-06 15:19
 */
public class Number739 {
    public static void main(String[] args) {
        int[] ints = new Solution739().dailyTemperatures(new int[]{
                73, 74, 75, 71, 69, 72, 76, 73
        });

        System.out.println(ints[0]);
    }
}


/**
 * 求左边或右边比当前元素大的元素，一般用单调栈解题
 */
class Solution739 {
    public int[] dailyTemperatures(int[] temperatures) {

        if (temperatures == null || temperatures.length == 0) {
            return temperatures;
        }

        Stack<Integer> ms = new Stack<>();
        ms.push(0);

        int[] result = new int[temperatures.length];


        for (int i = 1; i < temperatures.length; i++) {
            while (!ms.empty()) {
                Integer peek = ms.peek();
                if (temperatures[i] > temperatures[peek]) {
                    result[peek] = i - peek;
                    ms.pop();
                } else {
                    break;
                }
            }

            ms.push(i);
        }

        return result;

    }
}