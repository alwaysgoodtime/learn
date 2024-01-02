package visitor;

/**
 * @author goodtime
 * @create 2023-12-24 22:44
 */
public interface Element {
    void accept(Visitor visitor);
}
