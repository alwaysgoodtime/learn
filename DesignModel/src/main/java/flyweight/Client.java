package flyweight;

/**
 * @author goodtime
 * @create 2023-12-23 19:44
 */
public class Client {
    public static void main(String[] args) {
        FlyweightFactory flyweightFactory = new FlyweightFactory();
        Chess flyweight = flyweightFactory.getFlyweight(Color.WHITE);
        Chess flyweight1 = flyweightFactory.getFlyweight(Color.BLACK);


        flyweight.draw(new Point(1, 1));
        flyweight1.draw(new Point(2, 2));
    }
}
