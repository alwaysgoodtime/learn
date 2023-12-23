package bridge;

/**
 * 需要不同品牌、不同颜色的手机
 * @author goodtime
 * @create 2020-03-11 1:44 下午
 */
public class Client {
    public static void main(String[] args) {

        Brand brand = new Oppo(new Black());
        brand.buy();

    }
}
