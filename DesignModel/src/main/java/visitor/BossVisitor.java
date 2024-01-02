package visitor;

/**
 * @author goodtime
 * @create 2023-12-24 22:48
 */
public class BossVisitor implements Visitor {
    @Override
    public void visit(ProgrammerElement programmerElement) {
        System.out.println("为什么不好好写代码");
    }

    @Override
    public void visit(DesignerElement designerElement) {
        System.out.println("为什么不好好切图");
    }
}
