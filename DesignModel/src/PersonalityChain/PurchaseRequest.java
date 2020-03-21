package PersonalityChain;

/**
 * 请求金额单
 * @author goodtime
 * @create 2020-03-11 12:41 上午
 */
public class PurchaseRequest {
    private int type;//请求类型
    private int price; //金额
    private int id = 0;//第几个请求

    public PurchaseRequest(int type, int price, int id) {
        this.type = type;
        this.price = price;
        this.id = id;
    }
    public int getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }
}
