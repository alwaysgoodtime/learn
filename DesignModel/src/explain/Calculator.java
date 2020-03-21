package explain;


import java.util.HashMap;
import java.util.Stack;

/**
 * @author goodtime
 * @create 2020-03-10 6:34 下午
 */
public class Calculator {
    private Expression expression;

    public Calculator(String expStr){//构造器传参
        Stack<Expression> stack = new Stack<>();
        char[] chars = expStr.toCharArray();
        Expression left = null;
        Expression right = null;
        for (int i = 0; i < chars.length; i++) {
            switch (chars[i]) {
                case '+':
                    left = stack.pop();
                    right = new VarExpression(String.valueOf(chars[++i]));
                    stack.push(new AddExpression(left, right));
                    break;
                case '-':
                    left = stack.pop();
                    right = new VarExpression(String.valueOf(chars[++i]));
                    stack.push(new SubExpression(left, right));
                    break;
                default:
                    stack.push(new VarExpression(String.valueOf(chars[i])));
                    break;
            }
        }
        this.expression = stack.pop();
    }

    public int run(HashMap<String,Integer> var){

        return this.expression.interpreter(var);
    }

}
