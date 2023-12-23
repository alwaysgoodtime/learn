package bridge;

/**
 * @author goodtime
 * @create 2023-12-23 16:25
 */
public class Black extends Color {

    String color = "black";


    @Override
    void painted() {
        this.color = "black";
        System.out.println("染黑");
    }


    @Override
    public String toString() {
        return color;
    }
}
