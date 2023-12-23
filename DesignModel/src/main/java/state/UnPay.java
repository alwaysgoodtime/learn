package state;

/**
 * @author goodtime
 * @create 2023-12-24 01:47
 */
public class UnPay implements OrderState{
    @Override
    public void action() {
        System.out.println("跳转支付宝支付");
    }
}
