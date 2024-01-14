/**
 * @author goodtime
 * @create 2024-01-14 00:45
 */
public class JHSDB_TestCase {
    static class Test {
        static ObjectHolder staticObj = new ObjectHolder();
        ObjectHolder instanceObj = new ObjectHolder();

        void foo() {
            System.out.println(System.getProperty("user.home"));
            System.out.println(System.getProperty("java.version"));
            System.out.println(System.getProperty("os.name"));
            System.out.println(System.getProperty("java.vendor.url"));
            ObjectHolder localObj = new ObjectHolder();
            System.out.println("done"); // 这里设一个断点
        }
    }

    private static class ObjectHolder {
    }

    public static void main(String[] args) {
        Test test = new JHSDB_TestCase.Test();
        test.foo();
    }
}
