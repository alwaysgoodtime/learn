package state;

/**
 * @author goodtime
 * @create 2020-03-10 9:09 下午
 */
public class Activity {

    //state 表示活动当前状态
    private State state;

    //奖品数量
    private int count;


    public Activity(int count) {
        this.state = getNoRaffleState();//初始化为不能抽奖状态。
        this.count = count;
    }

    //四个属性表示四种状态
    private State noRaffleState = new NoRaffleState();
    private State canRaffleState = new CanRaffleState();
    private State dispenseOutState = new DispenseOutState();
    private State dispensePrizeState = new DispensePrizeState();

    //扣分，调用当前状态的duduceMoney
    public void deduceMoney() {
        state.deduceMoney(this);
    }

    //抽奖
    public void raffle() {
        if (state.raffle(this)) {
            state.dispensePrize(this);
        }
    }


    public int getCount() {//奖品数目,如果数目大于0，说明还有，count自动扣除1个
        int curCount = count;
        count--;
        return curCount;
    }

    public void setState(State state) {
        this.state = state;
    }



    public State getNoRaffleState() {
        return noRaffleState;
    }


    public State getCanRaffleState() {
        return canRaffleState;
    }


    public State getDispenseOutState() {
        return dispenseOutState;
    }


    public State getDispensePrizeState() {
        return dispensePrizeState;
    }

}
