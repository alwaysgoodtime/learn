package bridge;

/**
 * @author goodtime
 * @create 2023-12-23 16:25
 */
public abstract class Brand {

    Color color;

    public Brand(Color color){
        this.color = color;
    }

    abstract void buy();
}
