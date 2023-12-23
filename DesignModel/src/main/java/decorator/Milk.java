package decorator;



/**
 * @author goodtime
 * @create 2020-03-08 12:05 上午
 */
public class Milk extends Decorator {

    public Milk(Component component) {
        super(component);
        setPrice(2);
    }

}
