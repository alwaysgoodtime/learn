/**
 * @author goodtime
 * @create 2024-01-22 22:29
 */
public class StaticTest {

    private static int num  = 1;
    private static final int num3 = 2;

    static {
        System.out.println("num = " + num);
        num = 2;
        num2 = 3;
        System.out.println("num = " + num);
    }

    private static int num2 = 2;

    static {
        num2 = 4;
        System.out.println("num2 = " + num2);
    }

    {
        num2 = 6;
    }

    private int getNum2(){
        return num2;
    }

    public static void main(String[] args) {
        System.out.println("test static load");
        System.out.println(StaticTest.num2);
        System.out.println(new StaticTest().getNum2());
    }
}
