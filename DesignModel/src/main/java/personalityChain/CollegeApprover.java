package personalityChain;

/**
 * @author goodtime
 * @create 2020-03-11 12:49 上午
 */
public class CollegeApprover extends Approver{


    public CollegeApprover(String name) {
        super(name);
    }

    @Override
    public void processRequest(PurchaseRequest purchaseRequest) {
        if(purchaseRequest.getPrice() <= 30000){
            System.out.println( name +"能处理"+ purchaseRequest.getId());
        }else {
            approver.processRequest(purchaseRequest);
        }
    }
}
