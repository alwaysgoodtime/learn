package visitor;

/**
 * @author goodtime
 * @create 2023-12-24 22:45
 */
public interface Visitor {

    void visit(ProgrammerElement programmerElement);

    void visit(DesignerElement designerElement);
}
