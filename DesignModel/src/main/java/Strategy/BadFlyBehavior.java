package Strategy;

/**
 * @author goodtime
 * @create 2020-03-11 12:11 上午
 */
public class BadFlyBehavior implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("飞翔技术一般");
    }
}
