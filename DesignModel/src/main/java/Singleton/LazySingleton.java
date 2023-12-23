package Singleton;

/**
 * 懒汉式
 *
 * @author goodtime
 * @create 2023-12-22 19:26
 */
public class LazySingleton {
    private static volatile LazySingleton instance = new LazySingleton();

    //私有化构造器，避免其他方式被实例化
    private LazySingleton() {
    }

    public static LazySingleton getInstance() {

        if (instance == null) {
            //缩小synchronized加锁粒度，加快getInstance()方法
            synchronized (LazySingleton.class) {
                //getInstance 查询前再加一层判定，避免指令重排
                if (instance == null) {
                    instance = new LazySingleton();
                }
            }
        }


        return instance;

    }
}
