package abstractFactory;


/**
 * @author goodtime
 * @create 2023-12-22 21:56
 */
public class Client {

    public static void main(String[] args) {

        Factory factory = new XiaomiFactory();

        factory.producePhone().call();
        factory.produceComputer().watch();

    }
}
