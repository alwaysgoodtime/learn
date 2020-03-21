package proxy;

/**
 * 静态代理
 *特点：代理类和被代理类，在编译期间就确定了
 * @author goodtime
 * @create 2020-03-02 8:16 下午
 */
interface ClotheFactory {
    void produceClothes();
}

//代理类
class ProxyClotheFactory implements ClotheFactory{

    private ClotheFactory clotheFactory;//聚合模式

    public ProxyClotheFactory(ClotheFactory clotheFactory) {
        this.clotheFactory = clotheFactory;//在构造器中将被代理对象传进来
    }


    @Override
    public void produceClothes() {
        System.out.println("生产衣服的准备工作");
        clotheFactory.produceClothes();
        System.out.println("生产玩衣服后的工作");
    }

}

//被代理类

class NikeFactory implements ClotheFactory{

    @Override
    public void produceClothes() {
        System.out.println("nike开始发设计图");
    }
}



public class StaticProxy {

    public static void main(String[] args) {

        //创建被代理类对象
        ClotheFactory nikeFactory = new NikeFactory();

        //创建代理类的对象,传入nike的参数
        ClotheFactory clotheFactory = new ProxyClotheFactory(nikeFactory);

        clotheFactory.produceClothes();


    }
}
