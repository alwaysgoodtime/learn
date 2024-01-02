package handler;

/**
 * @author goodtime
 * @create 2023-12-24 21:46
 */
public abstract class Handler {

    Handler nextHandler;

    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    abstract void handle(int money);

}
