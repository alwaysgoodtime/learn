package composite;

/**
 * @author goodtime
 * @create 2023-12-23 17:53
 */
public class OrganizationClient {
    public static void main(String[] args) {
        OrganizationComponent headOffice = new OrganizationComposite("总公司");
        OrganizationComponent tianjin = new OrganizationComposite("天津分公司");
        OrganizationComponent it = new OrganizationLeaf("总公司It部",30);
        OrganizationComponent hr = new OrganizationLeaf("总公司人力部",10);
        OrganizationComponent hrTianjin = new OrganizationLeaf("天津人力部",50);
        tianjin.add(hrTianjin);
        headOffice.add(it);
        headOffice.add(hr);
        headOffice.add(tianjin);

        System.out.println(headOffice.getChild("天津分公司").getStaffCount());
        System.out.println(headOffice.getStaffCount());
    }
}
