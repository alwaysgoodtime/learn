package simpleFactory;

/**
 * @author goodtime
 * @create 2023-12-22 21:51
 */
public class Youtube extends Parser {

    private static final Youtube youtube = new Youtube();

    @Override
    public void handle() {
        System.out.println("正在解析youtube视频");
    }

}
