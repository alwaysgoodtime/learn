package explain;

import java.util.HashMap;

/**
 * @author goodtime
 * @create 2020-03-10 6:35 下午
 */
public class AddExpression extends SymbolExpression {
    public AddExpression(Expression left, Expression right) {
        super(left, right);
    }

    //处理加法
    @Override
    public int interpreter(HashMap<String, Integer> var) {
        return left.interpreter(var) + right.interpreter(var);
        //这里注意，left和right一定要是VarExpression才行，否则就会调用抽象
        //方法的interpreter。
    }
}
