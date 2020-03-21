package decorator;

/**
 * @author goodtime
 * @create 2020-03-08 12:35 上午
 */
public class Sugar extends Decorator {

    public Sugar(Component component) {
        super(component);
        setPrice(1);
    }
}
