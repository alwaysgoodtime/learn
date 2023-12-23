package state;

/**
 * 订单状态：未付款、付款、已确认
 * @author goodtime
 * @create 2023-12-24 01:46
 */
public interface OrderState {
    void action();
}
