package prototype;

/**
 * @author goodtime
 * @create 2023-12-23 03:47
 */
public class Client {
    public static void main(String[] args) {
        ReportPrototype reportPrototype = new ReportPrototype();
        reportPrototype.write();
        Prototype copy = reportPrototype.copy();
        System.out.println(copy + " to Pony");
        Prototype copy2 = reportPrototype.copy();
        System.out.println(copy2 + " to Jack");
    }
}
