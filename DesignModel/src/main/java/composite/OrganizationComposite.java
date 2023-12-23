package composite;

import java.util.ArrayList;
import java.util.List;

/**
 * @author goodtime
 * @create 2020-03-08 1:13 上午
 */
public class OrganizationComposite extends OrganizationComponent {

    List<OrganizationComponent> lists = new ArrayList<>();

    public OrganizationComposite(String name) {
        super(name);
    }

    @Override
    public OrganizationComponent getChild(String name) {
        if (this.name.equals(name)) {
            return this;
        }
        for (int i = 0; i < lists.size(); i++) {
            OrganizationComponent child = lists.get(i).getChild(name);
            if (child != null) {
                return child;
            }
        }
        return null;
    }

    @Override
    public int getStaffCount() {
        int sum = 0;
        for (int i = 0; i < lists.size(); i++) {
            OrganizationComponent child = lists.get(i);
            if (child != null) {
                sum += child.getStaffCount();
            }
        }
        return sum;
    }

    @Override
    public void add(OrganizationComponent organizationComponent) {
        lists.add(organizationComponent);
    }
}
