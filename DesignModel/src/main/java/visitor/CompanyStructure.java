package visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author goodtime
 * @create 2023-12-24 22:50
 */
public class CompanyStructure {

    private List<Element> employee = new ArrayList();

    public CompanyStructure() {
        employee.add(new ProgrammerElement());
        employee.add(new DesignerElement());
    }

    public void accept(Visitor visitor) {
        for (Element element : employee) {
            element.accept(visitor);
        }
    }
}
