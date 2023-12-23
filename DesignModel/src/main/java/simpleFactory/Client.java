package simpleFactory;

/**
 * @author goodtime
 * @create 2023-12-22 21:56
 */
public class Client {

    public static void main(String[] args) {

        String name = "Bilibili";

        VideoParseFactory.getParser(name).handle();

    }
}
