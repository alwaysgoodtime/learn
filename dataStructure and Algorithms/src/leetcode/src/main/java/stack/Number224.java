package leetcode.src.main.java.stack;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author goodtime
 * @create 2023-12-02 21:58
 */
public class Number224 {

    public static void main(String[] args) {
        String s = "  30";
        System.out.println(new Solution224().calculate(s));
    }

}

/**
 * 思路：将传统的加减转换为逆波兰式表达法，再用逆波兰式的栈进行计算即可
 *
 * 要求一个表达式的逆波兰式，可以按照以下步骤进行（中缀转后缀）
 *
 * 1.创建一个空的栈用于存储运算符，一个数组用于存储转换后的逆波兰式
 * 2.从左到右遍历表达式的每个元素。
 * 3.如果当前元素是操作数（数字），则将其输出或存储到结果中。
 * 4.如果当前元素是运算符，则执行以下操作：
 * 4.1如果栈为空，或者栈顶的元素是左括号 “(”，则将当前运算符入栈。
 * 4.2如果当前运算符的优先级高于栈顶运算符的优先级，则将当前运算符入栈。
 * 4.3如果当前运算符的优先级低于或等于栈顶运算符的优先级，则将栈顶运算符出栈并输出或存储到结果中，直到栈顶运算符的优先级低于当前运算符或栈为空，然后将当前运算符入栈。
 * 4.4如果当前运算符是右括号 “)”，则连续弹出栈顶运算符并输出或存储到结果中，直到遇到左括号 “(”。注意：左括号 "("不输出或存储到结果中，只用于匹配右括号 “)”。
 *
 * 5.遍历完所有元素后，如果栈中仍然有运算符，将它们依次弹出并输出或存储到结果中。
 * 最终得到的结果即为表达式的逆波兰式。
 *
 * 注意负数的情况，此时-x中的-为数值
 *
 * @see Number150
 */
class Solution224 {
    public int calculate(String s) {

        if (s == null || s.length() == 0) {
            return 0;
        }

        //将String转换成逆波兰式后存储
        List<String> RPN = new ArrayList<>();

        //符号栈
        Stack<Character> stack = new Stack<>();

        //去掉s中的空格字符，方便处理
        char[] chars = s.toCharArray();


        StringBuilder ss = new StringBuilder();

        for (char aChar : chars) {
            if (aChar != ' ') {
                ss.append(aChar);
            }
        }

        String standardS = ss.toString();


        for (int i = 0; i < standardS.length(); ) {
            char c = standardS.charAt(i);

            if (c == '(') {
                i++;
                stack.add(c);
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {

                if (stack.size() == 0 || stack.peek() == '(') {

                    //对负数的情况进行特殊处理,要么-号是第一个字符，即RPN为空，要么-号前一个符号是(,且中间没有其他符号
                    //此时的处理为：将-号视为-号，同时前面插入一个值0
                    if (c == '-' && (i == 0 || standardS.charAt(i - 1) == '(')) {
                        RPN.add("0");
                    }

                    stack.push(c);
                    i++;
                    continue;
                }

                Character peek = stack.peek();

                if ((peek == '*' || peek == '/')) {
                    RPN.add(stack.pop().toString());
                    stack.push(c);
                } else if ((c == '*' || c == '/')) {
                    //peek为加减，c为乘除
                    stack.push(c);
                } else {
                    //peek为加减，c为加减
                    RPN.add(stack.pop().toString());
                    stack.push(c);
                }


                i++;
            } else if (c == ')') {
                i++;
                while (stack.size() != 0 && stack.peek() != '(') {
                    RPN.add(stack.pop().toString());
                }
                //把'('也清掉
                stack.pop();
            }  else {
                StringBuilder builder = new StringBuilder();
                builder.append(standardS.charAt(i));
                i++;

                while (i < standardS.length() && standardS.charAt(i) >= 48 && standardS.charAt(i) <= 57) {
                    builder.append(standardS.charAt(i));
                    i++;
                }

                RPN.add(builder.toString());
            }

        }

        //不要忘了，最后如何有剩余符号，放到RPN里面
        while (stack.size() != 0) {
            RPN.add(stack.pop().toString());
        }

        return evalRPN(RPN.toArray(new String[0]));
    }

    int evalRPN(String[] tokens) {

        if (tokens == null || tokens.length == 0) {
            return 0;
        }

        Stack<String> stack = new Stack<>();

        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            if (token.equals("+")) {
                BigDecimal pop1 = new BigDecimal(stack.pop());
                BigDecimal pop2 = new BigDecimal(stack.pop());
                stack.push(pop1.add(pop2).toString());
            } else if (token.equals("-")) {
                BigDecimal pop1 = new BigDecimal(stack.pop());
                BigDecimal pop2 = new BigDecimal(stack.pop());
                stack.push(pop2.subtract(pop1).toString());
            } else if (token.equals("*")) {
                BigDecimal pop1 = new BigDecimal(stack.pop());
                BigDecimal pop2 = new BigDecimal(stack.pop());
                stack.push(pop1.multiply(pop2).toString());
            } else if (token.equals("/")) {
                BigDecimal pop1 = new BigDecimal(stack.pop());
                BigDecimal pop2 = new BigDecimal(stack.pop());
                stack.push(pop2.divide(pop1, 0, BigDecimal.ROUND_DOWN).toString());
            } else {
                stack.push(token);
            }
        }

        return Integer.valueOf(stack.pop());

    }

}
