package handler;

/**
 * @author goodtime
 * @create 2023-12-24 21:54
 */
public class BossHandler extends Handler {
    @Override
    void handle(int money) {
        if (money <= 5000) {
            System.out.println("审批完毕");
        } else {
            if (nextHandler != null) {
                nextHandler.handle(money);
            } else {
                System.out.println("无法审批");
            }
        }
    }
}
