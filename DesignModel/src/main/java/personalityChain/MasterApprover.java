package personalityChain;

/**
 * @author goodtime
 * @create 2020-03-11 12:50 上午
 */
public class MasterApprover extends Approver {


    public MasterApprover(String name) {
        super(name);
    }

    @Override
    public void processRequest(PurchaseRequest purchaseRequest) {
            System.out.println( name +"能处理"+ purchaseRequest.getId());
    }
}
