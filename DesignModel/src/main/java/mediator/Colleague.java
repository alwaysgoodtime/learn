package mediator;

/**
 * @author goodtime
 * @create 2020-03-10 4:39 下午
 */
public abstract class Colleague {
    public abstract void sendMessage(int operation);
    public abstract Mediator getMediator();
}
