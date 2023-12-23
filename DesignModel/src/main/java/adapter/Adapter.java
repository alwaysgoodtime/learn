package adapter;

/**
 * @author goodtime
 * @create 2023-12-23 04:22
 */
public class Adapter implements LogFactory {

    Adaptee adaptee = new Adaptee();

    public Adapter() {
    }

    @Override
    public void print(String errorName, String error) {
        adaptee.print(errorName, error, String.valueOf(System.currentTimeMillis()));
    }
}
