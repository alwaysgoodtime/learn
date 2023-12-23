package adapter;

/**
 * @author goodtime
 * @create 2023-12-23 04:23
 */
public class Client {
    public static void main(String[] args) {
        LogFactory factory = new Adapter();
        factory.print("oom", "oom error");
    }
}
