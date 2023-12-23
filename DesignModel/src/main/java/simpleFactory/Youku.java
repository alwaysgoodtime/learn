package simpleFactory;

/**
 * @author goodtime
 * @create 2023-12-22 23:04
 */
public class Youku extends Parser{

    private Youku(){}

    @Override
    public void handle() {

        System.out.println("解析youku视频");
    }
}
