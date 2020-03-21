package decorator;

/**
 * @author goodtime
 * @create 2020-03-07 11:52 下午
 */
public abstract class Component {//抽象类，主体类和包装类都要实现
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    int price;

    public abstract int count();//主体类和被包装类有两处不同，
    //一处是包装类要有被包装类，才能得到被包装类的属性，可以通过构造器或着set得到
    //第二处是包装类和被包装类的count方法实现不同

}
