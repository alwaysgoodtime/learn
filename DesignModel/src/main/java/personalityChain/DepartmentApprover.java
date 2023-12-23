package personalityChain;

/**
 * @author goodtime
 * @create 2020-03-11 12:46 上午
 */
public class DepartmentApprover extends Approver {

    public DepartmentApprover(String name) {
        super(name);
    }

    @Override
    public void processRequest(PurchaseRequest purchaseRequest) {
        if (purchaseRequest.getPrice() <= 5000) {
            System.out.println(name +"能处理" + purchaseRequest.getId());
        } else {
            approver.processRequest(purchaseRequest);
        }
    }
}
