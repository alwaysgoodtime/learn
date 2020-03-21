import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author goodtime
 * @create 2020-03-08 9:05 下午
 */

class Number {

    public Number(){
    }

    int i = 0;

    int count = 0;

    int count2 = 65;

    char a = (char) count2;

    StringBuilder rt = new StringBuilder();

    ReentrantLock lock = new ReentrantLock();

    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();


    public void produce() {
        lock.lock();
        while (i != 0) {
            try {
                condition1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        i++;
        count++;
        rt.append(count);
        count++;
        rt.append(count);
        condition2.signal();
        lock.unlock();
    }

    public void custumer() {
        lock.lock();
        while (i != 1) {
            try {
                condition2.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        i--;
        rt.append(a);
        rt.append(" ");
        a = (char) ++count2;
        if(count == 52){
            System.out.println(rt.toString());
        }


        condition1.signal();
        lock.unlock();
    }
}

//现在是一个生产者，一个消费者，要精确通知
public class Main {
    public static void main(String[] args) {


        Number number = new Number();

        new Thread(() -> {
            for (int i = 0; i < 26; i++) {
                number.custumer();
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 26; i++) {
                number.produce();
            }
        }).start();


    }
}

