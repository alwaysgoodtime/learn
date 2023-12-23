package proxy;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 利用cglib实现
 * @author goodtime
 * @create 2023-12-23 22:12
 */

class SuitorCglibProxy implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        if(method.getName().equals("submit")){
            System.out.println("拦截成功");
            System.out.println("证据为:"+ Arrays.asList(objects));
        }
        return methodProxy.invokeSuper(o,objects);
    }
}

class SuitorProxyCglibFactory {


    public static Suitor getProxy(Object target) {

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(new SuitorCglibProxy());
        return (Suitor) enhancer.create();
    }
}

public class CglibClient {
    public static void main(String[] args) {
        Suitor suitor = SuitorProxyCglibFactory.getProxy(new CuihuaNiu());
        suitor.submit("100元工资条");
    }
}
