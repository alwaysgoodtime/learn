package visitor;

/**
 * @author goodtime
 * @create 2020-03-10 1:07 上午
 */
public class Wait extends Operation {
    @Override
    public void getManResult(Man man) {
        System.out.println("男人给的评价是待定");
    }

    @Override
    public void getWomanResult(Woman man) {
        System.out.println("女人给的评价是待定");
    }
}
