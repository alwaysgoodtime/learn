package Strategy;

/**
 * @author goodtime
 * @create 2020-03-11 12:10 上午
 */
public class NoFlyBehavior implements  FlyBehavior {
    @Override
    public void fly() {
        System.out.println("不会飞");
    }
}
