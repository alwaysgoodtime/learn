package explain;

import java.util.HashMap;

/**
 * 运算符号解析器
 * @author goodtime
 * @create 2020-03-10 6:34 下午
 */
public class SymbolExpression extends Expression  {
    protected Expression left;
    protected Expression right;

    public SymbolExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    //因为SymbolExpression是让其子类来实现，这里是默认实现
    @Override
    public int interpreter(HashMap<String, Integer> var) {
        return 0;
    }

}
