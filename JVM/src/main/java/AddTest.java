/**
 * @author goodtime
 * @create 2024-01-20 13:48
 */
public class AddTest {
    public static void main(String[] args) {
        System.out.println("test add difference");
        byte a = 1;
        add(a);
        add(1);
    }

    private static void add(byte i){
        i = 127;
        i += 10;
        System.out.println(i);
    }

    private static void add(int i){
        i = 127;
        i += 10;
        System.out.println(i);
    }
}
