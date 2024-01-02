package handler;

/**
 * @author goodtime
 * @create 2023-12-24 21:55
 */
public class CTOHandler extends Handler {
    @Override
    void handle(int money) {
        if (money <= 10000) {
            System.out.println("审批完毕");
        } else {
            if (nextHandler != null) {
                System.out.println("交给上级审批");
                nextHandler.handle(money);
            } else {
                System.out.println("无法审批");
            }
        }
    }
}
