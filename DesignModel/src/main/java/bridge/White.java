package bridge;

/**
 * @author goodtime
 * @create 2023-12-23 16:25
 */
class White extends Color {

    @Override
    void painted() {
        this.color = "white";
        System.out.println("染白");

    }


    @Override
    public String toString() {
        return color;
    }
}
