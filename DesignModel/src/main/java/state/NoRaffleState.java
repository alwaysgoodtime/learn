package state;

/**
 * 不能抽奖状态对应的类,没扣钱
 *
 * @author goodtime
 * @create 2020-03-10 9:07 下午
 */
public class NoRaffleState implements State {

    //当前状态可以扣钱，扣完后，将状态设置成可以抽奖状态
    @Override
    public void deduceMoney(Activity activity) {
        System.out.println("扣除2元成功，您可以抽奖了");
        activity.setState(activity.getCanRaffleState());
    }

    @Override
    public boolean raffle(Activity activity) {
        System.out.println("扣了钱才能抽奖");
        return false;
    }

    @Override
    public void dispensePrize(Activity activity) {
        System.out.println("没扣钱，还未抽奖，不能发奖品");
    }
}
