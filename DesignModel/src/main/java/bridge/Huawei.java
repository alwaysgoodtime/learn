package bridge;

/**
 * @author goodtime
 * @create 2023-12-23 16:25
 */
public class Huawei extends Brand {


    public Huawei(Color color) {
        super(color);
    }

    @Override
    void buy() {
        System.out.println("买了Huawei的" + color + "手机");
    }
}
