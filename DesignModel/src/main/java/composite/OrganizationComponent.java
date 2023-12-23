package composite;

/**
 * @author goodtime
 * @create 2023-12-23 17:35
 */
public abstract class OrganizationComponent {

    protected String name;

    public OrganizationComponent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    //按照名字获取组织
    public abstract OrganizationComponent getChild(String name);

    //获取人员数量
    public abstract int getStaffCount();

    //添加机构
    public abstract void add(OrganizationComponent organizationComponent);

    @Override
    public String toString() {
        return name;
    }
}
