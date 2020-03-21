package Observer;

import java.util.Observer;

/**
 * 测试jdk自带observable的使用
 * @author goodtime
 * @create 2020-03-10 4:02 下午
 */
public class JDKclient {
    public static void main(String[] args) {
        JDKobservable test = new JDKobservable();
        JDKobserver ts = new JDKobserver();
        test.addObserver(ts);
        test.setChanged();//通知前要setChanged，表示被观察者数据有改变，才能通知观察者传参，否则没用
        test.notifyObservers("aaa");
    }
}
