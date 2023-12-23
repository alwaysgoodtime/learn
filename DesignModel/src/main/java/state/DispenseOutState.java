package state;

/**
 * 奖品发完状态
 * @author goodtime
 * @create 2020-03-10 9:08 下午
 */
public class DispenseOutState implements State {

    @Override
    public void deduceMoney(Activity activity) {
        System.out.println("奖品已发完");
    }

    @Override
    public boolean raffle(Activity activity) {
        System.out.println("奖品已发完");
        return false;
    }

    @Override
    public void dispensePrize(Activity activity) {
        System.out.println("奖品已发完");
    }
}
