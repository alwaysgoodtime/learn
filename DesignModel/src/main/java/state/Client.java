package state;

/**
 * @author goodtime
 * @create 2020-03-10 9:55 下午
 */
public class Client {
    public static void main(String[] args) {

        //这是第一个例子，其状态切换是由State自动完成的，也即每个State都认识
        Activity activity = new Activity(1);

        for (int i = 0; i < 30; i++) {
            activity.deduceMoney();
            activity.raffle();
        }

        //这是第二个例子，其状态切换是Context完成的
        Context context = new Context(new UnPay());
        context.action();
        //状态切换
        context.setState(new Payed());
        context.action();
        //状态继续切换
        context.setState(new Confirmed());
        context.action();
    }
}
