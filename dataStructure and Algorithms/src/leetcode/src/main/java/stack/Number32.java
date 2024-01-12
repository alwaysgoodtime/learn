package leetcode.src.main.java.stack;

import java.util.Stack;

/**
 * @author goodtime
 * @create 2024-01-12 11:44
 */
public class Number32 {
    public static void main(String[] args) {
        System.out.println("test");
    }
}

/**
 * 使用栈，栈底中存放的是"最后一个没有匹配的）下标"，开始是为了简化操作，可以放入-1
 */
class Solution32 {
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        char[] chars = s.toCharArray();
        int result = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') {
                stack.push(i);
            } else {
                Integer leftIndex = stack.pop();
                if (leftIndex == -1 || chars[leftIndex] == ')') {
                    stack.push(i);
                } else {
                    result = Math.max(result, i - stack.peek());
                }
            }
        }

        return result;
    }
}