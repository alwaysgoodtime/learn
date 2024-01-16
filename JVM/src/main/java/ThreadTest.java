import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author goodtime
 * @create 2024-01-14 12:36
 */
public class ThreadTest {

    /**
     * 线程死循环演示
     */
    public static void createBusyThread() {
        Thread thread = new Thread(() -> {
            while (true) {// 第15行
            }
        }, "testBusyThread");
        thread.start();
    }

    /**
     * 线程锁等待演示
     */
    public static void createLockThread(final Object lock) {
        Thread thread = new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "testLockThread");
        thread.start();
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        createBusyThread();
        br.readLine();
        Object obj = new Object();
        createLockThread(obj);
    }

}
