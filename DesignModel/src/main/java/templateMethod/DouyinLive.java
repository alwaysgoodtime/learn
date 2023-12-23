package templateMethod;

/**
 * @author goodtime
 * @create 2023-12-23 23:04
 */
public class DouyinLive extends Live {

    @Override
    public void init() {
        System.out.println("打开抖音");
    }

    @Override
    public void openRoom() {
        System.out.println("无需开直播间，一键直播");
    }

    @Override
    public void close() {
        System.out.println("退出抖音");
    }
}
