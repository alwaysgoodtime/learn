package leetcode.src.main.java.stack;

import java.math.BigDecimal;
import java.util.Stack;

/**
 * @author goodtime
 * @create 2023-12-02 21:38
 */
public class Number150 {
    public static void main(String[] args) {
        String[] tokens = {"2","9","+","-6","/"};
        System.out.println(new Solution150().evalRPN(tokens));
    }
}

/**
 * 逆波兰式
 *
 * 适合用栈操作运算：遇到数字则入栈；遇到算符则取出栈顶两个数字进行计算，并将结果压入栈中
 *
 */
class Solution150 {
    public int evalRPN(String[] tokens) {

        if(tokens == null || tokens.length == 0){
            return 0;
        }

        Stack<String> stack = new Stack<>();

        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            if(token.equals("+")){
                BigDecimal pop1 = new BigDecimal(stack.pop());
                BigDecimal pop2 = new BigDecimal(stack.pop());
                stack.push(pop1.add(pop2).toString());
            }else if(token.equals("-")){
                BigDecimal pop1 = new BigDecimal(stack.pop());
                BigDecimal pop2 = new BigDecimal(stack.pop());
                stack.push(pop2.subtract(pop1).toString());
            }else if(token.equals("*")){
                BigDecimal pop1 = new BigDecimal(stack.pop());
                BigDecimal pop2 = new BigDecimal(stack.pop());
                stack.push(pop1.multiply(pop2).toString());
            }else if(token.equals("/")){
                BigDecimal pop1 = new BigDecimal(stack.pop());
                BigDecimal pop2 = new BigDecimal(stack.pop());
                stack.push(pop2.divide(pop1,0,BigDecimal.ROUND_DOWN).toString());
            }else {
                stack.push(token);
            }
        }

        return Integer.valueOf(stack.pop());

    }
}
