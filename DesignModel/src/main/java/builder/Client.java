package builder;

/**
 * @author goodtime
 * @create 2023-12-23 02:47
 */
public class Client {
    public static void main(String[] args) {
        ManBuilder builder = new ManBuilder("脑子");
        Director director = new Director(builder);
        director.create();
        System.out.println(builder.create().toString());
    }
}
