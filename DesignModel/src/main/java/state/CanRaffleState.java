package state;

import java.util.Random;

/**
 * @author goodtime
 * @create 2020-03-10 9:08 下午
 */
public class CanRaffleState implements State {

    @Override
    public void deduceMoney(Activity activity) {
        System.out.println("您已扣过了积分");

    }

    @Override
    public boolean raffle(Activity activity) {
        System.out.println("正在抽奖");
        Random r = new Random();
        int num = r.nextInt(10);
        if (num == 0) {
            //改变活动状态为发放奖品状态
            System.out.println("您已中奖，请领取奖品");
            activity.setState(activity.getDispensePrizeState());
            return true;
        } else {
            System.out.println("很遗憾，您未中奖");
            activity.setState(activity.getNoRaffleState());//没抽中奖，回退成未抽奖前状态
            return false;
        }
    }

    @Override
    public void dispensePrize(Activity activity) {
        System.out.println("您还没有抽奖");
        activity.setState(activity.getDispensePrizeState());
    }
}
