package abstractFactory;

/**
 * @author goodtime
 * @create 2023-12-22 21:50
 */
public class HuaweiFactory implements Factory {


    @Override
    public Phone producePhone() {
        return new HuaweiPhone();
    }

    @Override
    public Computer produceComputer() {
        return new HuaweiComputer();
    }
}
