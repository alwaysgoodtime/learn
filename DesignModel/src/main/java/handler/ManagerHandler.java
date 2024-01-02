package handler;

/**
 * @author goodtime
 * @create 2023-12-24 21:49
 */
public class ManagerHandler extends Handler {

    @Override
    void handle(int money) {
        if(money <= 1000){
            System.out.println("审批完毕");
        }else {
            System.out.println("交给上级审批");
            if(nextHandler != null){
                nextHandler.handle(money);
            }
        }
    }
}
