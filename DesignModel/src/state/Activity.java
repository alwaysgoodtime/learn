package state;

/**
 * @author goodtime
 * @create 2020-03-10 9:09 下午
 */
public class Activity {

    //state 表示活动当前状态
    private State state = null;

    //奖品状态
    private int count = 0;


    public Activity(int count) {
        this.state = getNoRaffleState();//初始化为不能抽奖状态。
        this.count = count;
    }

    //四个属性表示四种状态
    private State noRaffleState = new NoRaffleState(this);
    private State canRaffleState = new CanRaffleState(this);
    private State dispenseOutState = new DispenseOutState(this);
    private State dispensePrizeState = new DispensePrizeState(this);

    //扣分，调用当前状态的duduceMoney
    public void deduceMoney() {
        state.deduceMoney();
    }

    //抽奖
    public void raffle() {
        if (state.raffle()) {
            state.dispensePrize();
        }
    }

    public void dispensePrize(){
        state.dispensePrize();
    }


    public State getState() {
        return state;
    }


    public int getCount() {//奖品数目,如果数目大于0，说明还有，count自动扣除1个
        int curCount = count;
        count--;
        this.count = count;
        return curCount;
    }

    public void setState(State state) {
        this.state = state;
    }



    public State getNoRaffleState() {
        return noRaffleState;
    }

    public void setNoRaffleState(State noRaffleState) {
        this.noRaffleState = noRaffleState;
    }

    public State getCanRaffleState() {
        return canRaffleState;
    }

    public void setCanRaffleState(State canRaffleState) {
        this.canRaffleState = canRaffleState;
    }

    public State getDispenseOutState() {
        return dispenseOutState;
    }

    public void setDispenseOutState(State dispenseOutState) {
        this.dispenseOutState = dispenseOutState;
    }

    public State getDispensePrizeState() {
        return dispensePrizeState;
    }

    public void setDispensePrizeState(State dispensePrizeState) {
        this.dispensePrizeState = dispensePrizeState;
    }
}
