package explain;

import java.util.HashMap;

/**
 * @author goodtime
 * @create 2020-03-10 6:34 下午
 */
public class VarExpression extends Expression {

    private String key;

    public VarExpression(String key) {
        this.key = key;
    }

    @Override
    public int interpreter(HashMap<String, Integer> var) {
        return var.get(key);//根据变量的名称，返回具体值
    }

}
