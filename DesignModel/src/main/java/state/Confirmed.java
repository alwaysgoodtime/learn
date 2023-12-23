package state;

/**
 * @author goodtime
 * @create 2023-12-24 01:47
 */
public class Confirmed implements OrderState {
    @Override
    public void action() {
        System.out.println("已完成确认");
    }
}
