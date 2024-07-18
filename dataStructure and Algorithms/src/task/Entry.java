package task;

import java.util.Date;

/**
 * @author goodtime
 * @create 2024-01-20 21:48
 */
public class Entry {
    public static void main(String[] args) throws InterruptedException {
        //启动
        TaskSingleTimeWheel taskSingleTimeWheel = new TaskSingleTimeWheel();
        taskSingleTimeWheel.start();

        Task task = new Task();
        task.setTime(new Date());

        task.setRunnable(() -> System.out.println("我是任务，我正在执行中"));

        taskSingleTimeWheel.add(task);
        taskSingleTimeWheel.add(task);
        taskSingleTimeWheel.add(task);
        taskSingleTimeWheel.add(task);

        taskSingleTimeWheel.sleep(5000);
        taskSingleTimeWheel.add(task);

        // 等待其他线程执行完
        Thread.currentThread().join();
    }
}
