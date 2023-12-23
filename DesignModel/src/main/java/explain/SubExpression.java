package explain;

import java.util.HashMap;

/**
 * @author goodtime
 * @create 2020-03-10 6:35 下午
 */
public class SubExpression extends SymbolExpression {

    public SubExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public int interpreter(HashMap<String, Integer> var) {
        return left.interpreter(var) - right.interpreter(var);
    }
}
