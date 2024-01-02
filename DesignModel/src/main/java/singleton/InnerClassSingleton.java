package singleton;

/**
 * 静态内部类
 * 在被调用的时候创建，也是懒加载（延迟加载）
 * @author goodtime
 * @create 2023-12-23 02:20
 */
public class InnerClassSingleton {
    private InnerClassSingleton() {
        System.out.println("创建了一次");
    }

    public static InnerClassSingleton getSingle() {
        return InnerClassSingleton.Inner.instance;
    }

    private static class Inner {
        public static final InnerClassSingleton instance = new InnerClassSingleton();//一定是个全局常量，public static final 线程安全
    }

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(3000);
        InnerClassSingleton.getSingle();
        InnerClassSingleton.getSingle();
    }

}

