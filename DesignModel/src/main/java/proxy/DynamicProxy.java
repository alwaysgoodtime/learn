package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理的举例：没有原来的静态代理类，而是动态生成
 *
 * @author goodtime
 * @create 2020-03-02 9:13 下午
 */

interface Human {
    void eat(String food);

    void sleep();
}

//被代理类

class SuperMan implements Human {

    @Override
    public void eat(String food) {
        System.out.println("吃神仙" + food);
    }

    @Override
    public void sleep() {
        System.out.println("超人在睡觉");
    }


}

class ProxyFactory {

    //调用此方法，返回一个代理类的对象，解决问题1
    //这是一个代理工厂，返回object，是为了不要让他
    //o就是被代理类的对象
    public static Object getProxyFactory(Object o) {

        MyInvocationHandler handler = new MyInvocationHandler();

        handler.bind(o);

        //Proxy是java.lang.reflect下的一个类

        //两个参数，第一个是传一个类加载器，一般就是传被代理类对象的加载器，第二个是传被代理类对象实现的接口，代理类要和被代理类实现同样的接口
        return Proxy.newProxyInstance(o.getClass().getClassLoader(), o.getClass().getInterfaces(), handler);
    }
}


//当我们通过代理类的对象，调用方法a时，就会自动调用这个加强版的调用处理器中的invoke方法
class MyInvocationHandler implements InvocationHandler {

    private Object obj = null;

    public void bind(Object obj) {
        this.obj = obj;
    }

    //这里还需要被代理类对象,在工厂中我们动态绑定一下
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        //代理类调用此方法时，被代理类对象也调用此方法,代理类对象的返回值，就是rt
        Object rt = method.invoke(obj, args);

        return rt;
    }
}


public class DynamicProxy {
    public static void main(String[] args) {

        //1.如何根据加载到内存的对象，动态生成代理类的对象
        SuperMan superMan = new SuperMan();

        //这里记录一个bug，实例方法调用父类的静态的方法时，没有提示

        //动态代理，返回的只是一个和代理类类加载器一样，实现了同样接口的类对象，而非被代理对象的类，比如这里，
        //只能强转成接口的对象，调用接口的方法，而不能强转成被代理的类。
        //否则会报Exception in thread "main" java.lang.ClassCastException: proxy.$Proxy0 cannot be cast to proxy.SuperMan
        //所有动态代理的对象，都是proxy.$Proxy0类的对象，表示它是动态代理得到的，$表明它是porxy中的内部类，0是被创建的序号
        Human proxyFactory = (Human) ProxyFactory.getProxyFactory(superMan);//这里传出来的对象，就是Human接口的一个实现类，但不是superMan，只是代理的superMan

        //2.当通过代理类的对象调用方法时，如果动态地调用被代理类中的同名方法，通过invacationhandler实现
        proxyFactory.sleep();
        proxyFactory.eat("tomato");


    }

}
