package leetcode.src.main.java.stack;

import java.util.Stack;

/**
 * @author goodtime
 * @create 2024-01-11 18:16
 */
public class Number394 {
    public static void main(String[] args) {
        System.out.println(new Solution394s().decodeString("2[e1[f]]ef2[e]c"));
    }

}

/**
 * 辅助栈法
 */
class Solution394s {

    public String decodeString(String s) {

        Stack<Integer> numberStack = new Stack<>();
        Stack<String> strStack = new Stack<>();

        int multi = 0;
        StringBuilder result = new StringBuilder();

        char[] chars = s.toCharArray();

        for (int i = 0; i < chars.length; i++) {

            if (chars[i] == '[') {
                strStack.push(result.toString());
                result.delete(0, result.length());
                numberStack.push(multi);
                multi = 0;
            } else if (chars[i] == ']') {
                String pop = strStack.pop();
                Integer popNumber = numberStack.pop();

                String curString = result.toString();

                while (popNumber > 1) {
                    result.append(curString);
                    popNumber--;
                }

                if (!pop.equals("")) {
                    StringBuilder builder = new StringBuilder(pop);
                    builder.append(result);
                    result = builder;
                }

            } else if (chars[i] >= '0' && chars[i] <= '9') {
                multi = multi * 10 + (chars[i] - '0');
            } else {
                result.append(chars[i]);
            }
        }

        return result.toString();

    }
}


/**
 * 递归处理，虽然该题默认输入都是有效的，但允许[]嵌套的情况，eg:3[a2[c]]
 * 也有辅助栈法
 */
class Solution394 {

    int index = 0;

    public String decodeString(String s) {
        return decode(s.toCharArray());
    }

    private String decode(char[] chars) {

        StringBuilder builder = new StringBuilder();

        while (index < chars.length) {
            if (chars[index] >= '0' && chars[index] <= '9') {
                builder.append(parseNumber(chars));
            } else {
                builder.append(parseSubString(chars));
            }
        }

        return builder.toString();
    }

    private String parseNumber(char[] chars) {

        StringBuilder builder = new StringBuilder();

        int tmp = 0;

        while (chars[index] >= '0' && chars[index] <= '9') {
            tmp = tmp * 10 + (chars[index] - '0');
            index++;
        }

        String sub = parseSubString(chars);

        //处理
        if (tmp <= 1) {
            builder.append(sub);
        } else {
            for (int j = 0; j < tmp; j++) {
                builder.append(sub);
            }
        }

        return builder.toString();
    }

    private String parseSubString(char[] chars) {

        StringBuilder builder = new StringBuilder();


        while (index < chars.length && chars[index] != ']') {
            if (chars[index] == '[') {
                index++;
            } else if (chars[index] >= 'a' && chars[index] <= 'z') {
                builder.append(chars[index]);
                index++;
            } else {
                //注意，这里无需index++，已经处理整个子[]的内容
                builder.append(parseNumber(chars));
            }
        }


        index++;//跳过[

        return builder.toString();
    }
}