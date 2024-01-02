package strategy;

/**
 * @author goodtime
 * @create 2023-12-24 16:51
 */
public class Client {
    public static void main(String[] args) {
        Context context = new Context(new TaxiStrategy());
        System.out.println(context.getPrice(10));
        System.out.println(context.getTime(10));

    }
}
