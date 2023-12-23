package state;

/**
 * 状态抽奖接口
 * @author goodtime
 * @create 2020-03-10 9:07 下午
 */
public interface State {
    void deduceMoney(Activity activity);//扣钱
    boolean raffle(Activity activity);//抽奖
    void dispensePrize(Activity activity);//分奖品
}
