package PersonalityChain;

/**
 * @author goodtime
 * @create 2020-03-11 12:52 上午
 */
public class Client {
    public static void main(String[] args) {
        //创建一个请求
        PurchaseRequest purchaseRequest = new PurchaseRequest(1,3000,1);
        DepartmentApprover departmentApprover = new DepartmentApprover("李主任");
        CollegeApprover collegeApprover = new CollegeApprover("王院长");
        MasterApprover masterApprover = new MasterApprover("张校长");
        departmentApprover.setApprover(collegeApprover);
        collegeApprover.setApprover(masterApprover);
        //如果这样的话，是单链，所有请求到校长那就到头了，这样一定要注意顺序
        masterApprover.setApprover(masterApprover);//这样就设置成环状，不过对于此例并没什么用
        departmentApprover.processRequest(purchaseRequest);
    }
}
