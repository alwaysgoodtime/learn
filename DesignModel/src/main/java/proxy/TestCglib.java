package proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author goodtime
 * @create 2020-03-07 4:01 上午
 */


class ProxyFactorys implements MethodInterceptor {

    //维护一个目标对象
    private Object object;

    //构造器传入被代理对象
    public ProxyFactorys(Object object) {
        this.object = object;
    }

    public Object getProxyInstance(){
        //1.创建一个工具类
        Enhancer enhancer = new Enhancer();
        //2.设置父类，创建目标对象的继承类
        enhancer.setSuperclass(object.getClass());
        //3.设置回调函数,表示返回后调用它自己
        enhancer.setCallback(this);
        //4.创建子类对象（代理对象）
        return enhancer.create();

    }

    //在该方法中实现对目标对象方法的调用
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib代理开始");
        Object invoke = method.invoke(object, objects);
        System.out.println("cglib代理完成");
        return invoke;
    }
}

class TeacherDao{
    public void teach(){
        System.out.println("老师授课中");
    }
}

public class TestCglib {
    public static void main(String[] args) {
        TeacherDao teacherDao = new TeacherDao();
        TeacherDao proxyInstance = (TeacherDao)new ProxyFactorys(teacherDao).getProxyInstance();
        proxyInstance.teach();
    }
}
