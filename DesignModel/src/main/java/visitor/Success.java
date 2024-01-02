package visitor;

/**
 * @author goodtime
 * @create 2020-03-10 12:48 上午
 */
public class Success extends  Operation {

    @Override
    public void getManResult(Man man) {
        System.out.println("男人认为成功了");
    }

    @Override
    public void getWomanResult(Woman man) {
        System.out.println("女人认为成功了");
    }
}
