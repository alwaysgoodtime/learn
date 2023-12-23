package decorator;

/**
 * @author goodtime
 * @create 2020-03-07 11:55 下午
 */
public abstract class Coffee extends Component {//多了一层抽象

    public Coffee() {
    }

    @Override
    public int count() {
        return price;
    }
}
