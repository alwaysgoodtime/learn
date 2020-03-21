package Strategy;

/**
 * @author goodtime
 * @create 2020-03-11 12:10 上午
 */
public class ToyDuck extends Duck {

    public ToyDuck(){
        flyBehavior = new NoFlyBehavior();
    }


    @Override
    public void display() {
        System.out.println("这是玩具鸭");
    }
}
