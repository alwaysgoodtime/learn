package Strategy;

/**
 * @author goodtime
 * @create 2020-03-11 12:09 上午
 */
public abstract class Duck {

    //属性，策略接口
    protected FlyBehavior flyBehavior;

    public abstract void display();
    //展示鸭子

    public void quack(){
        System.out.println("鸭子嘎嘎叫");
    }//这里没有将这个方法用上策略模式

    public void fly(){
        //改进
        if(flyBehavior != null){
            flyBehavior.fly();
        }
    };


    public void setFlyBehavior(FlyBehavior flyBehavior){
        this.flyBehavior = flyBehavior;
    }

}
