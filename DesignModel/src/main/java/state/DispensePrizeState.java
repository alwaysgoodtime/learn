package state;

/**
 * 发放奖品状态
 * @author goodtime
 * @create 2020-03-10 9:08 下午
 */
public class DispensePrizeState implements State {

    @Override
    public void deduceMoney(Activity activity) {
        System.out.println("不能扣除积分");
    }

    @Override
    public boolean raffle(Activity activity) {
        System.out.println("不能抽奖");
        return false;
    }

    @Override
    public void dispensePrize(Activity activity) {
        if(activity.getCount() > 0){
            System.out.println("恭喜中奖");
            //改变状态为不能抽奖
            activity.setState(activity.getNoRaffleState());
            if(activity.getCount() == 0){//查看发完奖品是否已经没有奖品了
                //不做检查的话，后面的人还会抽奖
                activity.setState(activity.getDispenseOutState());
                //奖品领完的话，也可以直接设置退出，后面的人就不能抽了
                //System.exit(0);
            }
        }else{
            System.out.println("奖品被别人领完了,抽奖结束");
            activity.setState(activity.getDispenseOutState());
        }
    }
}
