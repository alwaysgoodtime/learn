package Strategy;

/**
 * @author goodtime
 * @create 2020-03-11 12:10 上午
 */
public class WildDuck extends Duck {
    //构造器，传入FlyBehavior对象
    public WildDuck(){
        flyBehavior = new GoodFlyBehavior();
    }

    @Override
    public void display() {
        System.out.println("这是野鸭");
    }
}
