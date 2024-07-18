package task;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.LockSupport;

/**
 * [手把手教你实现简单时间轮算法 - 叶新东博客 chn520.cn](http://chn520.cn/article_detail/1694074536479733)
 * 单层时间轮
 *
 * @author goodtime
 * @create 2024-01-20 21:22
 */
public class TaskSingleTimeWheel {
    // 时间轮
    private List<LinkedBlockingQueue<Task>> wheelQueue;
    // 时间轮线程
    private ThreadPoolExecutor wheelThreadService = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
    // 执行任务的线程
    private ThreadPoolExecutor taskThreadService = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
    // 正在阻塞的线程，如果有线程调用LockSupport.park()方法，那么blockThread一定有值
    private static volatile Thread blockThread;

    public TaskSingleTimeWheel() {
        //60个队列，每秒1个
        wheelQueue = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 60; i++) {
            wheelQueue.add(new LinkedBlockingQueue<>());
        }
    }

    // 添加任务
    public void add(Task task) {
        System.out.println("添加任务...");
        // 计算槽位，获取当前时间的秒数，放到对应的槽位
        int second = getCurrentDateSecond(task.getTime());
        LinkedBlockingQueue<Task> tasks = wheelQueue.get(second);
        if (!tasks.offer(task)) {
            System.out.println("添加任务" + task + "失败");
            return;
        }
        if (null != blockThread) {
            // 解除阻塞
            System.out.println("添加任务" + task + "成功，解除阻塞");
            LockSupport.unpark(blockThread);
            blockThread = null;
        }
    }

    // 启动定时任务
    public void start() {
        // 线程总数 - 已完成的线程数 = 正在运行的线程数量
        if (wheelThreadService.getTaskCount() > wheelThreadService.getCompletedTaskCount()) {
            System.out.println("定时任务已启动，请不要重复启动");
            return;
        }
        wheelThreadService.execute(() -> {
            // 与当前机器时间同步
            for (; ; ) {
                int index = getCurrentDateSecond(new Date());
                LinkedBlockingQueue<Task> tasks = wheelQueue.get(index);
                System.out.println("第" + index + "秒的任务数量有" + tasks.size() + "个");
                if (!tasks.isEmpty()) {
                    runTask(tasks);
                }
                // 延时一秒后遍历下一个槽
                sleep();
                if (index >= wheelQueue.size() - 1) {
                    // 阻塞当前线程
                    System.out.println("尝试阻塞");
                    blockThisThread();
                }
            }
        });
        System.out.println("开启,getCompletedTaskCount" + wheelThreadService.getCompletedTaskCount() + "getTaskCount:" + wheelThreadService.getTaskCount());
    }

    //转完1遍时间轮后，尝试判断队列中是否有任务，若没有任务则阻塞线程，避免不必要的开销
    private void blockThisThread() {
        // 所有的槽中都没有可执行的任务，阻塞当前线程
        if (0 == wheelQueue.stream().mapToLong(item -> item.stream().count()).sum()) {
            // 阻塞当前线程
            System.out.println("所有的槽中都没有可执行的任务，进行阻塞操作");
            blockThread = Thread.currentThread();
            LockSupport.park();
        }
    }

    /**
     * 运行任务
     *
     * @param tasks
     */
    private void runTask(LinkedBlockingQueue<Task> tasks) {
        while (!tasks.isEmpty()) {
            Task task = tasks.poll();
            System.out.println("开始执行任务，时间点" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(task.getTime()));
            // 运行
            taskThreadService.execute(task.getRunnable());
        }
    }

    // 睡眠一段时间
    public void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            System.out.println("睡眠异常" + e);
            throw new RuntimeException(e);
        }
    }

    // 睡眠一秒
    public void sleep() {
        sleep(1000);
    }

    /**
     * 获取当前时间的秒数(计算槽位）
     *
     * @param time
     * @return
     */
    private int getCurrentDateSecond(Date time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        // 因为时间轮是以秒为单位来计算的，所以获取当前时间的秒数
        int i = calendar.get(Calendar.SECOND);
        System.out.println("当前时间的秒数为" + i);
        return i;
    }

    /**
     * 获取当前时间的ms数
     *
     * @param time
     * @return
     */
    private int getCurrentDateMs(Date time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        int i = calendar.get(Calendar.MILLISECOND);
        System.out.println("当前时间的毫秒数为" + i);
        return i;
    }
}