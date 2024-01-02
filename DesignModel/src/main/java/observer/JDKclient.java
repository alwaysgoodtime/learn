package observer;

/**
 * 测试jdk自带observable的使用
 * @author goodtime
 * @create 2020-03-10 4:02 下午
 */
public class JDKclient {
    public static void main(String[] args) {
        //1.创建被观察者
        JDKobservable test = new JDKobservable();
        //2.创建观察者
        JDKobserver ts = new JDKobserver();
        //3.注册观察者
        test.addObserver(ts);
        //4.被观察者发生改变
        test.setChanged();
        //通知前要setChanged，表示被观察者数据有改变，才能通知观察者传参，否则
        //notifyObservers会忽略消息
        //5.告知观察者
        test.notifyObservers("aaa");
    }
}
