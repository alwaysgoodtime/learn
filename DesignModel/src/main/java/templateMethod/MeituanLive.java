package templateMethod;

/**
 * @author goodtime
 * @create 2023-12-23 23:03
 */
public class MeituanLive extends Live{
    @Override
    public void init() {
        System.out.println("打开美团APP");
    }

    @Override
    public void openRoom() {
        System.out.println("开好直播间");
    }

    @Override
    public void close() {
        System.out.println("点击右上角关闭");
    }
}
