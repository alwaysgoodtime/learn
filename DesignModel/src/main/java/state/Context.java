package state;

/**
 * @author goodtime
 * @create 2023-12-24 01:50
 */
public class Context {

    OrderState state;

    public Context(OrderState state) {
        this.state = state;
    }

    public void action() {
        state.action();
    }

    public void setState(OrderState state){
        this.state = state;
    }

}
