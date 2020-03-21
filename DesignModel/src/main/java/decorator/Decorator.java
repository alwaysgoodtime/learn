package decorator;

/**
 * @author goodtime
 * @create 2020-03-08 12:04 上午
 */
public class Decorator extends Component {

    private Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public int count() {
        return price + component.price;
    }
}
