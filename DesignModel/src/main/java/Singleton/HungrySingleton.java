package Singleton;

/**
 * @author goodtime
 * @create 2023-12-22 19:33
 */
public class HungrySingleton {

    private static final HungrySingleton instance = new HungrySingleton();

    //私有化构造器
    private HungrySingleton() {

    }

    public static HungrySingleton getInstance() {
        return instance;
    }

}
