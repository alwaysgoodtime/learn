package Strategy;

/**
 * @author goodtime
 * @create 2020-03-11 12:09 上午
 */
public class PekingDuck extends Duck {


    public PekingDuck(){
        flyBehavior = new BadFlyBehavior();
    }


    @Override
    public void display() {
        System.out.println("这是野鸭");
    }
}
