package templateMethod;

/**
 * @author goodtime
 * @create 2023-12-23 23:01
 */
public abstract class Live {

    public abstract void init();
    public abstract void openRoom();
    public abstract void close();

    public final void play(){
        init();
        openRoom();
        close();
    }
}
