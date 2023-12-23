package bridge;

/**
 * @author goodtime
 * @create 2023-12-23 16:28
 */
public class Oppo extends Brand {

    public Oppo(Color color) {
        super(color);
    }

    @Override
    void buy() {
        System.out.println("买了Oppo的" + color + "手机");
    }


}
