package state;

/**
 * @author goodtime
 * @create 2020-03-10 9:55 下午
 */
public class Client {
    public static void main(String[] args) {
        Activity activity = new Activity(1);

        for (int i = 0; i < 30; i++) {
            activity.deduceMoney();
            activity.raffle();
        }

    }
}
