package visitor;

/**
 * @author goodtime
 * @create 2020-03-10 12:48 上午
 */

//说明
//1.这里我们使用了双分派,即首先在客户端程序中，将具体状态作为参数传递给
//Woman中（第一次分派），
//2.然后woman类调用作为参数的"具体方法"中方法getWomanResult，同时将自己
//自己作为参数传入，完成第二次分派
public class Woman extends Person {
    @Override
    public void accept(Operation operation) {
        operation.getWomanResult(this);
    }
}
