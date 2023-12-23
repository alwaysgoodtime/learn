package observer;

import java.util.Observable;
import java.util.Observer;

/**
 * @author goodtime
 * @create 2020-03-10 3:59 下午
 */
public class JDKobserver implements Observer {

    private Object a;

    @Override
    public void update(Observable o, Object arg) {
        a = arg;
        System.out.println("laaa");
        System.out.println(a.toString());
    }
}
