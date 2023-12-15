package leetcode.src.main.java.stack;

import java.util.Stack;

/**
 * https://leetcode.cn/problems/valid-parentheses/
 *
 * @author goodtime
 * @create 2023-12-02 14:16
 */
public class Number20 {
    public static void main(String[] args) {
        System.out.println(new Solution20().isValid("{[]}"));
    }
}

class Solution20 {
    public boolean isValid(String s) {

        if (s == null || s.length() == 0 || s.length() % 2 != 0) {
            return false;
        }

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);

            if (c == '(' || c == '[' || c == '{') {

                stack.push(c);

            } else {

                if (stack.size() == 0) {
                    return false;
                }

                Character pop = stack.pop();
                if (pop == '(' && c != ')') {
                    return false;
                }

                if (pop == '[' && c != ']') {
                    return false;
                }

                if (pop == '{' && c != '}') {
                    return false;
                }

                if (pop != '(' && pop != '[' && pop != '{') {
                    return false;
                }
            }

        }

        return stack.size() == 0;

    }
}

