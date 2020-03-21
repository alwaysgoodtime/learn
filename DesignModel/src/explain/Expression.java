package explain;

import java.util.HashMap;

/**
 * 抽象类表达式，通过HashMap键值对，可以获取到变量的值
 * @author goodtime
 * @create 2020-03-10 6:34 下午
 */
public abstract class Expression {

    //解释公式和数数组，key就是公式参数[a,b,c],value就是具体值
    public abstract int interpreter(HashMap<String,Integer> var);
}
