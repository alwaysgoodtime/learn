package mediator;

/**中介者的抽象类
 * @author goodtime
 * @create 2020-03-10 4:38 下午
 */
public abstract class Mediator {

    public abstract void register(Colleague colleague ,String name);

    public abstract void getMessage(Object name,Object operation);
}
