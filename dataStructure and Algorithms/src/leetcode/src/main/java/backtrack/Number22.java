package leetcode.src.main.java.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/generate-parentheses/description/
 *
 * @author goodtime
 * @create 2023-12-08 09:29
 */
public class Number22 {
    public static void main(String[] args) {
        System.out.println(new Solution22().generateParenthesis(3));
    }
}

/**
 * 括号的匹配规则，右括号前面必须有一个左括号，可以在回溯的时候就加好规则限制
 */
class Solution22 {

    List<String> result = new ArrayList<>();
    StringBuilder stringBuilder = new StringBuilder();
    int k;

    public List<String> generateParenthesis(int n) {

        if (n <= 0) {
            return result;
        }

        k = n;

        backtrack(n * 2, 0, 0);
        return result;
    }

    private void backtrack(int n, int i, int leftCount) {

        if (n == 0) {
            result.add(stringBuilder.toString());
            return;
        }

        if (i > 0) {
            stringBuilder.append(')');
            backtrack(n - 1, i - 1, leftCount);
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }

        if (leftCount < k) {
            stringBuilder.append('(');
            backtrack(n - 1, i + 1, leftCount + 1);
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }

    }

}
