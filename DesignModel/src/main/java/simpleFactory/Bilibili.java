package simpleFactory;

/**
 * @author goodtime
 * @create 2023-12-22 21:50
 */
public class Bilibili extends Parser {

    private Bilibili(){}

    @Override
    public void handle() {
        System.out.println("正在解析Bilibili视频");
    }


}
