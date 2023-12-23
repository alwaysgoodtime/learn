package proxy;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 利用java原生的reflect包实现
 * @author goodtime
 * @create 2023-12-23 21:51
 */

interface Suitor {
    void submit(String proof);
}

class CuihuaNiu implements Suitor {
    @Override
    public void submit(String proof) {
        System.out.println("这里是证据：" + proof);
    }
}




class SuitorProxy implements InvocationHandler {

    private Object object;//被代理对象

    public SuitorProxy(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("律师上场");
        return method.invoke(object, args);
    }
}

class SuitorProxyFactory {

    static Suitor getProxy(Suitor target) {

        return (Suitor) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
                new SuitorProxy(target));
    }

}

public class DynamicProxyClient {
    public static void main(String[] args) {
        Suitor suitor = SuitorProxyFactory.getProxy(new CuihuaNiu());
        suitor.submit("100元工资条");
    }
}
