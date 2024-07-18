package task;

import java.util.Date;

/**
 * @author goodtime
 * @create 2024-01-20 21:29
 */
public class Task {
    // 任务执行时间
    private Date time;
    // 需要执行的任务任务
    private Runnable runnable;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Runnable getRunnable() {
        return runnable;
    }

    public void setRunnable(Runnable runnable) {
        this.runnable = runnable;
    }
}
