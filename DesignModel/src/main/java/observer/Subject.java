package observer;

/**被观察者，1个
 *
 * @author goodtime
 * @create 2020-03-10 3:07 下午
 */
public interface Subject {

    public void registerObserver(Observer o);

    public void removeObserver(Observer o);

    public void notifyObserver();


}
