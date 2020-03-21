package state;

/**
 * 状态抽奖接口
 * @author goodtime
 * @create 2020-03-10 9:07 下午
 */
public interface State {
    void deduceMoney();//扣钱
    boolean raffle();//抽奖
    void dispensePrize();//分奖品
}
