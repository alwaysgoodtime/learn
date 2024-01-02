package visitor;

/**
 * @author goodtime
 * @create 2023-12-24 22:49
 */
public class PMVisitor implements Visitor{
    @Override
    public void visit(ProgrammerElement programmerElement) {
        System.out.println("还没写完么");
    }

    @Override
    public void visit(DesignerElement designerElement) {
        System.out.println("我奶奶来都搞完了");
    }
}
