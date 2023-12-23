package composite;

import memento.Originator;

/**
 * @author goodtime
 * @create 2023-12-23 17:43
 */
public class OrganizationLeaf extends OrganizationComponent {

    int staff;

    //叶子节点必须设置staff数量
    public OrganizationLeaf(String name, int staff){
        super(name);
        this.staff = staff;
    }

    //叶子节点只能返回自己
    @Override
    public OrganizationComponent getChild(String name) {
        if (getName().equals(name)) {
            return this;
        }
        return null;
    }

    @Override
    public int getStaffCount() {
        return staff;
    }

    @Override
    public void add(OrganizationComponent organizationComponent) {
        throw new UnsupportedOperationException(this.name + "已经是叶子节点，无法添加下属部门");
    }
}
