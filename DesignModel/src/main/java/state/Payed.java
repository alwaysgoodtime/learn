package state;

/**
 * @author goodtime
 * @create 2023-12-24 01:47
 */
public class Payed implements OrderState{
    @Override
    public void action() {
        System.out.println("支付完成，等待到货后确认");
    }
}
