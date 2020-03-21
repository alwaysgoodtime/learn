package visitor;

/**
 * @author goodtime
 * @create 2020-03-10 12:50 上午
 */
public class Fail extends Operation{
    @Override
    public void getManResult(Man man) {
        System.out.println("男人认为失败了");
    }

    @Override
    public void getWomanResult(Woman man) {
        System.out.println("女人认为失败了");
    }
}
