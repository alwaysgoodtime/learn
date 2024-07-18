/**
 * 通过创建类检查字节码
 * @author goodtime
 * @create 2024-01-20 16:54
 */
public class ObjectTest {
    public static void main(String[] args) {
        Object o = new Object();
        System.out.println(o);

        int[][] a = new int[10][];
        int[] ints = a[1];
        System.out.println(ints == null);

        Order order = new Order();
        order.orderId = 1;
        System.out.println(order.orderId);
        Order.name = 2;
        System.out.println(Order.name);

    }


}

class Order{
    int orderId;
    static int name;
}