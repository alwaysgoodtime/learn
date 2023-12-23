package visitor;


/**
 * @author goodtime
 * @create 2020-03-10 12:47 上午
 */
public class Man extends Person {
    @Override
    public void accept(Operation operation) {

        operation.getManResult(this);
    }

}
