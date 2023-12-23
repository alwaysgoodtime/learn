package observer;

import java.util.Observable;
import java.util.Observer;

/**
 * @author goodtime
 * @create 2020-03-10 3:57 下午
 */
public class JDKobservable  extends Observable {

    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
    }

    @Override
    public void notifyObservers(Object arg) {
        super.notifyObservers(arg);
    }

    @Override
    protected synchronized void setChanged() {
        super.setChanged();
    }
}
