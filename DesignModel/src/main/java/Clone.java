/**
 * 深拷贝：第一种方法，递归实现cloneable接口
 * @author goodtime
 * @create 2020-03-07 7:07 下午
 */
public class Clone {
    public static void main(String[] args) throws CloneNotSupportedException {
        A a = new A();
        A clone = a.clone();
        System.out.println(a.f == clone.f);

    }


}

class A implements Cloneable {
    int a = 1;
    String b = "ss";
    String c = new String("ss");
//    A d = new A();//克隆方法不要克隆自己，否则会出现StackOverflow
    Integer e = new Integer(1222);
    B f = new B();

    @Override
    protected A clone() throws CloneNotSupportedException {
        Object clone = super.clone();
        return (A)clone;
    }
}

class B implements Cloneable{
    int a = 1;
}