package state;

/**
 * 奖品发完状态
 * @author goodtime
 * @create 2020-03-10 9:08 下午
 */
public class DispenseOutState implements State {

    Activity activity;

    public DispenseOutState(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void deduceMoney() {
        System.out.println("奖品已发完");
    }

    @Override
    public boolean raffle() {
        System.out.println("奖品已发完");
        return false;
    }

    @Override
    public void dispensePrize() {
        System.out.println("奖品已发完");
    }
}
