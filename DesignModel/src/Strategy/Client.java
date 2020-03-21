package Strategy;

/**
 * @author goodtime
 * @create 2020-03-11 12:21 上午
 */
public class Client {
    public static void main(String[] args) {
        WildDuck wildDuck = new WildDuck();
        wildDuck.fly();
        wildDuck.setFlyBehavior(new NoFlyBehavior());//动态修改
        wildDuck.fly();
    }
}
