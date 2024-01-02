package visitor;

/**
 * @author goodtime
 * @create 2023-12-24 22:47
 */
public class DesignerElement implements Element {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
