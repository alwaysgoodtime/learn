package decorator;

/**
 * @author goodtime
 * @create 2020-03-08 12:36 上午
 */
public class TesyDecorator {
    public static void main(String[] args) {
        Component lanshan = new Lanshan();
        System.out.println(lanshan.count());
        Component sugar = new Sugar(lanshan);
        System.out.println(sugar.getPrice());
        System.out.println(sugar.count());
        Milk milk = new Milk(sugar);
        System.out.println(milk.getPrice());
        System.out.println(milk.count());
    }
}
